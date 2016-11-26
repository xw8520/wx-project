package com.models.web;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/26.
 */
public class BaseResp implements Serializable {
    private Boolean success;
    private String data;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
