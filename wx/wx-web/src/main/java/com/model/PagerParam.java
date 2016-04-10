package com.model;

import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

/**
 * Created by Admin on 2016/4/10.
 */
public class PagerParam implements Serializable {
    private int pageIndex;
    private int pageSize;
    private String args;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
