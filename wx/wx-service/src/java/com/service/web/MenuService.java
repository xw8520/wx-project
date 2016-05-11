package com.service.web;

import com.models.web.MenuInfo;
import com.models.web.MenuItem;

import java.util.List;

/**
 * Created by TimLin on 2016/5/11.
 */
public interface MenuService {

    List<MenuItem> getMenuList();

    MenuInfo getMenuById(int id);

    List<MenuInfo> getMenuByPid(int pid);
}
