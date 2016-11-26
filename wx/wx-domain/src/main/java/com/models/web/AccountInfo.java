package com.models.web;

import com.enums.AccountType;

import java.io.Serializable;

/**
 * Created by Admin on 2016/4/10.
 */
public class AccountInfo implements Serializable {
    private int id;
    private String name;
    private String appid;
    private String secret;

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    private AccountType type;

    public AccountInfo() {
    }

    public AccountInfo(int id, String name, String appid, String secret, AccountType type) {
        this.id = id;
        this.name = name;
        this.appid = appid;
        this.secret = secret;
        this.type = type;
    }

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
}
