package com.service.web.impl;

import com.data.MessageRecordMapper;
import com.data.WxTagMapper;
import com.domain.wx.Message;
import com.domain.wx.MessageRecord;
import com.domain.wx.MessageRecordExample;
import com.domain.wx.WxTag;
import com.enums.MessageState;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.message.*;
import com.models.wx.WxBaseResp;
import com.models.wx.message.*;
import com.service.api.inter.WxMassMsgService;
import com.service.api.inter.WxTagService;
import com.service.web.inter.MessageRecordService;
import com.service.web.inter.MessageService;
import com.service.web.inter.TagService;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/12/18.
 */
@Service("messageRecordService")
public class MessageRecordServiceImpl implements MessageRecordService {
    @Resource
    MessageRecordMapper messageRecordMapper;
    @Resource
    MessageService messageService;
    @Resource
    TagService tagService;
    @Resource
    WxMassMsgService wxMassMsgService;

    Logger logger = Logger.getLogger(MessageRecordServiceImpl.class);

    @Override
    public BaseResp addMessageRecord(AddMessageRecordReq req) {
        BaseResp resp = new BaseResp();
        try {
            MessageRecord record = new MessageRecord();
            record.setId(req.getId());
            record.setAccountid(req.getAccountId());
            record.setDomain(req.getDomain());
            record.setMid(req.getMid());
            record.setOpenid(req.getOpenId());
            record.setTagid(req.getTagId());
            record.setRemark(req.getRemark());
            record.setTitle(req.getTitle());
            record.setToall(req.getToall());
            boolean s;
            if (record.getId() > 0) {
                MessageRecordExample exp = new MessageRecordExample();
                MessageRecordExample.Criteria c = exp.createCriteria();
                c.andIdEqualTo(record.getId());
                s = messageRecordMapper.updateByExampleSelective(record, exp) > 0;
            } else {
                record.setCreatetime(new Date());
                record.setStateid((byte) MessageState.normal.getValue());
                s = messageRecordMapper.insert(record) > 0;
            }
            resp.setSuccess(s);
            return resp;

        } catch (Exception ex) {
            logger.error(ex);
            resp.setInfo("系统出错");
        }
        return resp;
    }

    @Override
    public MessageRecordInfo getMessageRecord(int id) {
        MessageRecord record = messageRecordMapper.selectByPrimaryKey(id);
        return mapMessageRecordInfo(record);
    }

    @Override
    public SendByTagIdInfo getSendByTagIdInfo(int id, int mid) {
        SendByTagIdInfo info = new SendByTagIdInfo();
        info.setMid(mid);
        MessageInfo msgInfo = messageService.getMessage(mid);
        if (msgInfo != null) {
            info.setAccountName(msgInfo.getAccountName());
            info.setAccountId(msgInfo.getAccountId());
        }
        if (id != 0) {
            info.setId(id);
            MessageRecord record = messageRecordMapper.selectByPrimaryKey(id);
            if (record != null) {
                info.setTitle(record.getTitle());
                info.setRemark(record.getRemark());
                info.setTagId(record.getTagid());
                info.setToall(record.getToall());
            }
            return info;
        }

        return info;
    }

    @Override
    public SendByOpenIdInfo getSendByOpenIdInfo(int id, int mid) {
        SendByOpenIdInfo info = new SendByOpenIdInfo();
        info.setMid(mid);
        MessageInfo msgInfo = messageService.getMessage(mid);
        if (msgInfo != null) {
            info.setAccountName(msgInfo.getAccountName());
            info.setAccountId(msgInfo.getAccountId());
        }
        if (id != 0) {
            info.setId(id);
            MessageRecord record = messageRecordMapper.selectByPrimaryKey(id);
            if (record != null) {
                info.setTitle(record.getTitle());
                info.setRemark(record.getRemark());
                info.setOpenId(record.getOpenid());
                info.setToall(record.getToall());
            }
            return info;
        }

        return info;
    }

