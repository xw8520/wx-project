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
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.mapping.ParameterMap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
     *
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
//            MultipartEntityBuilder builder=MultipartEntityBuilder.create()
            MultipartEntity entity = new MultipartEntity();
            entity.addPart("media", fileBody);
            if (param != null && param.size() > 0) {
                for (NameValuePair p : param) {
                    entity.addPart(p.getName(), new StringBody(p.getValue()));
                }
            }
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
    public static File doGet(String url) {

        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            String content_disposition = conn.getHeaderField("Content-disposition");
            //微信服务器生成的文件名称
            String file_name = "";
            String[] content_arr = content_disposition.split(";");
            if (content_arr.length == 2) {
                String tmp = content_arr[1];
                int index = tmp.indexOf("\"");
                file_name = tmp.substring(index + 1, tmp.length() - 1);
            }
            //生成不同文件名称
            File file = new File(file_name);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buf = new byte[2048];
            int length = bis.read(buf);
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
