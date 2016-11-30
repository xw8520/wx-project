package com.models.web.tag;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/30.
 */
public class DeleteTagReq implements Serializable {
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
