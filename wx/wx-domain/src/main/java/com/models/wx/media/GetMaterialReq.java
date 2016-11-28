package com.models.wx.media;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/28.
 */
public class GetMaterialReq implements Serializable {
    private String mediaId;
    private int accountId;

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
