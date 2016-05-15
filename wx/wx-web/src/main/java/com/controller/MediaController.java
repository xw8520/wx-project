package com.controller;

import com.model.PagerParam;
import com.service.web.MediaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/5/15.
 */
@RequestMapping("media")
@Controller
public class MediaController {

    @Resource
    MediaService mediaService;

    @RequestMapping("tmedia.thml")
    public ModelAndView tmpMedia() {
        ModelAndView view = new ModelAndView("/media/tmedia");
        return view;
    }

    @RequestMapping(value = "getTMeidaList", method = RequestMethod.POST)
    public Map<String, Object> getTMeidaList(@ModelAttribute("data") PagerParam data) {
        Map<String, Object> map = mediaService.getMediaList(data.getPageSize(), data.getPageIndex(),
                false, data.getArgs());
        return map;
    }
}
