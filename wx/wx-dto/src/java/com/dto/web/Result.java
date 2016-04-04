package com.dto.web;

import java.io.Serializable;

/**
 * Created by Admin on 2016/4/4.
 */
public class Result implements Serializable {
    private boolean success;
    private String info;
    private int objId;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private Object data;
}
