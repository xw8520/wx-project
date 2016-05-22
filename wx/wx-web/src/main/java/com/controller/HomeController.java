package com.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.web.UserInfo;
import com.service.web.UsersService;
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
    public Map<String, Object> login(@RequestParam("account") String account,
                                     @RequestParam("password") String password,
                                     HttpServletRequest req,
                                     HttpServletResponse resp) {
        //
        Map<String, Object> map = usersService.login(account, password);
        try {
            if (Boolean.valueOf(map.get("success").toString())) {
                UserInfo info = new UserInfo(Integer.valueOf(map.get("id").toString()),
                        Integer.valueOf(map.get("domain").toString()),
                        map.get("name").toString());
                CookieUtil.setCookie(req, resp, "u", JsonUtils.Serialize(info));
                map.remove("id");
                map.remove("name");
                map.remove("domain");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
}
