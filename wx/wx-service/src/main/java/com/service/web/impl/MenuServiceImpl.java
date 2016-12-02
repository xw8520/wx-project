package com.service.web.impl;

import com.data.MenuMapper;
import com.domain.web.Menu;
import com.domain.web.MenuExample;
import com.enums.MenuStatus;
import com.enums.MenuType;
import com.models.web.BaseResp;
import com.models.web.MenuInfo;
import com.models.web.MenuItem;
import com.service.web.inter.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by TimLin on 2016/5/11.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<MenuItem> getMenuList() {
        List<MenuItem> level1 = getChildMenu(0);
        List<MenuItem> level2 = null;
        for (MenuItem item : level1) {
            if (item.getType() == MenuType.Url.getValue()) continue;
            level2 = getChildMenu(item.getId());
            if (level2.isEmpty()) continue;
            item.setChild(level2);
        }

        return level1;
    }

    @Override
    public MenuInfo getMenuById(int id) {
        MenuInfo result = new MenuInfo();
        Menu menu = menuMapper.selectByPrimaryKey(id);
        if (menu == null) return result;
        result.setUrl(menu.getUrl());
        result.setType(menu.getType());
        result.setName(menu.getName());
        result.setId(menu.getId());
        result.setOrdernum(menu.getOrdernum());
        result.setPid(menu.getPid());
        result.setDomain(menu.getDomain());
        result.setStatus(menu.getStatus());

        return result;
    }

    @Override
    public List<MenuInfo> getMenuByPid(int pid) {
        MenuExample param = new MenuExample();
        param.createCriteria().andPidEqualTo(pid);
        List<Menu> list = menuMapper.selectByExample(param);
        List<MenuInfo> result = new ArrayList<>();
        MenuInfo newItem;
        for (Menu item : list) {
            newItem = new MenuInfo();
            newItem.setUrl(item.getUrl());
            newItem.setType(item.getType());
            newItem.setName(item.getName());
            newItem.setId(item.getId());
            newItem.setOrdernum(item.getOrdernum());
            newItem.setPid(item.getPid());
            newItem.setDomain(item.getDomain());
            newItem.setStatus(item.getStatus());
            result.add(newItem);
        }

        return result;
    }

    @Override
    public BaseResp saveOrUpdate(MenuInfo info) {
        BaseResp resp = new BaseResp();
        if (info == null) {
            resp.setInfo("请求参数为空");
            return resp;
        }
        Menu data = new Menu();
        data.setId(info.getId());
        data.setDomain(info.getDomain());
        data.setName(info.getName());
        data.setOrdernum(info.getOrdernum());
        data.setPid(info.getPid());
        data.setStatus(info.getStatus());
        data.setType(info.getType());
        data.setUrl(info.getUrl());
        data.setModifytime(new Date());
        try {
            if (info.getId() == 0) {
                data.setCreatetime(new Date());
                menuMapper.insert(data);
            } else {
                MenuExample exp = new MenuExample();
                exp.createCriteria().andIdEqualTo(info.getId());

                menuMapper.updateByExampleSelective(data, exp);
            }
            resp.setSuccess(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setInfo("系统出错");
        }
        return resp;
    }

    @Override
    public BaseResp deleteMenu(Integer id) {
        BaseResp resp = new BaseResp();
        MenuExample exp = new MenuExample();
        exp.createCriteria().andPidEqualTo(id);

        int childCount = menuMapper.countByExample(exp);

        if (childCount > 0) {
            resp.setInfo("请先移除子菜单");
            return resp;
        }
        Menu menu = new Menu();
        menu.setId(id);
        menu.setStatus(MenuStatus.刪除.getValue());
        menu.setModifytime(new Date());
        menuMapper.updateByPrimaryKeySelective(menu);
        resp.setSuccess(true);
        return resp;
    }

    private List<MenuItem> getChildMenu(int pid) {
        MenuExample param = new MenuExample();
        param.setOrderByClause("  ordernum desc");
        param.createCriteria()
                .andStatusGreaterThan(MenuStatus.刪除.getValue())
                .andPidEqualTo(pid);
        List<Menu> list = menuMapper.selectByExample(param);

        List<MenuItem> result = new ArrayList<>();
        MenuItem newItem = null;
        for (Menu item : list) {
            newItem = new MenuItem();
            newItem.setId(item.getId());
            newItem.setName(item.getName());
            newItem.setType(item.getType());
            newItem.setUrl(item.getUrl());
            result.add(newItem);
        }

        return result;
    }
}
