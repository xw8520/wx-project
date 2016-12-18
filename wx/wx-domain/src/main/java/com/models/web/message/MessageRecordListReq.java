package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/18.
 */
public class MessageRecordListReq implements Serializable {
    private int accountId;
    private int stateId;
    private int tagId;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }
}
