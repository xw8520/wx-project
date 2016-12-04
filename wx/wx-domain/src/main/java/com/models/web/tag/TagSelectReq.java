package com.models.web.tag;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/4.
 */
public class TagSelectReq implements Serializable {
    private int accountId;
    private int domain;

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
