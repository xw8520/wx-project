package com.api;

import javax.ws.rs.*;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Admin on 2016/2/23.
 */
@Path("/")
public interface WxService {

    @POST
    @Path("/reply")
    @Consumes("application/x-www-form-urlencoded")
    String reply(@FormParam("name") String name);

    @GET
    @Path("/reply")
    @Produces("text/plain")
    String reply(@QueryParam("signature") String signature,
                 @QueryParam("timestamp") String timestamp,
                 @QueryParam("nonce") String nonce,
                 @QueryParam("echostr") String echostr) throws NoSuchAlgorithmException;
}
