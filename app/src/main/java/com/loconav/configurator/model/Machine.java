package com.loconav.configurator.model;

/**
 * Created by prateek on 28/11/17.
 */
public class Machine {

    //private variables
    private Integer machine_no;
    private String machine_name;
    private Integer total_msg;

    // Empty constructor
    public Machine(){

    }
    // constructor
    public Machine(Integer machineNo, String machineName, Integer totalMsgs){
        this.machine_no = machineNo;
        this.machine_name = machineName;
        this.total_msg = totalMsgs;
    }

    // getting ID
    public Integer getMachine_no(){
        return this.machine_no;
    }

    // setting id
    public void setMachine_no(Integer number){
        this.machine_no = number;
    }

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