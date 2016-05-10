package com.controller;


import com.models.web.LoginResp;
import com.service.web.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Admin on 2016/2/17.
 */
@Controller
public class HomeController {

    @Resource
    UsersService usersService;

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"index.html", "home/index.html"})
    public ModelAndView index() throws Exception {
        ModelAndView view = new ModelAndView("home/index");

        return view;
    }

    /**
     * 执行登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "home/login.action", method = RequestMethod.POST)
    public LoginResp login(@RequestParam("account") String account,
                                     @RequestParam("password") String password) {
        LoginResp resp=usersService.login(account,password);
        return resp;
    }
}
