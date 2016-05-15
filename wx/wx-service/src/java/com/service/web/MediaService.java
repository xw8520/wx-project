package com.service.web;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/5/15.
 */
public interface MediaService {
    /**
     *
     * @param pageSize
     * @param pageIndex
     * @param isLong
     * @param title
     * @return
     */
    Map<String, Object> getMediaList(int pageSize, int pageIndex, Boolean isLong, String title);
}
