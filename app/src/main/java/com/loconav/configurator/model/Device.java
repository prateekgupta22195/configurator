package com.loconav.configurator.model;

/**
 * Created by prateek on 28/11/17.
 */

public class Device {
    private Integer machine_no;
    private String device_id;
    private Integer success_count;
    private String type;

    public Device() {}

    public Device(Integer machineNo, String cType, String deviceId,
                  Integer successCount){
        this.machine_no = machineNo;
        this.device_id = deviceId;
        this.success_count = successCount;
        this.type = cType;
    }

    public Integer getMachine_no() {
        return machine_no;
    }

    public String getDevice_id() {
        return device_id;
    }

    public Integer getSuccess_count() {
        return success_count;
    }

    public String getType() {
        return type;
    }

    public void setMachine_no(Integer machine_no) {
        this.machine_no = machine_no;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public void setSuccess_count(Integer success_count) {
        this.success_count = success_count;
    }

    public void setType(String type) {
        this.type = type;
    }
}
