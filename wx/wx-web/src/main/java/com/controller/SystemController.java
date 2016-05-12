package com.controller;

import com.service.web.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Admin on 2016/5/12.
 */
@Controller
@RequestMapping("/sys")
public class SystemController {
    @Resource
    MenuService menuService;

    @RequestMapping("/menu.html")
    public ModelAndView menu() {
        ModelAndView view = new ModelAndView("sys/menu");

        return view;
    }

    @ResponseBody
    @RequestMapping("/getMenuList")
    public Map<String, Object> getMenuList() {
        Map<String, Object> list = null;
        return list;
    }
}
