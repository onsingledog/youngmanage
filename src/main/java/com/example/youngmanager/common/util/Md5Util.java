package com.example.youngmanager.common.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2018/6/13.
 */
public class Md5Util {

    public static String md5(String str){
        try{
            if(StringUtils.isBlank(str))
                return null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes("UTF-8"));
            return toHex(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

}
