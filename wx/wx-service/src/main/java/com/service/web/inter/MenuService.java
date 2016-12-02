package com.service.web.inter;

import com.models.web.BaseResp;
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

    BaseResp saveOrUpdate(MenuInfo info);

    BaseResp deleteMenu(Integer id);
}
