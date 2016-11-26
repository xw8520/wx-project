package com.models.web.media;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public class DeleteArticleReq implements Serializable {
    private List<Integer> data;
    private boolean deleteWx;

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public boolean isDeleteWx() {
        return deleteWx;
    }

    public void setDeleteWx(boolean deleteWx) {
        this.deleteWx = deleteWx;
    }
}
