package com.models.web.message;


/**
 * Created by admin on 2016/12/18.
 */
public class SendByOpenIdInfo extends SendByTagIdInfo {
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
