package com.test;

import com.dto.token.TokenDto;
import com.service.AccessTokenService;
import junit.framework.TestCase;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Admin on 2016/2/18.
 */
public class TestFrame extends TestCase {
    ClassPathXmlApplicationContext context = null;

    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

        context.start();
    }


    public void testMybatis() throws IOException {

        AccessTokenService blogService = (AccessTokenService) context.getBean("accessTokenService");
        TokenDto token = blogService.getAccessToken(1);
        System.out.println("TokenDto:" + token.getAccess_token());
    }

    public void testPostBlog() throws IOException {
        String url = "http://192.168.1.102:8080/services/postBlog?id=2&_type=json";
        HttpClient client = HttpClients.createDefault();

        HttpPost post = new HttpPost(url);
        StringEntity param = new StringEntity("");
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

    public void testGetBlog() throws IOException {
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