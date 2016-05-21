package com.controller;

import com.model.PagerParam;
import com.service.web.MediaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("media.html")
    public ModelAndView tmpMedia() {
        ModelAndView view = new ModelAndView("media/media");
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "getMediaList", method = RequestMethod.POST)
    public Map<String, Object> getMediaList(@ModelAttribute("data") PagerParam data) {
        Map<String, Object> map = mediaService.getMediaList(data.getPageSize(), data.getPageIndex(),
                false, data.getArgs());
        return map;
    }
}
