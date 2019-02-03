package com.ccc.oa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

/**
 * @ClassName: FileUtil
 * @Author: Administrator
 * @Description: 获取路径工具
 * @Date: 2019/1/3 20:43
 * @Version: 1.0
 **/

public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    /**
     * @Description 创建文件
     * @Date 2019/1/13 21:42 
     * @Param [path]
     * @return void
     **/
    
    private static void creatFile(String path) {
        File filePath = new File(path);
        boolean isDirectoryCreated = filePath.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = filePath.mkdirs();
            if (!isDirectoryCreated) {
                LOG.error("Failed to create file!");
            }
        }
    }

    /**
     * @Description 获取文件路径
     * @Date 2019/1/13 21:43 
     * @Param []
     * @return java.lang.String
     **/
    
    private static String getFilePath() {
        String path = "";
        if (ClassUtils.getDefaultClassLoader() != null) {
            URL filePath = ClassUtils.getDefaultClassLoader().getResource("static/images/upload");
            if (filePath != null) {
                path = filePath.getPath();
            }
        }
        creatFile(path);
        return path;
    }

    /**
     * @Description 上传文件
     * @Date 2019/1/13 21:48
     * @Param [file]
     * @return java.lang.String
     **/
    
    public static String uploadFile(MultipartFile file) {
        try {
            if (file.getSize() > 1048576) {
                return "文件太大";
            }
            if (file.isEmpty()) {
                return "文件为空";
            }
            String fileName = file.getOriginalFilename();
            String newName = null;
            if (fileName != null) {
                newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            }
            String uploadDir = getFilePath();
            File newFile = new File(uploadDir + newName);
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件上传成功";
    }
}
