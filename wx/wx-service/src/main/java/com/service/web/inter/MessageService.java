package com.service.web.inter;

import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.message.*;

/**
 * Created by admin on 2016/12/2.
 */
public interface MessageService {
    BaseResp addMessage(AddMessageReq req);

    DataListResp getMessageList(int pageSize, int pageIndex, int domain, String args);

    MessageInfo getMessage(int id);

    BaseResp deleteMessage(DelMessageReq req);

    BaseResp sendMessage(SendMessageReq req);

    BaseResp syncSendState(SyncSendStateReq req);

    BaseResp previewMessage(PreviewMessageReq req);
}
