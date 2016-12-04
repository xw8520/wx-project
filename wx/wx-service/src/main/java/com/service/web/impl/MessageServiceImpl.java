package com.service.web.impl;

import com.data.MessageMapper;
import com.data.WxTagMapper;
import com.domain.wx.Message;
import com.domain.wx.MessageExample;
import com.domain.wx.WxTag;
import com.enums.MessageState;
import com.enums.MessageType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.AccountInfo;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.message.*;
import com.models.wx.WxBaseResp;
import com.models.wx.message.*;
import com.service.api.inter.WxMassMsgService;
import com.service.web.inter.AccountService;
import com.service.web.inter.MessageService;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/12/2.
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    MessageMapper messageMapper;
    @Resource
    AccountService accountService;
    @Resource
    WxTagMapper wxTagMapper;
    @Resource
    WxMassMsgService wxMassMsgService;

    @Override
    public BaseResp addMessage(AddMessageReq req) {
        BaseResp resp = new BaseResp();
        Message msg = new Message();
        msg.setAccountid(req.getAccountId());
        msg.setMediaid(req.getMediaId());
        msg.setRemark(req.getRemark());
        msg.setTagid(req.getTagId());
        msg.setTitle(req.getTitle());
        msg.setToall(req.getToall());
        msg.setType(req.getType());
        msg.setContent(req.getContent());
        if (req.getId() > 0) {
            MessageExample exp = new MessageExample();
            MessageExample.Criteria c = exp.createCriteria();
            c.andIdEqualTo(req.getId());
            boolean s = messageMapper.updateByExampleSelective(msg, exp) > 0;
            resp.setSuccess(s);
            return resp;
        }
        msg.setStateid((byte) MessageState.normal.getValue());
        msg.setDomain(req.getDomain());
        msg.setDelwx(false);
        msg.setCreatetime(new Date());
        boolean s = messageMapper.insert(msg) > 0;
        resp.setSuccess(s);
        return resp;
    }

    @Override
    public DataListResp getMessageList(int pageSize, int pageIndex, int domain, String args) {
        DataListResp resp = new DataListResp();
        try {
            MessageExample exp = new MessageExample();
            MessageExample.Criteria c = exp.createCriteria();
            c.andDomainEqualTo(domain);
            Map<String, String> param = (HashMap<String, String>) JsonUtils.Deserialize(args, HashMap.class);
            if (param.containsKey("accountId") && !param.get("accountId").equals("-1")) {
                c.andAccountidEqualTo(Integer.valueOf(param.get("accountId")));
            }
            if (param.containsKey("title")) {
                c.andTitleLike(String.format("%%s%", param.get("title")));
            }
            if (param.containsKey("type") &&
                    !StringUtils.isNullOrEmpty(param.get("type"))
                    && !"-1".equals(param.get("type"))) {
                c.andTypeEqualTo(Short.valueOf(param.get("type")));
            }
            if (param.containsKey("state")
                    && !StringUtils.isNullOrEmpty(param.get("state"))
                    && !"-1".equals(param.get("state"))) {
                c.andStateidEqualTo(Byte.valueOf(param.get("state")));
            }
            PageHelper.startPage(pageIndex, pageSize);
            List<Message> tmp = messageMapper.selectByExample(exp);
            PageInfo page = new PageInfo(tmp);
            resp.setTotal(page.getTotal());
            List<MessageInfo> list = tmp.stream().map(f -> messageInfoMap(f)).collect(Collectors.toList());
            resp.setList(list);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }

    private MessageInfo messageInfoMap(Message msg) {
        MessageInfo m = new MessageInfo();
        m.setId(msg.getId());
        m.setAccountId(msg.getAccountid());
        m.setTitle(msg.getTitle());
        AccountInfo accountInfo = accountService.getAccountInfo(msg.getId());
        m.setAccountName(accountInfo.getName());
        m.setAccountId(accountInfo.getId());
        m.setDomain(msg.getDomain());
        m.setType(msg.getType());
        m.setTypeName(MessageType.getName(msg.getType()));
        m.setMediaId(msg.getMediaid());
        m.setMessageId(msg.getMessageid());
        m.setTagId(msg.getTagid());
        WxTag tag = wxTagMapper.selectByPrimaryKey(msg.getTagid());
        m.setTagId(tag != null ? tag.getId() : 0);
        m.setTagName(tag != null ? tag.getName() : "");
        m.setStateId(msg.getStateid());
        m.setContent(msg.getContent());
        m.setStateName(MessageState.getName(msg.getStateid()));
        return m;
    }

    @Override
    public MessageInfo getMessage(int id) {
        Message m = messageMapper.selectByPrimaryKey(id);
        return messageInfoMap(m);
    }

    @Override
    public BaseResp deleteMessage(DelMessageReq req) {
        BaseResp resp = new BaseResp();
        Message m = messageMapper.selectByPrimaryKey(req.getId());
        Message newM = new Message();
        newM.setId(m.getId());

        if (m.getType() == MessageType.news.getValue()) {
            newM.setStateid((byte) MessageState.delWx.getValue());
        } else {
            newM.setStateid((byte) MessageState.delete.getValue());
        }
        MessageExample exp = new MessageExample();
        MessageExample.Criteria c = exp.createCriteria();
        c.andIdEqualTo(req.getId());
        boolean s = messageMapper.updateByExampleSelective(newM, exp) > 0;
        resp.setSuccess(s);
        return resp;
    }

    @Override
    public BaseResp sendMessage(SendMessageReq req) {
        BaseResp resp = new BaseResp();
        try {
            Message m = messageMapper.selectByPrimaryKey(req.getId());
            if (m == null) {
                resp.setInfo("消息不存在");
                return resp;
            }
            WxTag tag = wxTagMapper.selectByPrimaryKey(m.getTagid());
            if (tag == null) {
                resp.setInfo("标签不存在");
                return resp;
            }
            switch (m.getType()) {
                case 1:
                    ArticleMsgReq artReq = new ArticleMsgReq();
                    artReq.setAccountId(m.getAccountid());
                    artReq.setMediaId(m.getMediaid());
                    artReq.setToAll(m.getToall());
                    artReq.setTagId(tag.getWxid());
                    WxMassMsgResp re = wxMassMsgService.sendArticleMsgByTag(artReq);
                    resp.setSuccess(re.getErrcode() == 0);
                    resp.setInfo(re.getErrmsg());
                    resp.setData(re.getMsg_id());
                    break;
                case 2:
                    TextMsgReq textReq = new TextMsgReq();
                    textReq.setAccountId(m.getAccountid());
                    textReq.setToAll(m.getToall());
                    textReq.setTagId(tag.getWxid());
                    textReq.setContent(m.getContent());
                    WxMassMsgResp textRe = wxMassMsgService.sendTextMsgByTag(textReq);
                    resp.setSuccess(textRe.getErrcode() == 0);
                    resp.setInfo(textRe.getErrmsg());
                    resp.setData(textRe.getMsg_id());
                    break;
                case 3:
                    ImageMsgReq imageReq = new ImageMsgReq();
                    imageReq.setAccountId(m.getAccountid());
                    imageReq.setToAll(m.getToall());
                    imageReq.setTagId(tag.getWxid());
                    imageReq.setMediaId(m.getMediaid());
                    WxMassMsgResp imageRe = wxMassMsgService.sendImageMsgByTag(imageReq);
                    resp.setSuccess(imageRe.getErrcode() == 0);
                    resp.setInfo(imageRe.getErrmsg());
                    resp.setData(imageRe.getMsg_id());
                    break;
                case 4:
                    VoiceMsgReq voiceReq = new VoiceMsgReq();
                    voiceReq.setAccountId(m.getAccountid());
                    voiceReq.setToAll(m.getToall());
                    voiceReq.setTagId(tag.getWxid());
                    voiceReq.setMediaId(m.getMediaid());
                    WxMassMsgResp voiceRe = wxMassMsgService.sendVoiceMsgByTag(voiceReq);
                    resp.setSuccess(voiceRe.getErrcode() == 0);
                    resp.setInfo(voiceRe.getErrmsg());
                    resp.setData(voiceRe.getMsg_id());
                    break;
            }
            if (resp.getSuccess()) {
                Message newM = new Message();
                newM.setId(m.getId());
                newM.setMessageid(resp.getData());
                newM.setStateid((byte) MessageState.send.getValue());
                MessageExample exp = new MessageExample();
                MessageExample.Criteria c = exp.createCriteria();
                c.andIdEqualTo(m.getId());
                boolean s = messageMapper.updateByExampleSelective(newM, exp) > 0;
                resp.setSuccess(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setInfo("系统出错");
        }
        return resp;
    }

    @Override
    public BaseResp syncSendState(SyncSendStateReq req) {
        BaseResp resp = new BaseResp();
        try {
            Message m = messageMapper.selectByPrimaryKey(req.getId());
            if (m == null) {
                resp.setInfo("消息不存在");
                return resp;
            }
            SendStatusReq sendReq = new SendStatusReq();
            sendReq.setAccountId(m.getAccountid());
            sendReq.setMsgId(m.getMessageid());
            SendStatusResp re = wxMassMsgService.getSendStatus(sendReq);
            resp.setSuccess("SEND_SUCCESS".equals(re.getMsg_status()));
            if(resp.getSuccess()){
                resp.setInfo("同步成功");
            }else{
                resp.setInfo("同步失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setInfo("系统出错");
        }
        return resp;
    }

    @Override
    public BaseResp previewMessage(PreviewMessageReq req) {
        BaseResp resp = new BaseResp();
        try {
            Message m = messageMapper.selectByPrimaryKey(req.getId());
            if (m == null) {
                resp.setInfo("消息不存在");
                return resp;
            }
            WxTag tag = wxTagMapper.selectByPrimaryKey(m.getTagid());
            if (tag == null) {
                resp.setInfo("标签不存在");
                return resp;
            }
            WxBaseResp re = null;
            switch (m.getType()) {
                case 0:
                    PreviewArticleReq artReq = new PreviewArticleReq();
                    artReq.setAccountId(m.getAccountid());
                    artReq.setMediaId(m.getMediaid());
                    artReq.setOpenId(req.getOpenId());
                    re = wxMassMsgService.previewArticleMsg(artReq);
                    break;
                case 1:
                    PreviewTextReq textReq = new PreviewTextReq();
                    textReq.setAccountId(m.getAccountid());
                    textReq.setOpenId(req.getOpenId());
                    textReq.setConent(m.getContent());
                    re = wxMassMsgService.previewTextMsg(textReq);

                    break;
                case 2:
                    PreviewImageReq imageReq = new PreviewImageReq();
                    imageReq.setAccountId(m.getAccountid());
                    imageReq.setMediaId(m.getMediaid());
                    imageReq.setOpenId(req.getOpenId());
                    re = wxMassMsgService.previewImageMsg(imageReq);
                    break;
                case 3:
                    PreviewVoiceReq voiceReq = new PreviewVoiceReq();
                    voiceReq.setAccountId(m.getAccountid());
                    voiceReq.setMediaId(m.getMediaid());
                    voiceReq.setOpenId(req.getOpenId());
                    re = wxMassMsgService.previewVoiceMsg(voiceReq);
                    break;
            }
            if (re == null) {
                resp.setInfo("消息类型有误");
                return resp;
            }
            resp.setSuccess(re.getErrcode() == 0);
            resp.setInfo(re.getErrmsg());
            resp.setData(re.getMsg_id());
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setInfo("系统出错");
        }
        return resp;
    }
}
