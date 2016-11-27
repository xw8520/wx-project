package com.models.wx.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/27.
 */
public class PreviewTextReq implements Serializable{
    private String openId;
    private String conent;
    private int accountId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
