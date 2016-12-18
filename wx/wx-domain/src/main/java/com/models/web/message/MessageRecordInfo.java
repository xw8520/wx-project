package com.models.web.message;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/18.
 */
public class MessageRecordInfo implements Serializable {
    private int id;
    private int mid;
    private String messageTitle;
    private int accountId;
    private String accountName;
    private int domain;
    private String openId;
    private int tagId;
    private String tagName;
    private int stateId;
    private String stateName;
    private int sendTypeId;
    private String sendTypeName;

    public int getSendTypeId() {
        return sendTypeId;
    }

    public void setSendTypeId(int sendTypeId) {
        this.sendTypeId = sendTypeId;
    }

    public String getSendTypeName() {
        return sendTypeName;
    }

    public void setSendTypeName(String sendTypeName) {
        this.sendTypeName = sendTypeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain) {
        this.domain = domain;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
