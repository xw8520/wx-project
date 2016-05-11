package com.models.web;


import java.io.Serializable;

/**
 * Created by TimLin on 2016/5/11.
 */
public class MenuInfo implements Serializable {
    private int id;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 链接
     */
    private String url;
    /**
     * 菜单类型
     */
    private byte type;

    private int pid;
    private int domain;
    private byte status;
    private int ordenum;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain) {
        this.domain = domain;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getOrdenum() {
        return ordenum;
    }

    public void setOrdenum(int ordenum) {
        this.ordenum = ordenum;
    }
}
