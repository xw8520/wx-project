package com.models.web;


import java.io.Serializable;

/**
 * Created by TimLin on 2016/5/10.
 */
public class LoginResp extends BaseResp implements Serializable {
    private int domain;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

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
