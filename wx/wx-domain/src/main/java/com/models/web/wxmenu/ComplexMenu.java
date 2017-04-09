package com.models.web.wxmenu;

import java.util.List;

/**
 * Created by wq on 2017/4/8.
 */
public class ComplexMenu extends BaseMenu {
    private String name;
    public List<BaseMenu> sub_button;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<BaseMenu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<BaseMenu> sub_button) {
        this.sub_button = sub_button;
    }
}
