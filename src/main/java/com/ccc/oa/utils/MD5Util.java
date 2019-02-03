package com.ccc.oa.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5Util extends DigestUtils {
    private static final String SALT = "";
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static byte[] digest(byte[] bytes) {
        return getDigest().digest(bytes);
    }

    private static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + "MD5" + "\"", var2);
        }
    }

    public static String encode(String password) {
        password = (password + SALT);
        return digestAsHexString(password.getBytes());
    }

    private static char[] digestAsHexChars(byte[] bytes) {
        byte[] digest = digest(bytes);
        return encodeHex(digest);
    }

    private static String digestAsHexString(byte[] bytes) {
        char[] hexDigest = digestAsHexChars(bytes);
        return new String(hexDigest);
    }

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
