package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Admin on 2016/4/4.
 */
@Controller
public class MainController {
    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "main/index.html")
    public ModelAndView index(){
        ModelAndView view=new ModelAndView("main/index");

        return view;
    }
}
