package com.models.web;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public class DataListResp<T> implements Serializable {
    private List<T> list;
    private long total;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
