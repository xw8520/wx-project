package com.models.web;

/**
 * Created by Admin on 2016/3/13.
 * 文件上传返回报文
 */
public class UploadFileResp {
    private Boolean success;
    private String errorMsg;
    private String data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
