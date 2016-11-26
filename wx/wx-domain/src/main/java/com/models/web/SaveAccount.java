package com.models.web;

import java.io.Serializable;

/**
 * Created by Admin on 2016/4/10.
 */
public class SaveAccount implements Serializable {
    private int id;
    private String name;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    /**
     * 0-订阅号，1-服务号
     */
    private byte type;
    private String appid;
    private String secret;

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRemark() {
        return remark;
    }
}
