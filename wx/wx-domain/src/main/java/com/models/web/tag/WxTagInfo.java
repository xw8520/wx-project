package com.models.web.tag;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/30.
 */
public class WxTagInfo implements Serializable {
    private int id;
    private int wxTagId;
    private String name;
    private String remark;
    private int accountId;
    private String accountName;

    public WxTagInfo() {

    }

    public WxTagInfo(int id, int wxTagId, String name, String remark, int accountId) {
        this.id = id;
        this.wxTagId = wxTagId;
        this.name = name;
        this.remark = remark;
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWxTagId() {
        return wxTagId;
    }

    public void setWxTagId(int wxTagId) {
        this.wxTagId = wxTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
