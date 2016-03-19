package com.domain.wx;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/3/13.
 * 素材
 */
public class WxMedia implements Serializable {
    private int id;
    private String title;
    private String remark;
    private int accountid;
    private Byte mediatype;
    private String filename;
    private Date createtime;
    private Boolean islong;
    private String mediaid;

    public WxMedia() {
    }

    public WxMedia(String title, String remark, int accountid, Byte mediatype,
                   String filename, String mediaid, Boolean islong) {
        this.title = title;
        this.remark = remark;
        this.accountid = accountid;
        this.mediatype = mediatype;
        this.mediaid = mediaid;
        this.islong = islong;
        this.filename = filename;
        createtime = new Date();
    }

    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Boolean getIslong() {
        return islong;
    }

    public void setIslong(Boolean islong) {
        this.islong = islong;
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

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public Byte getMediatype() {
        return mediatype;
    }

    public void setMediatype(Byte mediatype) {
        this.mediatype = mediatype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
