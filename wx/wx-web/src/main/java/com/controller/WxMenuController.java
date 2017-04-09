package com.controller;

import com.models.web.BaseResp;
import com.models.web.wxmenu.BaseMenu;
import com.models.web.wxmenu.ConditionalMenuReq;
import com.models.web.wxmenu.WxMenuReq;
import com.service.web.inter.WxMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wq on 2017/4/8.
 */
@Controller
@RequestMapping("wxmenu")
public class WxMenuController {
    @Resource
    WxMenuService wxMenuService;

    @ResponseBody
    @RequestMapping(value = "addmenu", method = RequestMethod.POST)
    public BaseResp addWxMenu(@RequestBody WxMenuReq req) {
        BaseResp resp = null;
        try {
            resp = wxMenuService.addMenu(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    @ResponseBody
    @RequestMapping("getmenu")
    public BaseResp getWxMenu(@RequestParam("accountId") Integer accountId) {
        BaseResp resp = wxMenuService.getWxMenu(accountId);
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "addconditionalmenu")
    public BaseResp addConditionalMenu(@RequestBody ConditionalMenuReq req) {
        BaseResp resp = wxMenuService.addConditionalMenu(req);
        return resp;
    }
}
