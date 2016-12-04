package com.utils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by admin on 2016/12/4.
 */
public class EnumUtils {
    private EnumUtils() {

    }

    /**
     * 枚举转换成map
     *
     * @param t
     * @param def 是否添加默认
     * @param <T>
     * @return
     */
    public static <T> List<KeyVal> getEnumMap(Class<T> t, boolean def) {
        List<KeyVal> list = new ArrayList<>();
        T[] ts = t.getEnumConstants();
        try {
            if (def) {
                list.add(new KeyVal(-1,"全部"));
            }
            Method getVal = t.getMethod("getValue");
            Method getName = t.getMethod("getName");
            Integer iVal;
            for (T item : ts) {
                Object val = getVal.invoke(item);
                iVal = Integer.valueOf(val.toString());
                if (iVal <= 0) continue;
                Object name = getName.invoke(item);
                list.add(new KeyVal(iVal,name.toString()));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static class KeyVal {
        private int id;
        private String name;
        public KeyVal(){

        }

        public KeyVal(int val, String name) {
            this.id = val;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
