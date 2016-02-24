package com.controller;


import com.service.AccessTokenService;
import com.service.WxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Admin on 2016/2/17.
 */
@Controller
public class HomeController {

    @Resource
    AccessTokenService accessTokenService;

    @Autowired
    WxMessageService wxMessageService;

    @RequestMapping(value = {"index.html", "home/index.html"})
    public ModelAndView index() throws Exception {
        String code = accessTokenService.getAccessToken(1).getAccess_token();
//        String code = wxMessageService.getQrCode("1123", 10000, 1);
        ModelAndView view = new ModelAndView("home/index");
        view.addObject("code", code);
        return view;
    }
}
