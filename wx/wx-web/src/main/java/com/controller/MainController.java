package com.controller;

import com.dto.web.MenuItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/4/4.
 */
@Controller
@RequestMapping("main")
public class MainController {
    /**
     * 主页
     *
     * @return
     */
    @RequestMapping(value = "index.html")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("main/index");
        List<MenuItem> list = new ArrayList<>();
        MenuItem item;
        for (int i = 0; i < 5; i++) {
            item = new MenuItem("name" + i, "url" + i);
            list.add(item);
        }
        for (int i = 0; i < 3; i++) {
            item = new MenuItem("name" + i, "/account/index.html");
            list.get(0).getChild().add(item);
        }
        view.addObject("menu", list);
        return view;
    }
}
