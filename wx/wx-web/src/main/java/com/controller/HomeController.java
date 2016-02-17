package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Admin on 2016/2/17.
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"index.html","home/index.html"})
    public ModelAndView index() {
        return new ModelAndView("home/index");
    }
}