    private MessageRecordInfo mapMessageRecordInfo(MessageRecord record) {
        MessageRecordInfo info = new MessageRecordInfo();
        info.setTitle(record.getTitle());
        info.setId(record.getId());
        info.setTagName(record.getTitle());
        info.setAccountId(info.getAccountId());
        info.setDomain(record.getDomain());
        info.setMid(record.getMid());
        info.setOpenId(record.getOpenid());
        info.setStateId(record.getStateid());
        info.setMsgId(record.getMsgid());
        info.setSendTypeId(record.getTagid() != 0 ? 1 : 2);
        info.setSendTypeName(record.getTagid() != 0 ? "标签" : "OpenId");
        MessageInfo msg = messageService.getMessage(record.getMid());
        if (msg != null) {
            info.setAccountName(msg.getAccountName());
            info.setMessageTitle(msg.getTitle());
        }
        WxTag wxTag = tagService.getWxTag(record.getTagid());
        if (wxTag != null) {
            info.setTagName(wxTag.getName());
        }
        info.setStateName(MessageState.getName(record.getStateid()));

        return info;
    }

    @Override
    public DataListResp getMessageRecordList(int pageSize, int pageIndex, int domain, String args) {
        DataListResp resp = new DataListResp();
        MessageRecordExample exp = new MessageRecordExample();
        MessageRecordExample.Criteria c = exp.createCriteria();
        c.andDomainEqualTo(domain);
        try {
            MessageRecordListReq req = JsonUtils.Deserialize(args, MessageRecordListReq.class);
            if (req.getAccountId() != -1) {
                c.andAccountidEqualTo(req.getAccountId());
            }
            if (req.getStateId() != -1) {
                c.andStateidEqualTo((byte) req.getStateId());
            }
            if (req.getTagId() != 0) {
                c.andTagidEqualTo(req.getTagId());
            }
            if (!StringUtils.isNullOrEmpty(req.getTitle())) {
                c.andTitleLike("%" + req.getTitle() + "%");
            }
            Page<MessageRecord> page = PageHelper.startPage(pageIndex, pageSize);
            List<MessageRecord> tmp = messageRecordMapper.selectByExample(exp);
            resp.setTotal(page.getTotal());
            List<MessageRecordInfo> list = tmp.stream().map(f -> mapMessageRecordInfo(f)).collect(Collectors.toList());
            resp.setList(list);
        } catch (IOException e) {
            logger.error(e);
        }

        return resp;
    }

    @Override
    public BaseResp deleteMessageRecord(DeleteMessageRecordReq req) {
        BaseResp resp = new BaseResp();
        boolean s = messageRecordMapper.deleteByPrimaryKey(req.getId()) > 0;
        resp.setSuccess(s);
        return resp;
    }

