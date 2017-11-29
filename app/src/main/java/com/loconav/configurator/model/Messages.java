package com.loconav.configurator.model;

/**
 * Created by prateek on 28/11/17.
 */

public class Messages {
    private Integer MSG_NO;
    private String MSG_TEXT;
    private Integer MACHINE_NO;

    public Messages(){

    }
    public Messages(Integer msgNo, String msgText, Integer machineNo){
        this.MSG_NO = msgNo;
        this.MSG_TEXT = msgText;
        this.MACHINE_NO = machineNo;
    }

    public void setMSG_NO(Integer MSG_NO) {
        this.MSG_NO = MSG_NO;
    }

    public void setMSG_TEXT(String MSG_TEXT) {
        this.MSG_TEXT = MSG_TEXT;
    }

    public void setMACHINE_NO(Integer MACHINE_NO) {
        this.MACHINE_NO = MACHINE_NO;
    }

    public Integer getMSG_NO() {

        return MSG_NO;
    }

    public String getMSG_TEXT() {
        return MSG_TEXT;
    }

    public Integer getMACHINE_NO() {
        return MACHINE_NO;
    }
}
