package com.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by Admin on 2016/2/23.
 */
@Path("/")
public interface WxService {

    @POST
    @Path("/Reply")
    @Consumes("application/x-www-form-urlencoded")
    String Reply(@FormParam("name") String name);
}
