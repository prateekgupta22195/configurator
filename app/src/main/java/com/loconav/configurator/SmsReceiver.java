package com.loconav.configurator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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

import static com.loconav.configurator.MessagesList.createMap;
import static com.loconav.configurator.MessagesList.machineMessages;

/**
 * Created by prateek on 10/02/18.
 */
public class SmsReceiver extends BroadcastReceiver {
    int p;
    String machine;
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
                    SmsManager sms= SmsManager.getDefault();
                    DeviceHelper deviceHelper = new DeviceHelper();
                    Device device = deviceHelper.getDevice(phoneNumber);
                    if(device!=null) {
                        retrieveAndSetDeviceID(message, device);
                        int status = device.getSuccess_count();
                        status = status + 2;
                        device.setSuccess_count(status);
                        deviceHelper.updateDevice(device);
                        MessageEvent event = new MessageEvent();
                        event.setAction(MessageEvent.Action.REFRESH_DEVICE_STATUS);
                        EventBus.getDefault().postSticky(event);
                        String messageToSend = machineMessages.get(device.getDevice_type()).get(device.getSuccess_count());
                        if(messageToSend!= null) {
                            sms.sendTextMessage(phoneNumber, null, messageToSend, null, null);
                        }
                    } else
                        return;

                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


    static void retrieveAndSetDeviceID (String message, Device device) {
            if(device.getDevice_type() == "TK101B") {
                device.setDevice_id("00"+ device.getDevice_number());
                new DeviceHelper().updateDevice(device);
            } else {
                Pattern p = Pattern.compile("(\\d{15})");
                Matcher m = p.matcher(message); // get a matcher object
                if(m.find()) {
                    device.setDevice_id(m.group(1));
                    new DeviceHelper().updateDevice(device);
                }
            }
    }
}
