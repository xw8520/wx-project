package com.controller;

import com.enums.MessageState;
import com.enums.MessageType;
import com.model.PagerParam;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.message.*;
import com.models.web.tag.TagSelectItem;
import com.models.web.tag.TagSelectReq;
import com.models.wx.message.PreviewVoiceReq;
import com.service.web.inter.MessageRecordService;
import com.service.web.inter.MessageService;
import com.service.web.inter.TagService;
import com.utils.CookieUtil;
import com.utils.EnumUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/12/2.
 */
@Controller
@RequestMapping("message")
public class MessageController {
    @Resource
    MessageService messageService;
    @Resource
    MessageRecordService messageRecordService;
    @Resource
    TagService tagService;

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
    @RequestMapping(value = "getMessageType", method = RequestMethod.POST)
    public List<EnumUtils.KeyVal> getMessageType() {
        List<EnumUtils.KeyVal> map = EnumUtils.getEnumMap(MessageType.class, true);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "getMessageState", method = RequestMethod.POST)
    public List<EnumUtils.KeyVal> getMessageState() {
        List<EnumUtils.KeyVal> map = EnumUtils.getEnumMap(MessageState.class, true);
        return map;
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
        BaseResp resp = messageRecordService.sendMessage(req);
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "syncSendState", method = RequestMethod.POST)
    public BaseResp syncSendState(SyncSendStateReq req) {
        BaseResp resp = messageRecordService.syncSendState(req);
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "previewMessage", method = RequestMethod.POST)
    public BaseResp previewMessage(PreviewMessageReq req) {
        BaseResp resp = messageRecordService.previewMessage(req);
        return resp;
    }

    @RequestMapping("sendByOpenId.html")
    public ModelAndView sendByOpenId(@RequestParam("mid") Integer mid,
                                     @RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        ModelAndView view = new ModelAndView("/message/sendByOpenId");
        SendByOpenIdInfo sendByOpenIdInfo = messageRecordService.getSendByOpenIdInfo(id, mid);
        view.addObject("sendByOpenIdInfo", sendByOpenIdInfo);
        return view;
    }

    @RequestMapping("sendByTagId.html")
    public ModelAndView sendByTagId(@RequestParam("mid") Integer mid,
                                    @RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
                                    HttpServletRequest req) {
        ModelAndView view = new ModelAndView("/message/sendByTagId");
        SendByTagIdInfo sendByTagIdInfo = messageRecordService.getSendByTagIdInfo(id, mid);
        view.addObject("sendByTagIdInfo", sendByTagIdInfo);
        TagSelectReq tsReq = new TagSelectReq();
        tsReq.setAccountId(sendByTagIdInfo.getAccountId());
        String d = CookieUtil.getCookieValue(req, "d");
        tsReq.setDomain(Integer.parseInt(d));
        List<TagSelectItem> tsSelect = tagService.getTagSelect(tsReq);
        view.addObject("TagSelect", tsSelect);
        return view;
    }

    @RequestMapping("messageRecordList.html")
    public ModelAndView messageRecordList() {
        ModelAndView view = new ModelAndView("/message/messageRecordList");
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "getMessageRecordList", method = RequestMethod.POST)
    public DataListResp getMessageRecordList(PagerParam data) {
        DataListResp resp = messageRecordService.getMessageRecordList(
                data.getPageSize(), data.getPageIndex(), data.getDomain(), data.getArgs()
        );
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "getMessageRecordInfo", method = RequestMethod.POST)
    public MessageRecordInfo getMessageRecordInfo(@RequestParam("id") Integer id) {
        return messageRecordService.getMessageRecord(id);
    }

    @ResponseBody
    @RequestMapping(value = "deleteMessageRecord", method = RequestMethod.POST)
    public BaseResp deleteMessageRecord(DeleteMessageRecordReq req) {
        return messageRecordService.deleteMessageRecord(req);
    }

    @ResponseBody
    @RequestMapping(value = "addMessageRecord", method = RequestMethod.POST)
    public BaseResp addMessageRecord(AddMessageRecordReq req) {
        return messageRecordService.addMessageRecord(req);
    }
}
