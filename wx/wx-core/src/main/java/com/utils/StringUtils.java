package com.utils;

/**
 * Created by Admin on 2016/3/16.
 */
public class StringUtils {
    /**
     * 判断对象是否为null
     *
     * @param obj
     * @return
     */
    public static Boolean isNullOrEmpty(Object obj) {
        if (obj == null) return true;
        if ("".equals(obj.toString())) return true;
        return false;
    }
}
