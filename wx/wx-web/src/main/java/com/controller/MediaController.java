package com.controller;

import com.model.PagerParam;
import com.models.web.AccountSelectInfo;
import com.models.web.SaveMediaInfo;
import com.models.web.UserInfo;
import com.service.web.AccountService;
import com.service.web.MediaService;
import com.utils.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
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
    @Resource
    AccountService accountService;

    @RequestMapping("media.html")
    public ModelAndView media() {
        ModelAndView view = new ModelAndView("media/media");
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "getMediaList", method = RequestMethod.POST)
    public Map<String, Object> getMediaList(PagerParam data) {
        Map<String, Object> map = mediaService.getMediaList(data.getPageSize(), data.getPageIndex(),
                data.getArgs());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "addMedia", method = RequestMethod.POST)
    public Map<String, Object> addMedia(SaveMediaInfo data,
                                        HttpServletRequest req) {
        UserInfo user = CookieUtil.GetCurrentUser(req);
        String path = req.getServletContext().getRealPath("/upload");
        data.setFilename(path + File.separator + data.getFilename());
        return mediaService.addMedia(data, user);
    }

    @ResponseBody
    @RequestMapping(value = "getAccountSelect", method = RequestMethod.POST)
    public List<AccountSelectInfo> getAccountSelect(HttpServletRequest req) {
        UserInfo user = CookieUtil.GetCurrentUser(req);
        return accountService.getAccountSelect(user.getDomain());
    }

    @ResponseBody
    @RequestMapping(value = "deleteMedia", method = RequestMethod.POST)
    public Map<String, Object> deleteMedia(@RequestBody List<Integer> data) {
        return mediaService.deleteMedia(data);
    }
}
