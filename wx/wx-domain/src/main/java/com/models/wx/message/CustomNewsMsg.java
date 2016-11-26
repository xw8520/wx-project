package com.models.wx.message;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2016/5/19.
 */
public class CustomNewsMsg implements Serializable {
    private String to;
    private List<CustomNewsMsgItem> list;
    private int accountid;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<CustomNewsMsgItem> getList() {
        return list;
    }

    public void setList(List<CustomNewsMsgItem> list) {
        this.list = list;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }
}
