package com.dto.wx.media;

import java.io.Serializable;

/**
 * Created by Admin on 2016/3/19.
 * 群发图文消息，图文项
 */
public class ArticleItem implements Serializable {
    //图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
    private String thumb_media_id;
    //
    private String author;
    private String title;
    private String content_source_url;
    //图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用
    private String content;
    //图文消息的描述
    private String digest;
    //是否显示封面，1为显示，0为不显示
    private String show_cover_pic;

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }
}
