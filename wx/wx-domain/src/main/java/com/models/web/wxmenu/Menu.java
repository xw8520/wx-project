package com.models.web.wxmenu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wq on 2017/4/8.
 */
public class Menu implements Serializable {
    private List<SubMenu> button;

    public List<SubMenu> getButton() {
        return button;
    }

    public void setButton(List<SubMenu> button) {
        this.button = button;
    }
}
