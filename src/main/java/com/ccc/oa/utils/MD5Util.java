package com.ccc.oa.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: MD5Util
 * @Author: Administrator
 * @Description: MD5加密工具类
 * @Date: 2019/4/2 11:07
 * @Version: 1.0
 **/
public class MD5Util extends DigestUtils {

    private static final String SALT = "m";

    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * @Description 返回加密后的字符串
     * @Date 2019/4/2 11:09
     * @Param [password]
     * @return java.lang.String
     **/
    public static String encode(String password) {
        password = (password + SALT);
        return digestAsHexString(password.getBytes());
    }
    
    /**
     * @Description 返回加密后的字符数组
     * @Date 2019/4/2 11:13 
     * @Param [bytes]
     * @return char[]
     **/
    private static char[] digestAsHexChars(byte[] bytes) {
        return encodeHex(bytes);
    }

    /**
     * @Description 返回加密后的字符串
     * @Date 2019/4/2 11:15
     * @Param [bytes]
     * @return java.lang.String
     **/
    private static String digestAsHexString(byte[] bytes) {
        char[] hexDigest = digestAsHexChars(bytes);
        return new String(hexDigest);
    }

    /**
     * @Description 通过散列算法加密
     * @Date 2019/4/2 11:15
     * @Param [bytes]
     * @return char[]
     **/
    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];

        for(int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }

        return chars;
    }
}
