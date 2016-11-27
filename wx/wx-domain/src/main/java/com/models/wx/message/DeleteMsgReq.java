package com.models.wx.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/27.
 */
public class DeleteMsgReq implements Serializable {
    private int accountId;
    private String msgId;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
