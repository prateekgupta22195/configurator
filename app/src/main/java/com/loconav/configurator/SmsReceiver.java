package com.loconav.configurator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by prateek on 10/02/18.
 */
public class SmsReceiver extends BroadcastReceiver {
    int p;
    String machine;

    private static final String TAG = "SmsReceiver";
    public static Map<String , Map<Integer, String>> machineMessages;

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        try {
            MessagesList messagesList = new MessagesList();
            final Bundle bundle = arg1.getExtras();
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                Log.d("SmsReceiver","pdusObj received : " + pdusObj.length);
                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[0]);
                String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                String message = currentMessage.getDisplayMessageBody();
                Log.d("SmsReceiver","Message Received: " + message);
                Device device = getDeviceByNumber(phoneNumber);
                if(device != null) {
                    String deviceType = device.getDevice_type();
                    int status = device.getSuccess_count();
                    messagesList.setSimType(device.getSimType());
                    machineMessages = messagesList.getMachineMessages();
                    if(status == -1) {
                        retrieveAndSetDeviceID(message, device);
                        if(device.getSuccess_count() == 0) {
                            sendMessage(phoneNumber, machineMessages.get(deviceType).get(0));
                            incrementDeviceStatus(device);
                        }
                    } else {
                        if(isMatchFound(message, machineMessages.get(deviceType).get(status))) {
                            incrementDeviceStatus(device);
                            String msgTosend = messageToSend(device.getDevice_type(),status+1);
                            if(!msgTosend.equals("")) {
                                sendMessage(phoneNumber, msgTosend);
                                incrementDeviceStatus(device);
                            }
                        }
                    }
                    notifyStatusList();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void retrieveAndSetDeviceID (String message, Device device) {
        switch (device.getDevice_type()) {
            case "TK101B":
                device.setDevice_id("00" + device.getDevice_number().substring(3));
                device.setSuccess_count(device.getSuccess_count() + 1);
                new DeviceHelper().updateDevice(device, true);
                break;
            case "MT05(top10)": {
                Pattern p = Pattern.compile("(\\d{14})");
                Matcher m = p.matcher(message); // get a matcher object

                if (m.find()) {
                    device.setDevice_id(m.group(1));
                    device.setSuccess_count(device.getSuccess_count() + 1);
                    new DeviceHelper().updateDevice(device, true);
                }
                break;
            }
            case "M2C": {
                Pattern p = Pattern.compile("(\\d{15})");
                Matcher m = p.matcher(message); // get a matcher object

                if (m.find()) {
                    device.setDevice_id(m.group(1));
                    device.setSuccess_count(device.getSuccess_count() + 1);
                    new DeviceHelper().updateDevice(device, true);
                }
                break;
            }
            default: {
                Pattern p = Pattern.compile("(\\d{15})");
                Matcher m = p.matcher(message); // get a matcher object

                if (m.find()) {
                    device.setDevice_id("0" + m.group(1));
                    device.setSuccess_count(device.getSuccess_count() + 1);
                    new DeviceHelper().updateDevice(device, true);
                }
                break;
            }
        }
    }

    private void sendMessage(String phoneNumber, String messageToSend) {
        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, messageToSend, null, null);
    }

    private Device getDeviceByNumber(String phoneNumber) {
        DeviceHelper deviceHelper = new DeviceHelper();
        Device device = deviceHelper.getDevice(phoneNumber);
        return device;
    }

    private void notifyStatusList() {
        MessageEvent event = new MessageEvent();
        event.setAction(MessageEvent.Action.REFRESH_DEVICE_STATUS);
        EventBus.getDefault().postSticky(event);
    }

    private String messageToSend (String deviceType, int messageNumber) {
        String messageToSend = "";
        if(messageNumber < machineMessages.get(deviceType).size()) {
            messageToSend = machineMessages.get(deviceType).get(messageNumber);
        }
        return messageToSend;
    }

    private boolean isMatchFound(String receivedMessage, String matchWith) {
        matchWith = matchWith.toLowerCase();
        Pattern p = Pattern.compile(matchWith);
        receivedMessage = receivedMessage.toLowerCase();
        Matcher m = p.matcher(receivedMessage); // get a matcher object
        if(m.find()) {
            return true;
        } else
            return false;
    }

    private void incrementDeviceStatus(Device device){
        int newStatus = device.getSuccess_count();
        device.setSuccess_count(++newStatus);
        new DeviceHelper().updateDevice(device, true);
    }

}
