package com.service.web.impl;

import com.data.WxMediaMapper;
import com.domain.wx.WxMedia;
import com.domain.wx.WxMediaExample;
import com.enums.WxMediaType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.*;
import com.models.web.media.MediaInfo;
import com.models.web.media.AddMediaReq;
import com.models.wx.media.AddMaterialReq;
import com.models.wx.media.AddMaterialResp;
import com.models.wx.media.AddTmpMediaResp;
import com.service.api.inter.WxMediaService;
import com.service.web.inter.AccountService;
import com.service.web.inter.MediaService;
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
    public DataListResp getMediaList(int pageSize, int pageIndex, String args) {
        DataListResp resp = new DataListResp();
        WxMediaExample exp = new WxMediaExample();
        WxMediaExample.Criteria criteria = exp.createCriteria();
        if (!StringUtils.isNullOrEmpty(args)) {
            try {
                HashMap<String, String> param = JsonUtils.Deserialize(args, HashMap.class);
                if (!param.isEmpty()) {
                    if (param.containsKey("name")) {
                        criteria.andTitleLike(param.get("name").toString());
                    }
                    if (param.containsKey("type")&&!param.get("type").equals("-1")) {
                        criteria.andMediatypeEqualTo(Byte.valueOf(param.get("type")));
                    }
                    if (param.containsKey("account")
                            &&!param.get("account").equals("-1")) {
                        criteria.andAccountidEqualTo(Integer.valueOf(param.get("account")));
                    }
                    if (param.containsKey("permanent")
                            &&!param.get("permanent").equals("-1")) {
                        criteria.andPermanentEqualTo(param.get("permanent").equals("1"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<WxMedia> list = wxMediaMapper.selectByExample(exp);
        PageInfo<WxMedia> pageInfo = new PageInfo<>(list);
        resp.setTotal(pageInfo.getTotal());
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
        resp.setList(result);
        return resp;
    }

    @Override
    public BaseResp addMedia(AddMediaReq data, UserInfo user) {
        BaseResp resp = new BaseResp();
        try {
            AddMaterialReq req = new AddMaterialReq(data.getFilename(),
                    data.getAccountid(), user.getDomain(), data.getTitle(),
                    data.getRemark(), data.getMediaType());
            if (data.getPermanent()) {
                AddMaterialResp addMaterialResp = wxMediaService.addMaterial(req);
                resp.setSuccess(addMaterialResp.getErrcode() == 0);
                resp.setInfo(addMaterialResp.getErrmsg());
            } else {
                AddTmpMediaResp addTmpMediaResp = wxMediaService.addTmpMedia(req);
                resp.setSuccess(addTmpMediaResp.getErrcode() == 0);
                resp.setInfo(addTmpMediaResp.getErrmsg());
            }
        } catch (Exception ex) {
            log.error("", ex);
        }
        return resp;
    }

    @Override
    public BaseResp deleteMedia(List<Integer> data) {
        BaseResp resp = new BaseResp();
        WxMediaExample exp = new WxMediaExample();
        exp.createCriteria().andIdIn(data);
        try {
            wxMediaMapper.deleteByExample(exp);
            resp.setSuccess(true);
            return resp;
        } catch (Exception ex) {
            log.error("", ex);
            resp.setInfo("系统出错");
        }
        return resp;
    }
}
