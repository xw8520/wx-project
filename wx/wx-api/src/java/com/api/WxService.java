package com.api;

import com.models.wx.user.QrCodeReq;

import javax.ws.rs.*;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Admin on 2016/2/23.
 */
@Path("/")
public interface WxService {

    @POST
    @Path("/reply")
//    @Produces("application/xml")
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
}
