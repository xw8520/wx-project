package com.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by TimLin on 2016/1/6.
 */
public class Blog implements Serializable {
    private  int id;
    private String title;
    private String content;
    private Date createTime;
    private String Remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
