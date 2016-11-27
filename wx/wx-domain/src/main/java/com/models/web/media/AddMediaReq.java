package com.models.web.media;

import java.io.Serializable;

/**
 * Created by Admin on 2016/5/22.
 */
public class AddMediaReq implements Serializable {
    private String title;
    private String remark;
    private int accountid;
    private String filename;
    private Boolean permanent;

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * 0-图片，1-语音，2-视频，3-缩略图，4-未知
     */
    private int mediaType;

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
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


    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
