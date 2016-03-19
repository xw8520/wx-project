package com.wxconfig;


import org.springframework.util.StringUtils;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Admin on 2016/2/21.
 */
public class WxConfig {
    private static WxConfig config;
    private static Properties props;

    private WxConfig() {
        try {
            props = new Properties();
            InputStreamReader streamReader = new InputStreamReader(
                    this.getClass().getClassLoader()
                            .getResourceAsStream("wxconfig.properties"), "utf-8");
            props.load(streamReader);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static WxConfig getInstance() {
        if (config == null) config = new WxConfig();
        return config;
    }

    private String getValue(String key) {
        Object o = props.getProperty(key);
        if (!StringUtils.isEmpty(o)) {
            return o.toString();
        }
        return "";
    }

    public String getAccesstoken() {

        return getValue("wx.accesstoken");
    }

    public String getWxserveripservice() {

        return getValue("wx.wxserveripservice");
    }

    public String getQrticket() {

        return getValue("wx.qrticket");
    }

    public String getQrcode() {
        return getValue("wx.qrcode");
    }

    public String getJsapiticket() {
        return getValue("wx.jsapiticket");
    }

    public String getUserInfo() {
        return getValue("wx.userinfo");
    }

    public String getUserinfoBatch() {
        return getValue("wx.batchgetuserinfo");
    }

    /**
     * 临时素材上传接口
     *
     * @return
     */
    public String getTmpMediaUpload() {
        return getValue("wx.uploadtmpmedia");
    }

    /**
     * 获取临时素材
     * @return
     */
    public String getTmpmedia() {
        return getValue("wx.gettmpmedia");
    }
}
