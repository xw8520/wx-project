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
//        msg.setTagid(req.getTagId());
        msg.setTitle(req.getTitle());
//        msg.setToall(req.getToall());
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
            c.andStateidGreaterThan((byte)0);
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
        AccountInfo accountInfo = accountService.getAccountInfo(msg.getAccountid());
        m.setAccountName(accountInfo.getName());
        m.setAccountId(accountInfo.getId());
        m.setDomain(msg.getDomain());
        m.setType(msg.getType());
        m.setTypeName(MessageType.getName(msg.getType()));
        m.setMediaId(msg.getMediaid());
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
    public Message getMessageDb(int id) {
        return messageMapper.selectByPrimaryKey(id);
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
}
