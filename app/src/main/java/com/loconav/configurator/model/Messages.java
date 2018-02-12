package com.loconav.configurator.model;

/**
 * Created by prateek on 28/11/17.
 */

public class Messages {
    private Integer MSG_NO;
    private String MSG_TEXT;
    private String MACHINE_TYPE;

    public Messages(){

    }
    public Messages(Integer msgNo, String msgText, String machineType){
        this.MSG_NO = msgNo;
        this.MSG_TEXT = msgText;
        this.MACHINE_TYPE = machineType;
    }

    public void setMSG_NO(Integer MSG_NO) {
        this.MSG_NO = MSG_NO;
    }

    public void setMSG_TEXT(String MSG_TEXT) {
        this.MSG_TEXT = MSG_TEXT;
    }

    public void setMACHINE_TYPE(String machineType) {
        this.MACHINE_TYPE = machineType;
    }

    public Integer getMSG_NO() {
        return MSG_NO;
    }

    public String getMSG_TEXT() {
        return MSG_TEXT;
    }

    public String  getMACHINE_NO() {
        return MACHINE_TYPE;
    }
}
