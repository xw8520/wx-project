package com.domain.wx;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/2/28.
 */
public class Jsapiticket implements Serializable {

    public Jsapiticket(){}
    public Jsapiticket(int accountid,String ticket,Date expiredtime){
        this.accountid=accountid;
        this.ticket=ticket;
        this.createtime=new Date();
        this.expiredtime=expiredtime;

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getExpiredtime() {
        return expiredtime;
    }

    public void setExpiredtime(Date expiredtime) {
        this.expiredtime = expiredtime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    private Date createtime;
    private Date expiredtime;
    private String ticket;
    private int accountid;
}
