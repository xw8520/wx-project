package com.models.wx.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/27.
 */
public class SendStatusReq  implements Serializable {
   private String msgId;
    private int accountId;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
