package com.controller;

import com.model.PagerParam;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.message.*;
import com.models.wx.message.PreviewVoiceReq;
import com.service.web.inter.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ResponseBody
    @RequestMapping(value = "getMessageList", method = RequestMethod.POST)
    public DataListResp getMessageList(PagerParam data) {
        DataListResp resp = messageService.getMessageList(data.getPageSize(), data.getPageIndex(), data.getDomain(),
                data.getArgs());
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "addMessage", method = RequestMethod.POST)
    public BaseResp addMessage(AddMessageReq req) {
        BaseResp resp = messageService.addMessage(req);
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "getMessage", method = RequestMethod.POST)
    public MessageInfo getMessage(@RequestParam("id") Integer id) {
        MessageInfo m = messageService.getMessage(id);
        return m;
    }

    @ResponseBody
    @RequestMapping(value = "deleteMessage", method = RequestMethod.POST)
    public BaseResp deleteMessage(DelMessageReq req) {
        BaseResp resp = messageService.deleteMessage(req);
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public BaseResp sendMessage(SendMessageReq req) {
        BaseResp resp = messageService.sendMessage(req);
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "syncSendState", method = RequestMethod.POST)
    public BaseResp syncSendState(SyncSendStateReq req) {
        BaseResp resp = messageService.syncSendState(req);
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "previewMessage", method = RequestMethod.POST)
    public BaseResp previewMessage(PreviewMessageReq req) {
        BaseResp resp = messageService.previewMessage(req);
        return resp;
    }
}
