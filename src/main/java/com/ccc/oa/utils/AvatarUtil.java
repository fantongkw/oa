package com.ccc.oa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
/***
 * @ClassName: AvatarUtil
 * @Author: Administrator
 * @Description: 根据用户名生成随机头像
 * @Date: 2019/1/28 20:43
 * @Version: 1.0
 **/
public class AvatarUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
    private static final String AVATAR_FORMAT = "png";
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 1024;

    /**
     * @Description 初始化头像数据
     * @Date 2019/1/28 17:27
     * @Param [content, font, wight, height]
     * @return java.awt.image.BufferedImage
     **/

    private static BufferedImage createImage(String content, Font font, Integer wight, Integer height) {
        BufferedImage bufferedImage = new BufferedImage(wight, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setBackground(getRandomColor());
        graphics2D.clearRect(0, 0, WIDTH, HEIGHT);
        graphics2D.setFont(font);
        graphics2D.setPaint(Color.WHITE);

        FontRenderContext context = graphics2D.getFontRenderContext();
        Rectangle2D rectangle2D = font.getStringBounds(content, context);
        double x = (wight - rectangle2D.getWidth()) / 2;
        double y = (height - rectangle2D.getHeight()) / 2;
        double ascent = -rectangle2D.getY();
        double baseY = y + ascent;
        graphics2D.drawString(content, (int)x, (int)baseY);
        return bufferedImage;
    }

    /**
     * @Description 自定义头像数据
     * @Date 2019/1/28 17:27
     * @Param [content, font, wight, height]
     * @return java.awt.image.BufferedImage
     **/

    private static BufferedImage getImage(String content, Font font, Integer wight, Integer height) {
        wight = wight == null ? WIDTH : wight;
        height = height == null ? HEIGHT : height;
        if (null == font) {
            font = new Font("微软雅黑", Font.PLAIN, 640);
        }
        return createImage(content, font, wight, height);
    }

    /**
     * @Description 生成头像
     * @Date 2019/1/28 17:27
     * @Param [content, font, wight, height, path]
     * @return void
     **/

    private static String generateImage(String content, Font font, Integer wight, Integer height, String path) {
        String file = FileUtil.uuid() + "." + AVATAR_FORMAT;
        try {
            ImageIO.write(getImage(content, font, wight, height), AVATAR_FORMAT, new File(path + file));
        } catch (IOException e) {
            LOG.error("Failed to generate tailoring!");
        }
        return file;
    }

    /**
     * @Description 生成头像
     * @Date 2019/1/28 17:27
     * @Param [content, path]
     * @return void
     **/

    private static String generateImage(String content, String path) {
        return generateImage(getInitials(content), null, null, null, path);
    }

    /**
     * @Description 获取头像路径
     * @Date 2019/1/28 17:42
     * @Param [content, font, wight, height]
     * @return java.lang.String
     **/

    public static String getAvatarPath(@NonNull String content, Font font, Integer wight, Integer height) {
        String path = FileUtil.getResourcePath();
        String fileName = generateImage(getInitials(content), font, wight, height,path);
        return FileUtil.BASE_PATH + fileName;
    }

    /**
     * @Description 获取头像路径
     * @Date 2019/1/28 17:27
     * @Param [content]
     * @return java.lang.String
     **/

    public static String getAvatarPath(@NonNull String content) {
        String path = FileUtil.getResourcePath();
        String fileName = generateImage(getInitials(content), path);
        return FileUtil.BASE_PATH + fileName;
    }

    /**
     * @Description 获取首字母并大写
     * @Date 2019/1/28 17:27
     * @Param [name]
     * @return java.lang.String
     **/

    public static String getInitials(@NonNull String name) {
        return name.substring(0,1).toUpperCase();
    }

    /**
     * @Description 获取随机颜色
     * @Date 2019/1/28 17:28
     * @Param []
     * @return java.awt.Color
     **/

    private static Color getRandomColor(){
        String[] RandomColor = new String[] {
                "230,184,175", "244,204,204", "252,229,205", "255,242,204", "217,234,211",
                "208,224,227", "201,218,248", "207,226,243", "217,210,233", "234,209,220",
                "221,126,107", "234,153,153", "249,203,156", "255,229,153", "182,215,168",
                "162,196,201", "164,194,244", "159,197,232", "180,167,214", "213,166,189",
                "204,65,37", "224,102,102", "246,178,107", "255,217,102", "147,196,125",
                "118,165,175", "109,158,235", "111,168,220", "142,124,195", "194,123,160",
                "166,28,0", "204,0,0", "230,145,56", "241,194,50", "106,168,79",
                "69,129,142", "60,120,216", "61,133,198", "103,78,167", "166,77,121",
                "133,32,12", "153,0,0", "180,95,6", "191,144,0", "56,118,29",
                "19,79,92", "17,85,204", "11,83,148", "53,28,117", "116,27,71",
                "91,15,0", "102,0,0", "120,63,4", "127,96,0", "39,78,19",
                "12,52,61", "28,69,135", "7,55,99", "32,18,77", "76,17,48"
        };
        int length = RandomColor.length;
        Random random = new Random();
        String[] color = RandomColor[random.nextInt(length)].split(",");
        return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]));
    }
}
