package com.utils;


/**
 * Created by Admin on 2016/2/21.
 */
public enum AcceptTypeEnum {
    json("application/json"),
    xml("application/xml");
    private String type;

    AcceptTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
