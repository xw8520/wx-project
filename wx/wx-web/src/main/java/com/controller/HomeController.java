package com.controller;


import com.service.AccessTokenService;
import com.service.WxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
        String code = wxMessageService.getQrCode("100",0, 1);
        ModelAndView view = new ModelAndView("home/index");
        view.addObject("code", code);
        return view;
    }

    @RequestMapping(value = "share.html", method = RequestMethod.GET)
    public ModelAndView share(HttpServletRequest req) throws Exception {
        ModelAndView view = new ModelAndView("home/share");
        String url = req.getRequestURL().toString();
        String uri = req.getRequestURI();
        String query = req.getQueryString();
        url = StringUtils.isEmpty(query) ? url : url + "?" + query;
        Map<String, String> sign = wxMessageService.getJsapiSignPackage(1, url);
        sign.put("url", url.replace(uri, ""));
        sign.put("all", url);
        view.addObject("sign", sign);
        return view;
    }
}
