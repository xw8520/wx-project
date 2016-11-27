package com.models.wx.media;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/27.
 */
public class AddMaterialReq implements Serializable {
    private String path;
    private int accountId;
    private int domain;
    private String title;
    private String remark;
    /**
     * 0-图片，1-语音，2-视频，3-缩略图，4-未知
     */
    private int type;

    public AddMaterialReq() {

    }

    public AddMaterialReq(String path, int accountId, int domain, String title, String remark, int type) {
        this.path = path;
        this.accountId = accountId;
        this.domain = domain;
        this.title = title;
        this.remark = remark;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
