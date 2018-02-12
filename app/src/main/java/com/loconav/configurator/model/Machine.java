package com.loconav.configurator.model;

/**
 * Created by prateek on 28/11/17.
 */
public class Machine {

    //private variables
    private String machine_name;
    private Integer total_msg;

    // Empty constructor
    public Machine(){

    }
    // constructor
    public Machine(String machineName, Integer totalMsgs){
        this.machine_name = machineName;
        this.total_msg = totalMsgs;
    }

    // getting ID

    // setting id

    // getting name
    public String getMachine_name(){
        return this.machine_name;
    }

    // setting name
    public void setName(String name){
        this.machine_name = name;
    }

    // getting phone number
    public Integer getTotal_msg(){
        return this.total_msg;
    }

    // setting phone number
    public void setTotal_msg(Integer totalMsg){
        this.total_msg = totalMsg;
    }
}