    @Override
    public BaseResp sendMessage(SendMessageReq req) {
        BaseResp resp = new BaseResp();
        try {
            MessageRecord record = messageRecordMapper.selectByPrimaryKey(req.getId());
            if (record == null) {
                resp.setInfo("消息不存在");
                return resp;
            }
            if (record.getTagid() > 0) {
                resp = sendByTagId(record);
            } else {
                resp = sendByOpenId(record);
            }
            if (resp.getSuccess()) {
                MessageRecord newM = new MessageRecord();
                newM.setId(req.getId());
                newM.setMsgid(resp.getData());
                newM.setStateid((byte) MessageState.send.getValue());
                MessageRecordExample exp = new MessageRecordExample();
                MessageRecordExample.Criteria c = exp.createCriteria();
                c.andIdEqualTo(record.getId());
                boolean s = messageRecordMapper.updateByExampleSelective(newM, exp) > 0;
                resp.setSuccess(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setInfo("系统出错");
        }
        return resp;
    }

    //按OpenId发送
    private BaseResp sendByOpenId(MessageRecord record) {
        BaseResp resp = new BaseResp();
        List<String> openId = Arrays.asList(record.getOpenid().split("\n"));
        Message m = messageService.getMessageDb(record.getMid());
        switch (m.getType()) {
            case 1:
                ArticleMsgReq artReq = new ArticleMsgReq();
                artReq.setAccountId(m.getAccountid());
                artReq.setMediaId(m.getMediaid());
                artReq.setToAll(record.getToall());
                artReq.setOpenId(openId);
                WxMassMsgResp re = wxMassMsgService.sendArticleMsgByOpenId(artReq);
                resp.setSuccess(re.getErrcode() == 0);
                resp.setInfo(re.getErrmsg());
                resp.setData(re.getMsg_id());
                break;
            case 2:
                TextMsgReq textReq = new TextMsgReq();
                textReq.setAccountId(m.getAccountid());
                textReq.setToAll(record.getToall());
                textReq.setOpenId(openId);
                textReq.setContent(m.getContent());
                WxMassMsgResp textRe = wxMassMsgService.sendTextMsgByTag(textReq);
                resp.setSuccess(textRe.getErrcode() == 0);
                resp.setInfo(textRe.getErrmsg());
                resp.setData(textRe.getMsg_id());
                break;
            case 3:
                ImageMsgReq imageReq = new ImageMsgReq();
                imageReq.setAccountId(m.getAccountid());
                imageReq.setToAll(record.getToall());
                imageReq.setOpenId(openId);
                imageReq.setMediaId(m.getMediaid());
                WxMassMsgResp imageRe = wxMassMsgService.sendImageMsgByTag(imageReq);
                resp.setSuccess(imageRe.getErrcode() == 0);
                resp.setInfo(imageRe.getErrmsg());
                resp.setData(imageRe.getMsg_id());
                break;
            case 4:
                VoiceMsgReq voiceReq = new VoiceMsgReq();
                voiceReq.setAccountId(m.getAccountid());
                voiceReq.setToAll(record.getToall());
                voiceReq.setOpenId(openId);
                voiceReq.setMediaId(m.getMediaid());
                WxMassMsgResp voiceRe = wxMassMsgService.sendVoiceMsgByTag(voiceReq);
                resp.setSuccess(voiceRe.getErrcode() == 0);
                resp.setInfo(voiceRe.getErrmsg());
                resp.setData(voiceRe.getMsg_id());
                break;
        }

        return resp;
    }

    //按标签发送
    private BaseResp sendByTagId(MessageRecord record) {
        BaseResp resp = new BaseResp();
        WxTag tag = tagService.getWxTag(record.getTagid());
        if (tag == null) {
            resp.setInfo("标签不存在");
            return resp;
        }
        Message m = messageService.getMessageDb(record.getMid());
        switch (m.getType()) {
            case 1:
                ArticleMsgReq artReq = new ArticleMsgReq();
                artReq.setAccountId(m.getAccountid());
                artReq.setMediaId(m.getMediaid());
                artReq.setToAll(record.getToall());
                artReq.setTagId(tag.getWxid());
                WxMassMsgResp re = wxMassMsgService.sendArticleMsgByTag(artReq);
                resp.setSuccess(re.getErrcode() == 0);
                resp.setInfo(re.getErrmsg());
                resp.setData(re.getMsg_id());
                break;
            case 2:
                TextMsgReq textReq = new TextMsgReq();
                textReq.setAccountId(m.getAccountid());
                textReq.setToAll(record.getToall());
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
                imageReq.setToAll(record.getToall());
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
                voiceReq.setToAll(record.getToall());
                voiceReq.setTagId(tag.getWxid());
                voiceReq.setMediaId(m.getMediaid());
                WxMassMsgResp voiceRe = wxMassMsgService.sendVoiceMsgByTag(voiceReq);
                resp.setSuccess(voiceRe.getErrcode() == 0);
                resp.setInfo(voiceRe.getErrmsg());
                resp.setData(voiceRe.getMsg_id());
                break;
        }

        return resp;
    }

    @Override
    public BaseResp syncSendState(SyncSendStateReq req) {
        BaseResp resp = new BaseResp();
        try {
            SendStatusReq sendReq = new SendStatusReq();
            sendReq.setAccountId(req.getAccountId());
            sendReq.setMsgId(req.getMsgId());
            SendStatusResp re = wxMassMsgService.getSendStatus(sendReq);
            resp.setSuccess("SEND_SUCCESS".equals(re.getMsg_status()));
            if (resp.getSuccess()) {
                resp.setInfo("同步成功");
            } else {
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
            Message m = messageService.getMessageDb(req.getMid());
            WxBaseResp re = null;
            switch (m.getType()) {
                case 1:
                    PreviewArticleReq artReq = new PreviewArticleReq();
                    artReq.setAccountId(m.getAccountid());
                    artReq.setMediaId(m.getMediaid());
                    artReq.setOpenId(req.getOpenId());
                    re = wxMassMsgService.previewArticleMsg(artReq);
                    break;
                case 2:
                    PreviewTextReq textReq = new PreviewTextReq();
                    textReq.setAccountId(m.getAccountid());
                    textReq.setOpenId(req.getOpenId());
                    textReq.setConent(m.getContent());
                    re = wxMassMsgService.previewTextMsg(textReq);

                    break;
                case 3:
                    PreviewImageReq imageReq = new PreviewImageReq();
                    imageReq.setAccountId(m.getAccountid());
                    imageReq.setMediaId(m.getMediaid());
                    imageReq.setOpenId(req.getOpenId());
                    re = wxMassMsgService.previewImageMsg(imageReq);
                    break;
                case 4:
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
