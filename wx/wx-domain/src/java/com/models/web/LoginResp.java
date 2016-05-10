package com.models.web;

import java.io.Serializable;

/**
 * Created by TimLin on 2016/5/10.
 */
public class LoginResp implements Serializable {
    private boolean success;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
