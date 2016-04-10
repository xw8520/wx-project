package com.service.api;

import com.models.wx.message.NewsMessageItem;
import com.wxconfig.ReceiveMsg;

import java.util.List;

/**
 * Created by Admin on 2016/3/11.
 * 文本消息
 */
public interface WxSendMsgService {

    /**
     * 获取文本消息
     * @param receive
     * @param msg
     * @return
     */
    String getTextMsg(ReceiveMsg receive, String msg);

    /**
     * 获取图片消息
     * @param receive
     * @param mediaId
     * @return
     */
    String getImageMsg(ReceiveMsg receive, String mediaId);

    /**
     * 获取语音消息
     * @param receive
     * @param mediaId
     * @return
     */
    String getVoiceMsg(ReceiveMsg receive, String mediaId);

    /**
     * 获取图文消息
     * @param receive
     * @param list
     * @return
     */
    String getNewsMsg(ReceiveMsg receive,  List<NewsMessageItem> list);

    /**
     * 获取视频消息
     * @param receive
     * @param mediaId
     * @param title
     * @param desc
     * @return
     */
    String getVideoMsg(ReceiveMsg receive, String mediaId,String title,String desc);

    /**
     *
     * @param receive
     * @param mediaId 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
     * @param title 音乐标题
     * @param desc 音乐描述
     * @param misucUrl 音乐链接
     * @param hqUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @return
     */
    String getMusicMsg(ReceiveMsg receive, String mediaId, String title,
                       String desc, String misucUrl, String hqUrl);

    /**
     * 客服接口 -发送文本消息
     * @param to
     * @param msg
     * @return
     */
    String sendCustomTextMsg(String to, String msg, int accountid);

    /**
     * 微信发送图片
     * @param to
     * @param mediaId
     * @param accountid
     * @return
     */
    String sendCustomImgMsg(String to,String mediaId, int accountid);
}
