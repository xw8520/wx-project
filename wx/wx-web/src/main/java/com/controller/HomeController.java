package com.controller;


import com.dto.token.TokenDto;
import com.service.AccessTokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Admin on 2016/2/17.
 */
@Controller
public class HomeController {

    @Resource
    AccessTokenService accessTokenService;

    @RequestMapping(value = {"index.html", "home/index.html"})
    public ModelAndView index() throws IOException {
        TokenDto dto = accessTokenService.getAccessToken(1);
        ModelAndView view = new ModelAndView("home/index");
        return view;
    }
}
