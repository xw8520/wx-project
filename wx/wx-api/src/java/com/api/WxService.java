package com.api;

import com.models.wx.message.CustomNewsMsg;
import com.models.wx.message.CustomNewsMsgItem;
import com.models.wx.message.CustomTextMsg;
import com.models.wx.user.CreateTagReq;
import com.models.wx.user.QrCodeReq;
import com.models.wx.user.UserInfoResp;

import javax.ws.rs.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Admin on 2016/2/23.
 */
@Path("/")
public interface WxService {

    @POST
    @Path("/reply")
    String reply(String body);

    @GET
    @Path("/reply")
    @Produces("text/plain")
    String reply(@QueryParam("signature") String signature,
                 @QueryParam("timestamp") String timestamp,
                 @QueryParam("nonce") String nonce,
                 @QueryParam("echostr") String echostr) throws NoSuchAlgorithmException;

    @POST
    @Path("/getQrCode")
    @Produces("application/json")
    @Consumes("application/json")
    String getQrCode(QrCodeReq data) throws Exception;

    @GET
    @Path("/getUserInfo/{accountid:\\d+}/{openid}")
    @Produces("application/json")
    UserInfoResp getUserInfo(@PathParam("accountid") int accountid,
                             @PathParam("openid") String openid) throws Exception;

    /**
     * 客服接口 -发送文本消息
     * @return
     */
    @POST
    @Path("/sendCustomTextMsg")
    @Produces("application/json")
    String sendCustomTextMsg(CustomTextMsg data);

    /**
     * 客服接口 -发送图文消息
     * @return
     */
    @POST
    @Path("/sendCustomNewsMsg")
    @Produces("application/json")
    String sendCustomNewsMsg(CustomNewsMsg data);

    /**
     * 客服接口 -发送图文消息
     * @return
     */
    @POST
    @Path("/createTag")
    @Produces("application/json")
    String createTag(CreateTagReq data) throws Exception;
}
