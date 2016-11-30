package com.models.wx.tag;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/30.
 */
public class UpdateTagReq implements Serializable {
    private int tagId;
    private String name;
    private int accountId;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
