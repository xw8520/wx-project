package com.service.web.inter;

import com.models.web.media.AddImageMsgReq;
import com.models.web.BaseResp;
import com.models.web.DataListResp;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public interface ImageMsgService {
    /**
     * 添加群发图片
     *
     * @param req
     * @return
     */
    BaseResp addImageMsg(AddImageMsgReq req);

    /**
     * 获取群发图片列表
     *
     * @param pageSize
     * @param pageIndex
     * @param args
     * @return
     */
    DataListResp getImageMsgList(int pageSize, int pageIndex, int domain, String args);

    /**
     * 删除群发接口图片
     * @param data
     * @return
     */
    BaseResp deleteImageMsg(List<Integer> data);
}
