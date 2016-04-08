package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Admin on 2016/4/8.
 */
@Controller
@RequestMapping("account")
public class AccountController {
    @RequestMapping(value = "index.html")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("account/index");
        return view;
    }
}
