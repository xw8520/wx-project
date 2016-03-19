package com.utils;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    /**
     * 上传文件
     *
     * @param url
     * @param path
     * @param param
     * @return
     */
    public static String doPost(String url, String path, List<NameValuePair> param) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            FileBody fileBody = new FileBody(new File(path));

            MultipartEntity entity = new MultipartEntity();
            entity.addPart("media", fileBody);
            post.setEntity(entity);
            response = client.execute(post);
            HttpEntity resEntity = response.getEntity();
            String str = EntityUtils.toString(resEntity, "utf-8");
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 文件下载
     *
     * @param url
     * @return
     */
    public static InputStream doGet(String url) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(4000)
                    .setConnectTimeout(4000).build();//设置请求和传输超时时间
            get.setConfig(requestConfig);
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String contentType = entity.getContentType().getValue();
            if (contentType.contains("json") || contentType.contains("xml")) {
                return null;
            }

            return entity.getContent();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            try {
//                response.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return null;
    }
}
