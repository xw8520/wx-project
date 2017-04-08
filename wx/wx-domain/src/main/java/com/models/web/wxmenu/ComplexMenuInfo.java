package com.models.web.wxmenu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wq on 2017/4/8.
 */
public class ComplexMenuInfo implements Serializable {
    private String name;
    private List<WxMenuInfo> sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WxMenuInfo> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WxMenuInfo> sub_button) {
        this.sub_button = sub_button;
    }
}
