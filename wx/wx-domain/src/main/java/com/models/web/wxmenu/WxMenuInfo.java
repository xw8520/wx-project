package com.models.web.wxmenu;

import java.io.Serializable;

/**
 * Created by wq on 2017/4/8.
 */
public class WxMenuInfo implements Serializable {
    private String type;
    private String name;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
