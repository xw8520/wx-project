package com.models.wx.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/27.
 */
public class PreviewVoiceReq implements Serializable {
    private String openId;
    private String mediaId;
    private int accountId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
