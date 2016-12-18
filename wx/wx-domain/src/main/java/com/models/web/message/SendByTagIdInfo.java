package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/18.
 */
public class SendByTagIdInfo implements Serializable {
    private int id;
    private int mid;
    private int accountId;
    private String accountName;
    private String title;
    private String remark;
    private int tagId;
    private boolean toall;

    public boolean getToall() {
        return toall;
    }

    public void setToall(boolean toall) {
        this.toall = toall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
