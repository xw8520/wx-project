package com.models.web;

import java.io.Serializable;

/**
 * Created by Admin on 2016/5/15.
 */
public class UserInfo implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int domain;
    private String name;

    public UserInfo(){}

    public UserInfo(int id, int domain, String name) {
        this.id = id;
        this.domain = domain;
        this.name = name;
    }

    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
