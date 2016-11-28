package com.models.wx.tag;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/11/28.
 */
public class BatchTaggingReq implements Serializable {
    private List<String> openId;
    private int accountId;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    private int tagId;

    public List<String> getOpenId() {
        return openId;
    }

    public void setOpenId(List<String> openId) {
        this.openId = openId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
