package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/3.
 */
public class SyncSendStateReq implements Serializable{
    private int id;
    private int accountId;
    private int domain;

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

    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain) {
        this.domain = domain;
    }
}
