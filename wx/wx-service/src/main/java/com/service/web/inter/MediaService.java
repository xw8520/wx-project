package com.service.web.inter;

import com.models.web.*;
import com.models.web.media.AddMediaReq;

import java.util.List;

/**
 * Created by Admin on 2016/5/15.
 */
public interface MediaService {
    /**
     * @param pageSize
     * @param pageIndex
     * @param title
     * @return
     */
    DataListResp getMediaList(int pageSize, int pageIndex, String title);

    /**
     * 新增素材
     *
     * @param data
     * @return
     */
    BaseResp addMedia(AddMediaReq data, UserInfo user);

    /**
     * 删除素材
     *
     * @param data
     * @return
     */
    BaseResp deleteMedia(List<Integer> data);
}
