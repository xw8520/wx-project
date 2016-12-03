package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/3.
 */
public class PreviewMessageReq implements Serializable {
    private int id;
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
