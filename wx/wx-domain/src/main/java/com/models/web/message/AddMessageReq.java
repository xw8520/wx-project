package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/3.
 */
public class AddMessageReq implements Serializable {
    private int id;
    private int accountId;
    private int domain;
    private String title;
    private String mediaId;
    private int wxTagId;
    private boolean toall;
    private String remark;
    private short type;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isToall() {
        return toall;
    }

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

    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getWxTagId() {
        return wxTagId;
    }

    public void setWxTagId(int wxTagId) {
        this.wxTagId = wxTagId;
    }

    public boolean getToall() {
        return toall;
    }

    public void setToall(boolean toall) {
        this.toall = toall;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }
}
