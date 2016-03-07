package com.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
}
