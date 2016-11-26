package com.models.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/4/7.
 */
public class MenuItem implements Serializable {
    public MenuItem() {
        child = new ArrayList<>();
    }

    public MenuItem(String name, String url) {
        this.name = name;
        this.url = url;
        child = new ArrayList<>();
    }

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
    /**
     * 子菜单
     */
    private List<MenuItem> child;

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

    public List<MenuItem> getChild() {
        return child;
    }

    public void setChild(List<MenuItem> child) {
        this.child = child;
    }
}
