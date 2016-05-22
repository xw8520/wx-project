package com.service.web.impl;

import com.data.WxMediaMapper;
import com.domain.wx.WxMedia;
import com.domain.wx.WxMediaExample;
import com.enums.WxMediaType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.AccountInfo;
import com.models.web.MediaInfo;
import com.models.web.SaveMediaInfo;
import com.models.web.UserInfo;
import com.service.api.WxMediaService;
import com.service.web.AccountService;
import com.service.web.MediaService;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 2016/5/15.
 */
@Service("mediaService")
public class MediaServiceImpl implements MediaService {

    Logger log = LoggerFactory.getLogger(MediaServiceImpl.class);
    @Resource
    WxMediaMapper wxMediaMapper;
    @Resource
    AccountService accountService;
    @Resource
    WxMediaService wxMediaService;

    @Override
    public Map<String, Object> getMediaList(int pageSize, int pageIndex, String args) {
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
            newItem.setMediatype(WxMediaType.unknow.getTypeName(item.getMediatype()));
            newItem.setTitle(item.getTitle());
            newItem.setPermanent(item.getPermanent());
            result.add(newItem);
        }
        map.put("list", result);
        return map;
    }

    @Override
    public Map<String, Object> addMedia(SaveMediaInfo data, UserInfo user) {
        if (data.getPermanent()) {

        } else {
            try {
                return wxMediaService.addTmpMedia(data.getFilename(), data.getAccountid(),
                        user.getDomain(), data.getTitle(), data.getRemark());
            } catch (Exception ex) {
                log.error("", ex);
            }
        }
        return new HashMap<>();
    }

    @Override
    public Map<String, Object> deleteMedia(List<Integer> data) {
        Map<String, Object> map = new HashMap<>();
        WxMediaExample exp = new WxMediaExample();
        exp.createCriteria().andIdIn(data);
        try {
            wxMediaMapper.deleteByExample(exp);
            map.put("success", true);
            return map;
        } catch (Exception ex) {
            log.error("", ex);
            map.put("success", false);
            map.put("info", "系统出错");
        }
        return map;
    }
}
