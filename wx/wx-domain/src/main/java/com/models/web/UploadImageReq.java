package com.models.web;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/26.
 */
public class UploadImageReq implements Serializable {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
