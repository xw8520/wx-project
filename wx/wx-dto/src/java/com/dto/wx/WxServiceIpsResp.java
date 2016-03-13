package com.dto.wx;

import java.io.Serializable;

/**
 * Created by Admin on 2016/2/23.
 */
public class WxServiceIpsResp implements Serializable {
    private String[] ip_list;

    public String[] getIp_list() {
        return ip_list;
    }

    public void setIp_list(String[] ip_list) {
        this.ip_list = ip_list;
    }
}
