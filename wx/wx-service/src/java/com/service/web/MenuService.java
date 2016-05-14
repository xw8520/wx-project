package com.service.web;

import com.enums.MenuStatus;
import com.models.web.MenuInfo;
import com.models.web.MenuItem;

import java.util.List;
import java.util.Map;

/**
 * Created by TimLin on 2016/5/11.
 */
public interface MenuService {

    List<MenuItem> getMenuList();

    MenuInfo getMenuById(int id);

    List<MenuInfo> getMenuByPid(int pid);

    Map<String,Object> saveOrUpdate(MenuInfo info);

    Map<String,Object> deleteMenu(Integer id);
}
