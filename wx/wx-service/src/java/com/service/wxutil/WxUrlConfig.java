package com.service.wxutil;


import org.springframework.util.StringUtils;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Admin on 2016/2/21.
 */
public class WxUrlConfig {
    private static WxUrlConfig config;
    private static Properties props;

    private WxUrlConfig() {
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

    public static WxUrlConfig getInstance() {
        if (config == null) config = new WxUrlConfig();
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
     *
     * @return
     */
    public String getTmpmedia() {
        return getValue("wx.gettmpmedia");
    }

    /**
     * 获取视频
     *
     * @return
     */
    public String getTmpVedio() {
        return getValue("wx.gettmpvedio");
    }

    /**
     * 图文消息上传图片
     *
     * @return
     */
    public String getUploadimg() {
        return getValue("wx.uploadimg");
    }

    /**
     * 上传群发图文消息
     *
     * @return
     */
    public String getUploadNews() {
        return getValue("wx.uploadnews");
    }

    /**
     * 获取微信群发接口
     *
     * @return
     */
    public String getSendall() {
        return getValue("wx.sendall");
    }

    /**
     * 上传永久素材
     *
     * @return
     */
    public String getAddmaterial() {
        return getValue("wx.addmaterial");
    }

    /**
     * 微信客服发送消息接口
     * @return
     */
    public String getSendTextMsg(){
        return getValue("wx.send");
    }
}
