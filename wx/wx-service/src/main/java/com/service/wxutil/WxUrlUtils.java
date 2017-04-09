package com.service.wxutil;


import org.springframework.util.StringUtils;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Admin on 2016/2/21.
 */
public class WxUrlUtils {
    private static WxUrlUtils config;
    private static Properties props;

    private WxUrlUtils() {
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

    public static WxUrlUtils getInstance() {
        if (config == null) config = new WxUrlUtils();
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
    public String getSendByTagId() {
        return getValue("wx.sendall");
    }

    /**
     * 根据openid群发
     *
     * @return
     */
    public String getSendMassByOpenId() {
        return getValue("wx.sendmass");
    }

    /**
     * 群发预览
     *
     * @return
     */
    public String getPreview() {
        return getValue("wx.preview");
    }

    /**
     * 群发状态
     *
     * @return
     */
    public String getMassStatus() {
        return getValue("wx.massstatus");
    }

    /**
     * 删除群发
     *
     * @return
     */
    public String deleteMass() {
        return getValue("wx.deletemass");
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
     *
     * @return
     */
    public String getSendTextMsg() {
        return getValue("wx.send");
    }

    /**
     * 创建标签url
     *
     * @return
     */
    public String getCreateTag() {
        return getValue("wx.createtag");
    }

    /**
     * 编辑微信标签
     *
     * @return
     */
    public String updateTag() {
        return getValue("wx.updatetag");
    }

    public String getTagList(String token) {
        String url = getValue("wx.gettag");
        return String.format(url, token);
    }

    public String deleteTag(String token) {
        String url = getValue("wx.deletetag");
        return String.format(url, token);
    }

    public String getMaterial() {
        return getValue("wx.get_material");
    }

    public String getBatchtagging() {
        return getValue("wx.attatchtag");
    }

    public String getCreateWxMenu() {
        return getValue("wx.createmenu");
    }

    public String getMenu() {
        return getValue("wx.getmenu");
    }

    public String getAddconditional() {
        return getValue("wx.addconditional");
    }
}
