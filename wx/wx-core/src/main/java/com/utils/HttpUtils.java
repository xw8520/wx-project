package com.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 2016/2/21.
 */
public class HttpUtils {

    /**
     * @param url
     * @param param
     * @param acceptType
     * @return
     */
    public static String doPost(String url, List<NameValuePair> param,
                                AcceptTypeEnum acceptType) {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            httppost.setHeader("Accept", acceptType.getType());
            UrlEncodedFormEntity uefEntity;
            uefEntity = new UrlEncodedFormEntity(param, "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
            response.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * @param url
     * @param str
     * @param acceptType
     * @return
     */
    public static String doPost(String url, String str, AcceptTypeEnum acceptType) {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            httppost.setHeader("Accept", acceptType.getType());
            HttpEntity param = new StringEntity(str);
            httppost.setEntity(param);
            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
            response.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * @param url
     * @param acceptType
     * @return
     */
    public static String doGet(String url, AcceptTypeEnum acceptType) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept", acceptType.getType());
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}
