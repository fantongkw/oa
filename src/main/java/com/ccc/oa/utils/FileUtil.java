package com.ccc.oa.utils;

import com.ccc.oa.Exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * @ClassName: FileUtil
 * @Author: Administrator
 * @Description: 文件相关工具类
 * @Date: 2019/1/3 20:43
 * @Version: 1.0
 **/
public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
    public static final String BASE_PATH = "/images/upload/";

    /**
     * @Description 获取UUID
     * @Date 2019/1/14 10:30
     * @Param []
     * @return java.lang.String
     **/

    public static String uuid() {
        return getUUID(true);
    }
    
    /**
     * @Description 生成UUID
     * @Date 2019/1/14 10:30
     * @Param [isDeleteSlash]
     * @return java.lang.String
     **/

    public static String getUUID(boolean isDeleteSlash) {
        String uuid = UUID.randomUUID().toString();
        return isDeleteSlash ? uuid.replaceAll("-", "") : uuid;
    }

    /**
     * @Description 创建目录
     * @Date 2019/1/13 21:42 
     * @Param [path]
     * @return void
     **/
    public static void creatFile(String path) {
        File filePath = new File(path);
        if (filePath.exists()) {
            return;
        }
        boolean result = filePath.mkdirs();
        Assert.isTrue(result, "Failed to create file directory");
    }

    /**
     * @Description 获取项目资源路径
     * @Date 2019/1/13 21:43 
     * @Param []
     * @return java.lang.String
     **/
    public static String getResourcePath() {
        String path = "";
        if (null != ClassUtils.getDefaultClassLoader()) {
            URL filePath = ClassUtils.getDefaultClassLoader().getResource("static");
            if (null != filePath) {
                path = filePath.getPath() + BASE_PATH;
            }
        }
        return path;
    }

    /**
     * @Description 获取唯一文件名(MultipartFile格式)
     * @Date 2019/7/4 17:36
     * @Param [file]
     * @return java.lang.String
     **/
    public static String getUUIDFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String newName = "";
        if (null != fileName) {
            newName = uuid() + fileName.substring(fileName.lastIndexOf("."));
        }
        return newName;
    }

    /**
     * @Description 获取唯一文件名(Base64格式)
     * @Date 2019/7/4 17:40
     * @Param [file]
     * @return java.lang.String
     **/
    public static String getUUIDFileName(String file) {
        return uuid() + getBase64Format(file);
    }

    /**
     * @Description base64划分数组(第一部分文件后缀 第二部分文件数据)
     * @Date 2019/7/4 17:55
     * @Param [file]
     * @return java.lang.String[]
     **/
    private static String[] getBase64(String file) {
        return file.split("base64,");
    }

    /**
     * @Description 获取文件后缀
     * @Date 2019/7/4 17:50
     * @Param [tailoring]
     * @return java.lang.String
     **/
    private static String getBase64Format(String picture) {
        String dataPrefix = getBase64(picture)[0];
        String suffix;
        if("data:image/jpeg;".equalsIgnoreCase(dataPrefix)){
            suffix = ".jpg";
        } else if("data:image/x-icon;".equalsIgnoreCase(dataPrefix)){
            suffix = ".ico";
        } else if("data:image/gif;".equalsIgnoreCase(dataPrefix)){
            suffix = ".gif";
        } else if("data:image/png;".equalsIgnoreCase(dataPrefix)){
            suffix = ".png";
        }else{
            throw new CustomException("上传图片格式不合法");
        }
        return suffix;
    }

    /**
     * @Description 获取文件大小
     * @Date 2019/7/4 18:00
     * @Param [tailoring]
     * @return int
     **/
    private static int getPictureSize(String picture) {
        String base64Date = getBase64(picture)[1];
        base64Date = base64Date.substring(0, base64Date.indexOf("="));
        int length = base64Date.length();
        return length - (length / 8) * 2;
    }

    /**
     * @Description 上传图片
     * @Date 2019/1/13 21:48
     * @Param [tailoring]
     * @return java.lang.String
     **/
    public static String uploadAvatar(String picture, String fileName) {
        if (null == picture || "".equals(picture.trim())) return "null";
        int pictureSize = getPictureSize(picture);
        if (pictureSize > 1048576) {
            return "max";
        }
        String base64Date = getBase64(picture)[1];
        byte[] base64Decode = Base64Utils.decodeFromString(base64Date);
        for (int i = 0; i < base64Decode.length; i++) {
            if (base64Decode[i] < 0) {
                base64Decode[i] += 256;
            }
        }
        try (OutputStream out = new FileOutputStream(getResourcePath() + fileName)) {
            out.write(base64Decode);
            out.flush();
        } catch (IOException e) {
            LOG.error("Picture upload failed!", e);
        }
        return "success";
    }
}
