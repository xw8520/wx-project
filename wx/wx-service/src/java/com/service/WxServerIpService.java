package com.service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 2016/2/23.
 */
public interface WxServerIpService {
    /**
     *
     * @param accountId
     * @return
     */
     List<String> getWxServerIp(int accountId) throws IOException;
}
