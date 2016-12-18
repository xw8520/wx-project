package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/18.
 */
public class DeleteMessageRecordReq implements Serializable {
    private int id;
    private boolean deWx;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeWx() {
        return deWx;
    }

    public void setDeWx(boolean deWx) {
        this.deWx = deWx;
    }
}
