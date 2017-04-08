package com.models.web.wxmenu;

import java.io.Serializable;

/**
 * Created by wq on 2017/4/8.
 */
public class BaseMenu implements Serializable {
    private String name;
    private String url;

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
