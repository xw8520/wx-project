package com.test;

import com.models.web.wxmenu.WxMenuReq;
import com.models.web.wxmenu.BaseMenu;
import com.models.web.wxmenu.ComplexMenu;
import com.models.wx.WxBaseResp;
import com.models.wx.media.GetMaterialReq;
import com.models.wx.media.UploadArticleResp;
import com.models.wx.media.WxArticleItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.wx.message.ArticleMsgReq;
import com.models.wx.tag.BatchTaggingReq;
import com.models.wx.tag.CreateTagResp;
import com.service.api.inter.WxMassMsgService;
import com.service.api.inter.WxMediaService;
import com.service.api.inter.WxTagService;
import com.utils.JsonUtils;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/2/18.
 */
public class TestFrame extends TestCase {
    ClassPathXmlApplicationContext context = null;
    static Logger log = LoggerFactory.getLogger(TestFrame.class);
    @Resource
    WxMediaService wxMediaService;

    public void setUp() {
//        context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        context.start();
    }

    @Test
    public void testCreateTag() {
        WxTagService wxTagService = (WxTagService) context.getBean("wxTagService");
        CreateTagResp res = wxTagService.createTag("测试28", 1);
        System.out.println();
    }

    @Test
    public void testAttachTag() {
        WxTagService wxTagService = (WxTagService) context.getBean("wxTagService");
        BatchTaggingReq req = new BatchTaggingReq();
        req.setAccountId(1);
        List<String> openId = new ArrayList<>();
        openId.add("o8MXrsrWROJQIfXa2QH6jSKjnTj8");
//        openId.add("o8MXrsjMqlbWn7mfC3wSaL8R6pqE");
        req.setOpenId(openId);
        req.setTagId(110);
        WxBaseResp res = wxTagService.batchTagging(req);
        System.out.println();
    }

    @Test
    public void testMassMsg() {
        WxMassMsgService wxMassMsgService = (WxMassMsgService) context.getBean("wxMassMsgService");
        ArticleMsgReq req = new ArticleMsgReq();
        req.setTagId(110);
        req.setAccountId(1);
        req.setMediaId("nGHyQ4y-TGUEAiuxpn0J2inaKj1aseQLTWCJWozOwWEDzXtEyyl3SosCYwNER2VY");
        wxMassMsgService.sendArticleMsgByTag(req);
    }

    @Test
    public void testMassMsgOpenId() {
        WxMassMsgService wxMassMsgService = (WxMassMsgService) context.getBean("wxMassMsgService");
        ArticleMsgReq req = new ArticleMsgReq();
//        req.setTagId(110);
        req.setAccountId(1);
        req.setMediaId("nGHyQ4y-TGUEAiuxpn0J2inaKj1aseQLTWCJWozOwWEDzXtEyyl3SosCYwNER2VY");
        List<String> openId = new ArrayList<>();
        openId.add("o8MXrsrWROJQIfXa2QH6jSKjnTj8");
        openId.add("o8MXrsjMqlbWn7mfC3wSaL8R6pqE");
        req.setOpenId(openId);
        wxMassMsgService.sendArticleMsgByOpenId(req);
    }

    public void testWxMedia() throws Exception {
        wxMediaService = (WxMediaService) context.getBean("wxMediaService");
        WxArticleItem item1 = new WxArticleItem("eFTC7PRZVf0W5qzzG6WCTdcoaKtfA8B6qhhkTHs9uuc",
                "小王", "测试标题", " http://wxtest963.tunnel.qydev.com", "Content", "digest", 1);
        WxArticleItem item2 = new WxArticleItem("eFTC7PRZVf0W5qzzG6WCTcGlHsIv5TleryRQwzCa_HM", "小李",
                "测试标题1", " http://wxtest963.tunnel.qydev.com", "Content1", "digest1", 0);
        List<WxArticleItem> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        UploadArticleResp resp = wxMediaService.uploadArticle(list, 1);
        String sf = JsonUtils.Serialize(resp);
        log.debug(sf);
    }

    @Test
    public void testGetMedia() throws JsonProcessingException {
        wxMediaService = (WxMediaService) context.getBean("wxMediaService");
        GetMaterialReq req = new GetMaterialReq();
        req.setAccountId(1);
        req.setMediaId("E_mgs5bWixgMJBgYOLVwAb0fC_3rQ8nHgIxtuX9u-Q8");
        wxMediaService.getMeterail(req);
    }

    @Test
    public void testJson() throws JsonProcessingException {
        WxMenuReq req = new WxMenuReq();
        req.setAccountId(1);
        List<ComplexMenu> list = new ArrayList<>();
        req.setButton(list);
        ComplexMenu menu1 = new ComplexMenu();
        ComplexMenu menu2 = new ComplexMenu();
        ComplexMenu menu3 = new ComplexMenu();
        list.add(menu1);
        list.add(menu2);
        list.add(menu3);

        menu1.setName("菜单1");
        menu1.setUrl("http://wapask.39.net");

        menu2.setName("菜单2");
        List<BaseMenu> menu2List = new ArrayList<>();
        BaseMenu baseMenu = new BaseMenu();
        baseMenu.setName("菜单2-1");
        baseMenu.setUrl("http://www.baidu.com");
        menu2List.add(baseMenu);
        menu2.setSubMenu(menu2List);

        menu3.setName("菜单3");
        List<BaseMenu> menu3List = new ArrayList<>();
        BaseMenu baseMenu31 = new BaseMenu();
        baseMenu31.setName("菜单3-1");
        baseMenu31.setUrl("http://www.baidu.com");
        menu3List.add(baseMenu31);
        BaseMenu baseMenu32 = new BaseMenu();
        baseMenu32.setName("菜单3-1");
        baseMenu32.setUrl("http://www.baidu.com");
        menu3List.add(baseMenu32);
        menu3.setSubMenu(menu3List);
        String json = JsonUtils.Serialize(req);
        System.out.println(json);
    }
}