package com.service.web.inter;

import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.message.*;

/**
 * Created by admin on 2016/12/18.
 */
public interface MessageRecordService {
    /**
     * 添加消息记录
     * @param req
     * @return
     */
    BaseResp addMessageRecord(AddMessageRecordReq req);

    /**
     * 获取消息记录
     * @param id
     * @return
     */
    MessageRecordInfo getMessageRecord(int id);

    /**
     * 获取消息记录列表
     * @param pageSize
     * @param pageIndex
     * @param domain
     * @param args
     * @return
     */
    DataListResp getMessageRecordList(int pageSize,int pageIndex,int domain,String args);

    /**
     * 删除消息
     * @param req
     * @return
     */
    BaseResp deleteMessageRecord(DeleteMessageRecordReq req);

    /**
     * 发送消息
     * @param req
     * @return
     */
    BaseResp sendMessage(SendMessageReq req);

    /**
     * 同步消息状态
     * @param req
     * @return
     */
    BaseResp syncSendState(SyncSendStateReq req);

    /**
     * 预览消息
     * @param req
     * @return
     */
    BaseResp previewMessage(PreviewMessageReq req);
}
