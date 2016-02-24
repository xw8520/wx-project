package com.wxconfig;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Admin on 2016/2/21.
 */
public class WxConfig {
    private static WxConfig config;

    private WxConfig() {
    }

    public static WxConfig getInstance() {
        if (config == null) config = new WxConfig();
        return config;
    }

    public String getWxConfig(String key) throws IOException {
        Properties props = new Properties();
        InputStreamReader streamReader = new InputStreamReader(
                this.getClass().getClassLoader()
                        .getResourceAsStream("wxconfig.properties"), "utf-8");
        props.load(streamReader);
        Object obj = props.getProperty(key);
        if (obj != null && obj != "") {
            return obj.toString();
        }
        return "";
    }
}
