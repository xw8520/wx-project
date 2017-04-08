package com.models.web.wxmenu;

import java.util.List;

/**
 * Created by wq on 2017/4/8.
 */
public class ComplexMenu extends BaseMenu {
    public List<BaseMenu> subMenu;

    public List<BaseMenu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<BaseMenu> subMenu) {
        this.subMenu = subMenu;
    }
}
