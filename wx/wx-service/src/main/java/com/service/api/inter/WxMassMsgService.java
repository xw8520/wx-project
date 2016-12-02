package com.service.api.inter;

import com.models.wx.WxBaseResp;
import com.models.wx.message.*;

/**
 * Created by admin on 2016/11/27.
 * 群发接口
 */
public interface WxMassMsgService {
    WxMassMsgResp sendArticleMsgByTag(ArticleMsgReq req);

    WxMassMsgResp sendTextMsgByTag(TextMsgReq req);

    WxMassMsgResp sendImageMsgByTag(ImageMsgReq req);

    WxMassMsgResp sendVoiceMsgByTag(VoiceMsgReq req);

    WxMassMsgResp sendArticleMsgByOpenId(ArticleMsgReq req);

    WxMassMsgResp sendTextMsgByOpenId(TextMsgReq req);

    WxMassMsgResp sendImageMsgByOpenId(ImageMsgReq req);

    WxMassMsgResp sendVoiceMsgByOpenId(VoiceMsgReq req);

    WxBaseResp previewArticleMsg(PreviewArticleReq req);

    WxBaseResp previewTextMsg(PreviewTextReq req);

    WxBaseResp previewImageMsg(PreviewImageReq req);

    WxBaseResp previewVoiceMsg(PreviewVoiceReq req);

    SendStatusResp getSendStatus(SendStatusReq req);

    WxBaseResp deleteMsg(DeleteMsgReq req);
}
