package com.wxconfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Admin on 2016/2/21.
 */
public class WxConfig {
    public static String getWxConfig(String key) throws IOException {

        Properties config = new Properties();
        InputStreamReader streamReader = new InputStreamReader(
                ClassLoader.getSystemResourceAsStream("wxconfig.properties"), "utf-8");
        config.load(streamReader);
        Object obj = config.get(key);
        if (obj != null && obj != "") {
            return obj.toString();
        }
        return "";
    }
}
