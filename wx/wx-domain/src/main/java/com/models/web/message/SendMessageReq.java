package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/3.
 */
public class SendMessageReq implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
