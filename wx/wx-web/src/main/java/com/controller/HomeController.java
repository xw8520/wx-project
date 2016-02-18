package com.controller;

import com.api.BlogService;
import com.domain.Blog;
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

    @Autowired
    BlogService blogService;

    @RequestMapping(value = {"index.html", "home/index.html"})
    public ModelAndView index() {
        Blog blog = blogService.selectBlog(2);
        ModelAndView view= new ModelAndView("home/index");
        view.addObject("blog",blog);
        return view;
    }
}
