package com.service.web.impl;

import com.data.WxMediaMapper;
import com.domain.wx.WxMedia;
import com.domain.wx.WxMediaExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.AccountInfo;
import com.models.web.MediaInfo;
import com.service.web.AccountService;
import com.service.web.MediaService;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 2016/5/15.
 */
@Service("mediaService")
public class MediaServiceImpl implements MediaService {

    @Resource
    WxMediaMapper wxMediaMapper;
    @Resource
    AccountService accountService;

    @Override
    public Map<String, Object> getMediaList(int pageSize, int pageIndex, Boolean isLong, String args) {
        Map<String, Object> map = new HashMap<>();
        WxMediaExample exp = new WxMediaExample();
        WxMediaExample.Criteria criteria = exp.createCriteria();
        if (!StringUtils.isNullOrEmpty(args)) {
            try {
                HashMap<String, Object> param = JsonUtils.Deserialize(args, HashMap.class);
                if (!param.isEmpty()) {
                    if (param.containsKey("title") && !StringUtils.isNullOrEmpty(param.get("title"))) {
                        criteria.andTitleLike(param.get("title").toString());
                    }
                    if (param.containsKey("type") && !StringUtils.isNullOrEmpty(param.get("type"))) {
                        criteria.andMediatypeEqualTo(Byte.valueOf(param.get("type").toString()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<WxMedia> list = wxMediaMapper.selectByExample(exp);
        PageInfo<WxMedia> pageInfo = new PageInfo<>(list);
        map.put("total", pageInfo.getTotal());
        MediaInfo newItem;
        List<MediaInfo> result = new ArrayList<>();
        for (WxMedia item : list) {
            newItem = new MediaInfo();
            newItem.setId(item.getId());
            newItem.setMediaid(item.getMediaid());
            newItem.setRemark(item.getRemark());
            AccountInfo accountInfo = accountService.getAccountInfo(item.getAccountid());
            newItem.setAccount(accountInfo != null ? accountInfo.getName() : "");
            newItem.setMediatype(item.getMediatype().toString());
            newItem.setTitle(item.getTitle());
            result.add(newItem);
        }
        map.put("list", result);
        return map;
    }
}
