package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/3.
 */
public class DelMessageReq implements Serializable {
    private int accountId;
    private int id;
    private boolean delWx;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDelWx() {
        return delWx;
    }

    public void setDelWx(boolean delWx) {
        this.delWx = delWx;
    }
}
