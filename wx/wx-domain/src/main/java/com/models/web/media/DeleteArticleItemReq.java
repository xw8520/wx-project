package com.models.web.media;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/11/27.
 */
public class DeleteArticleItemReq implements Serializable {
    private int aid;
    private List<Integer> data;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
