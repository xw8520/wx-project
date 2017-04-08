package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.web.BaseResp;
import com.models.web.wxmenu.AddWxMenuReq;
import com.service.web.inter.WxMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wq on 2017/4/8.
 */
@Controller
@RequestMapping("wxmenu")
public class WxMenuControll {
    @Resource
    WxMenuService wxMenuService;

    @ResponseBody
    @RequestMapping("addmenu")
    public BaseResp addWxMenu(@RequestBody AddWxMenuReq req) {
        BaseResp resp = null;
        try {
            resp = wxMenuService.addMenu(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}
