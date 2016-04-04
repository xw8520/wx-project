package com.controller;


import com.dto.web.Result;
import com.dto.wx.UserInfoResp;
import com.service.AccessTokenService;
import com.service.WxMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/2/17.
 */
@Controller
public class HomeController {

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
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String password) {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
}
