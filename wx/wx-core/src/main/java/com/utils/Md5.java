package com.utils;

import java.math.BigInteger;
import java.security.*;

/**
 * Created by TimLin on 2016/5/10.
 */
public class Md5 {
    /**
     * md5 Hash散列
     * @param source
     * @return
     */
    public static String md5(String source){
        if(StringUtils.isNullOrEmpty(source)) return  "";
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(source.getBytes());

            return new BigInteger(1,digest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
}
