package com.models.web.media;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/26.
 */
public class ImageMsgInfo implements Serializable {
    private int id;
    private String title;
    private String remark;
    private String url;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ImageMsgInfo() {

    }

    public ImageMsgInfo(int id, String title, String remark, String url) {
        this.id = id;
        this.title = title;
        this.remark = remark;
        this.url = url;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
