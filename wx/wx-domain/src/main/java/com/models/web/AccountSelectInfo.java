package com.models.web;

import java.io.Serializable;

/**
 * Created by Admin on 2016/5/22.
 */
public class AccountSelectInfo implements Serializable {
    private int id;
    private String name;

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
}
