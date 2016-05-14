package com.controller;

import com.models.web.MenuInfo;
import com.models.web.MenuItem;
import com.service.web.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/5/12.
 */
@Controller
@RequestMapping("/sys")
public class SystemController {
    @Resource
    MenuService menuService;

    @RequestMapping("menu.html")
    public ModelAndView menu() {
        ModelAndView view = new ModelAndView("sys/menu");
        List<MenuItem> list = menuService.getMenuList();
        view.addObject("menu", list);
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "getMenuById", method = RequestMethod.POST)
    public MenuInfo getMenuById(@RequestParam("id") int id) {
        MenuInfo info = menuService.getMenuById(id);
        return info;
    }

    @ResponseBody
    @RequestMapping(value = "addMenu", method = RequestMethod.POST)
    public Map<String, Object> addMenu(@ModelAttribute("data") MenuInfo data) {
        Map<String, Object> map = menuService.saveOrUpdate(data);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "deleteMenu", method = RequestMethod.POST)
    public Map<String, Object> deleteMenu(@RequestParam("id") int id) {
        Map<String, Object> map = menuService.deleteMenu(id);
        return map;
    }
}
