package com.controller;

import com.service.web.inter.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by admin on 2016/12/2.
 */
@Controller
@RequestMapping("message")
public class MessageController {
    @Resource
    MessageService messageService;

    @RequestMapping("index.html")
    public ModelAndView index() {
        return new ModelAndView("message/index");
    }
}
