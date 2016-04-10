package com.test;

import com.models.wx.message.NewsMessageItem;
import com.models.wx.token.TokenResp;
import com.models.wx.user.UserInfoListResp;
import com.enums.WxMediaType;
import com.models.wx.media.ArticleItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.service.api.AccessTokenService;
import com.service.api.WxMediaService;
import com.service.api.WxMessageService;
import com.service.api.impl.WxMediaServiceImpl;
import com.utils.JsonUtils;
import com.utils.XmlParseUtils;
import junit.framework.TestCase;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Admin on 2016/2/18.
 */
public class TestFrame extends TestCase {
    ClassPathXmlApplicationContext context = null;
    static Logger log = LoggerFactory.getLogger(TestFrame.class);
    WxMediaService wxMediaService;

    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

        context.start();
    }


    public void testMybatis() throws IOException {

        AccessTokenService blogService = (AccessTokenService) context.getBean("accessTokenService");
        TokenResp token = blogService.getAccessToken(1);
        System.out.println("TokenResp:" + token.getAccess_token());
    }

    public void testWxServiceIps() throws IOException {

        WxMessageService wxServerIpService = (WxMessageService) context.getBean("wxMsgService");
        wxServerIpService.getWxServerIp(1);
    }

    public void testPost() throws IOException {
        String url = "http://tdlz68zf6v.proxy.qqbrowser.cc/WeChatService/reply";
        HttpClient client = HttpClients.createDefault();

        HttpPost post = new HttpPost(url);
//        StringEntity param = new StringEntity("<xml>1123456</xml>");
        List<NameValuePair> kv = new ArrayList<NameValuePair>();
        kv.add(new BasicNameValuePair("name", "value"));
        HttpEntity param = new UrlEncodedFormEntity(kv);
        post.setEntity(param);
//        post.addHeader("Accept", "application/json");
        CloseableHttpResponse response = (CloseableHttpResponse)
                client.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String json = EntityUtils.toString(entity, "UTF-8");
            System.out.println(json);
        }
    }

    public void testGet() throws IOException {
        HttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet("http://192.168.1.102:8080/services/getBlog?id=1");
        get.addHeader("Accept", "application/xml");
        CloseableHttpResponse response = (CloseableHttpResponse)
                client.execute(get);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String json = EntityUtils.toString(entity, "UTF-8");
            System.out.println(json);
        }
    }

    public void testJsonDeSeralize() throws IOException {
        String json = "{\"user_info_list\": [{\"subscribe\": 1,\"openid\": \"otvxTs4dckWG7imySrJd6jSi0CWE\",\"nickname\": \"iWithery\",\"sex\": 1,\"language\": \"zh_CN\",\"city\": \"Jieyang\",\"province\": \"Guangdong\",\"country\": \"China\",\"headimgurl\": \"http://wx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCunTPicGKezDC4saKISzRj3nz/0\",\"subscribe_time\": 1434093047,\"unionid\": \"oR5GjjgEhCMJFyzaVZdrxZ2zRRF4\",\"remark\": \"\",\"groupid\": 0},{\"subscribe\": 0,\"openid\": \"otvxTs_JZ6SEiP0imdhpi50fuSZg\",\"unionid\": \"oR5GjjjrbqBZbrnPwwmSxFukE41U\"}]}";
        UserInfoListResp dto = JsonUtils.Deserialize(json, UserInfoListResp.class);
        System.out.println(dto != null);
    }

    public void testXmlSer() {
        String xml = " <xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[fromUser]]></FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <Content><![CDATA[this is a test]]></Content>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " </xml>";
        Document doc = XmlParseUtils.getDocumentByXML(xml);
        String msgType = XmlParseUtils.getDocElementTextByPath(doc, "xml/MsgType");
        System.out.println();
    }

    public void testStringFormate() {
        List<NewsMessageItem> items = new ArrayList<>();
        items.add(new NewsMessageItem("测试标题", "测试图文消息的描述",
                "http://wxtest963.tunnel.qydev.com/static/image/news-big.jpg",
                "http://wxtest963.tunnel.qydev.com/"));
        items.add(new NewsMessageItem("测试标题", "测试图文消息的描述",
                "http://wxtest963.tunnel.qydev.com/static/image/pic-small.jpg",
                "http://wxtest963.tunnel.qydev.com/"));
        int count = items.size();

        String msg = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>{3}</ArticleCount>\n" +
                "<Articles>{4}" +
                "</Articles>" +
                "</xml> ";

        String item = "<item>\n" +
                "<Title><![CDATA[{%s]]></Title> \n" +
                "<Description><![CDATA[{%s]]></Description>\n" +
                "<PicUrl><![CDATA[{%s]]></PicUrl>\n" +
                "<Url><![CDATA[%s]]></Url>\n" +
                "</item>";
        StringBuffer buffer = new StringBuffer();
        for (NewsMessageItem s : items) {
            buffer.append(String.format(item, s.getTitle(), s.getDesc(), s.getPicUrl(), s.getUrl()));
        }
        Long time = Calendar.getInstance().getTimeInMillis();
        msg = MessageFormat.format(msg, "to", "from", time, count, buffer.toString());
        System.out.println(msg);
    }

    public void testPattern() {
        WxMediaServiceImpl pattern = new WxMediaServiceImpl();
        String fileName = "E:\\git\\wx-project\\123.mp4";
        WxMediaType media = pattern.getMediaType(fileName);
        System.out.println(media);
    }

    public void testSerializeHash() throws JsonProcessingException {
        List<ArticleItem> list = new ArrayList<>();
        list.add(new ArticleItem());
        Hashtable hash = new Hashtable();
        hash.put("articles", list);

        String param = JsonUtils.Serialize(hash);
        System.out.println(param);
    }

    public void testLogger() {
//        logger.debug("debug","消息内容");
        try {
            log.info("info message", "info");
            log.debug("debug message", "debug");
            Object str = null;
            str.toString();
        } catch (Exception ex) {
            log.error("error", ex);
        }
    }

    public void testWxMedia() throws Exception {
        wxMediaService = (WxMediaService) context.getBean("wxMediaService");
        ArticleItem item1 = new ArticleItem("eFTC7PRZVf0W5qzzG6WCTdcoaKtfA8B6qhhkTHs9uuc", "小王",
                "测试标题", " http://wxtest963.tunnel.qydev.com", "Content", "digest", "1");
        ArticleItem item2 = new ArticleItem("eFTC7PRZVf0W5qzzG6WCTcGlHsIv5TleryRQwzCa_HM", "小李",
                "测试标题1", " http://wxtest963.tunnel.qydev.com", "Content1", "digest1", "0");
        List<ArticleItem> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        String mediaId = wxMediaService.uploadNews(list, 1);
        log.debug(mediaId);
    }

    public void testJsonSer() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("touser", "str");
        node.put("msgtype", "text");
        ObjectNode content = node.putObject("text");
        content.put("content", "message");
        String str = mapper.writeValueAsString(node);
        log.debug(str);
    }
}