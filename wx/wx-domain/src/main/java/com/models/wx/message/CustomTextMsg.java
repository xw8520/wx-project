package com.models.wx.message;

import java.io.Serializable;

/**
 * Created by Admin on 2016/5/18.
 */
public class CustomTextMsg implements Serializable {
    private String to;
    private String msg;
    private int accountid;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }
}
