package com.service.web;

import com.models.web.SaveMediaInfo;
import com.models.web.UserInfo;

import java.util.List;
import java.util.Map;

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
    Map<String, Object> getMediaList(int pageSize, int pageIndex, String title);

    /**
     * 新增素材
     *
     * @param data
     * @return
     */
    Map<String, Object> addMedia(SaveMediaInfo data, UserInfo user);

    /**
     * 删除素材
     *
     * @param data
     * @return
     */
    Map<String,Object> deleteMedia(List<Integer> data);
}
