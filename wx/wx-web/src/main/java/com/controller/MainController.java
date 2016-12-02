package com.controller;

import com.models.web.MenuItem;
import com.service.web.inter.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Admin on 2016/4/4.
 */
@Controller
@RequestMapping("main")
public class MainController {

    @Resource
    MenuService menuService;

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping(value = "index.html")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("main/index");
        List<MenuItem> list =menuService.getMenuList();
        view.addObject("menu", list);
        return view;
    }
}
