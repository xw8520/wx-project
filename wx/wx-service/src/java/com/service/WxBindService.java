package com.service;

import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Admin on 2016/2/21.
 */
@Path("/")
public interface WxBindService {
    @GET
    @Path("/Reply")
    @Produces("text/plain")
    String Reply(@QueryParam("signature") String signature,
                 @QueryParam("timestamp") String timestamp,
                 @QueryParam("nonce")String nonce,
                 @QueryParam("echostr") String echostr);
}
