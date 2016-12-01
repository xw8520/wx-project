package com.models.wx.tag;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/1.
 */
public class WxDeleteTagReq implements Serializable {
    private int id;
    private int accountId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
