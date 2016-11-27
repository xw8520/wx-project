package com.models.wx.message;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/11/27.
 */
public class VoiceMsgReq implements Serializable {
    private int tagId;
    private boolean toAll;
    private String mediaId;
    private int accountId;
    private List<String> openId;

    public List<String> getOpenId() {
        return openId;
    }

    public void setOpenId(List<String> openId) {
        this.openId = openId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public boolean isToAll() {
        return toAll;
    }

    public void setToAll(boolean toAll) {
        this.toAll = toAll;
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
