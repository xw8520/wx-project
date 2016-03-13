package com.dto.wx;

import java.io.Serializable;

/**
 * Created by Admin on 2016/3/13.
 */
public class NewsMessageItemDto implements Serializable {
    public NewsMessageItemDto() {

    }

    public NewsMessageItemDto(String title, String desc, String picUrl, String url) {
        this.title = title;
        this.desc = desc;
        this.picUrl = picUrl;
        this.url = url;
    }

    private String title;
    private String desc;
    private String picUrl;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    public String getPicUrl() {
        return picUrl;
    }
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
