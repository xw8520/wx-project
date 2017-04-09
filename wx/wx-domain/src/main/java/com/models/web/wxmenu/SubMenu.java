package com.models.web.wxmenu;

import java.util.List;

/**
 * Created by wq on 2017/4/8.
 */
public class SubMenu extends BaseMenu{
    private List<SubMenu> sub_button;

    public List<SubMenu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<SubMenu> sub_button) {
        this.sub_button = sub_button;
    }
}
