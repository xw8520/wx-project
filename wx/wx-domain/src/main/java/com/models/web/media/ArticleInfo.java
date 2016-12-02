package com.models.web.media;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/26.
 */
public class ArticleInfo implements Serializable {
    private int id;
    private String title;
    private String mediaid;
    private String remark;
    private int accountId;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private String account;

    public ArticleInfo() {

    }

    public ArticleInfo(int id, String title, String mediaid, String remark) {
        this.id = id;
        this.title = title;
        this.mediaid = mediaid;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
