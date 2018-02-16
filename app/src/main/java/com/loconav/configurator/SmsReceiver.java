package com.loconav.configurator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;
import com.loconav.configurator.model.Machine;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.loconav.configurator.Constants.SIM_TYPE;
import static com.loconav.configurator.MessagesList.machineMessages;
import static com.loconav.configurator.application.AppController.editor;

/**
 * Created by prateek on 10/02/18.
 */
public class SmsReceiver extends BroadcastReceiver {
    int p;
    String machine;
    private static final String TAG = "SmsReceiver";

    public static String getSimType() {
        return simType;
    }

    public static void setSimType(String simType) {
        SmsReceiver.simType = simType;
    }

    private static String simType;
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        try {
            final Bundle bundle = arg1.getExtras();
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                Log.d("SmsReceiver","pdusObj received : " + pdusObj.length);
                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[0]);
                String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                String message = currentMessage.getDisplayMessageBody();
                Log.d("SmsReceiver","Message Received: " + message);
                Device device = getDeviceByNumber(phoneNumber);
                setSimType(device.getSimType());
                if(device.getSuccess_count() == -1) {
                    retrieveAndSetDeviceID(message, device);
                    sendMessage(phoneNumber, machineMessages.get(device.getDevice_type()).get(0));
                } else  {
                    String msgTosend = messageToSend(device.getDevice_type(), device.getSuccess_count()+2);
                    Log.e("expected msg ", machineMessages.get(device.getDevice_type()).get(device.getSuccess_count()+1));
                    Log.e("original msg ", message);

                    if(isMatchFound(message,machineMessages.get(device.getDevice_type()).get(device.getSuccess_count()+1))) {
                        int finalStatus = device.getSuccess_count()+2;
                        device.setSuccess_count(finalStatus);
                        new DeviceHelper().updateDevice(device);
                        if(!msgTosend.equals("") ) {
                            sendMessage(phoneNumber, msgTosend);
                        }
                    }


                }
                notifyStatusList();
                Log.e(TAG, " final status "+  new DeviceHelper().getDevice(phoneNumber).getSuccess_count());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSim(Device device) {
        editor.putString(SIM_TYPE, device.getSimType());
        editor.commit();
    }

    static void retrieveAndSetDeviceID (String message, Device device) {
            if(device.getDevice_type().equals("TK101B")) {
                device.setDevice_id("00"+ device.getDevice_number().substring(3));
                device.setSuccess_count(0);
                new DeviceHelper().updateDevice(device);
            } else {
                Pattern p = Pattern.compile("(\\d{15})");
                Matcher m = p.matcher(message); // get a matcher object
                if(m.find()) {
                    device.setDevice_id("0"+m.group(1));
                    device.setSuccess_count(0);
                    new DeviceHelper().updateDevice(device);
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
        Pattern p = Pattern.compile(matchWith);
        Matcher m = p.matcher(receivedMessage); // get a matcher object
        if(m.find()) {
            return true;
        } else
            return false;
    }




}
