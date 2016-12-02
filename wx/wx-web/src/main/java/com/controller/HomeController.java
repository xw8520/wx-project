package com.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.web.LoginResp;
import com.models.web.UserInfo;
import com.service.web.inter.UsersService;
import com.utils.CookieUtil;
import com.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                           @RequestParam("password") String password,
                           HttpServletRequest req,
                           HttpServletResponse resp) {
        LoginResp loginResp = usersService.login(account, password);
        try {
            if (loginResp.getSuccess()) {
                UserInfo info = new UserInfo(loginResp.getId(), loginResp.getDomain(), loginResp.getName());
                CookieUtil.setCookie(req, resp, "u", JsonUtils.Serialize(info));
                CookieUtil.setCookie(req, resp, "d", String.valueOf(info.getDomain()));
                loginResp.setId(0);
                loginResp.setDomain(0);
                loginResp.setName("");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return loginResp;
    }
}
