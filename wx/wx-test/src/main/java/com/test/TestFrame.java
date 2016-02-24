package com.test;

import com.dto.wx.TokenDto;
import com.service.AccessTokenService;
import com.service.WxMessageService;
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
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/2/18.
 */
public class TestFrame extends TestCase {
    ClassPathXmlApplicationContext context = null;

    public void setUp() {
//        context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        context.start();
    }


    public void testMybatis() throws IOException {

        AccessTokenService blogService = (AccessTokenService) context.getBean("accessTokenService");
        TokenDto token = blogService.getAccessToken(1);
        System.out.println("TokenDto:" + token.getAccess_token());
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
}