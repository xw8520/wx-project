package com.api.impl;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by TimLin on 2016/5/17.
 */
public class AuthContainerRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext req) throws IOException {

        
//        InputStream stream = req.getEntityStream();
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        byte[] buffer=new byte[1024];
//        int count=0;
//        while ((count=stream.read(buffer))!=-1){
//            outStream.write(buffer, 0, count);
//        }
//
//        System.out.print( new String(outStream.toByteArray(),"utf-8"));
//        req.setEntityStream(stream);
    }
}
