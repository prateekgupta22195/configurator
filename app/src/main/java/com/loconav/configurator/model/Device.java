package com.loconav.configurator.model;

/**
 * Created by prateek on 28/11/17.
 */

public class Device {
    private String device_number;
    private String device_id;
    private Integer success_count;
    private String device_type;
    private long timeStamp;
    private String deviceStatus;
    private String simType;



    private int lookupAvailable;
    public Device() {}

    public Device(String deviceNo, String cType, String deviceId,
                  Integer successCount, long timeStamp, String simType, String deviceStatus, int lookupAvailable){
        this.device_number = deviceNo;
        this.device_id = deviceId;
        this.success_count = successCount;
        this.device_type = cType;
        this.timeStamp = timeStamp;
        this.simType = simType;
        this.deviceStatus = deviceStatus;
        this.lookupAvailable = lookupAvailable;
    }

    public String getDevice_number() {
        return device_number;
    }

    public String getDevice_id() {
        return device_id;
    }

    public Integer getSuccess_count() {
        return success_count;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_number(String device_number) {
        this.device_number = device_number;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public void setSuccess_count(Integer success_count) {
        this.success_count = success_count;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSimType() {
        return simType;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

    public String getDeviceStatus() { return deviceStatus;}

    public void setDeviceStatus(String deviceStatus) { this.deviceStatus = deviceStatus;}

    public int isLookupAvailable() {
        return lookupAvailable;
    }

    public void setLookupAvailable(int lookupAvailable) {
        this.lookupAvailable = lookupAvailable;
    }
}
