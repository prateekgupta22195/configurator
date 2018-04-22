package com.loconav.configurator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loconav.configurator.activity.MainActivity;
import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;
import com.loconav.lookup.MainActivity3;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static com.loconav.lookup.Constants.DEVICE_ID;


/**
 * Created by prateek on 10/02/18.
 */
public class DeviceListAdapter extends ArrayAdapter<Device> {

    private SmsManager sms;
    private DeviceHelper deviceHelper = new DeviceHelper();
    public Map<String , Map<Integer, String>> machineMessages;
    private  MessagesList messagesList;

    public DeviceListAdapter(MainActivity mainActivity, ArrayList<Device> users) {
        super(mainActivity, 0, users);
        messagesList = new MessagesList();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        final Device device = getItem(position);
        sms = SmsManager.getDefault();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }

        // Lookup view for data population
        ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
        TextView deviceNumber = (TextView) convertView.findViewById(R.id.device_number);
        TextView deviceType = (TextView) convertView.findViewById(R.id.device_type);
        TextView time = (TextView)convertView.findViewById(R.id.time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(device.getTimeStamp());
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR);
        String am_pm;

        if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
            am_pm = " AM";
        } else {
            am_pm = " PM";
        }

        deviceNumber.setText(device.getDevice_number().toString());
        deviceType.setText(device.getDevice_type());
        messagesList.setSimType(device.getSimType());
        machineMessages = messagesList.getMachineMessages();

        time.setText(hour + ":" + minute + " " + am_pm);
//        txtTitle2.setText(machineName + "      " + statusPercentageString + "%");

        if (device.getSuccess_count() == machineMessages.get(device.getDevice_type()).size()) {
            Drawable drawable = progressBar.getProgressDrawable();
            drawable.setColorFilter(new LightingColorFilter(0xFF000000, Color.parseColor("#6B8E23")));
            progressBar.setProgressDrawable(drawable);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), MainActivity3.class);
                    intent.putExtra(DEVICE_ID, device.getDevice_id());
                    getContext().startActivity(intent);
                }
            });
        }// Return the completed view to render on screen
        else if(device.getSuccess_count() < machineMessages.get(device.getDevice_type()).size()){
            Drawable drawable = progressBar.getProgressDrawable();
            drawable.setColorFilter(new LightingColorFilter(0xFF000000, Color.RED));
            progressBar.setProgressDrawable(drawable);
            convertView.setOnClickListener(null);
        }
        progressBar.setMax(machineMessages.get(device.getDevice_type()).size());
        progressBar.setProgress(device.getSuccess_count());
        Button send_again = (Button) convertView.findViewById(R.id.resend_message);
        send_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(device.getSuccess_count() == -1) {
                    sms.sendTextMessage(device.getDevice_number(), null,getMsgToSetDeviceId(device),
                            null, null);
                }else if(device.getSuccess_count() < machineMessages.get(device.getDevice_type()).size()) {
                    sms.sendTextMessage(device.getDevice_number(), null,messageToSend(device.getDevice_type(),
                            device.getSuccess_count()-1), null, null);
                    Toast.makeText(getContext(), "Message Resent", Toast.LENGTH_LONG).show();
                }

            }
        });

        Button delete = (Button) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deviceHelper.deleteDevice(device);
                MessageEvent event = new MessageEvent();
                event.setAction(MessageEvent.Action.REFRESH_DEVICE_STATUS);
                EventBus.getDefault().post(event);
            }
        });
        return convertView;
    }

    private String getMsgToSetDeviceId(Device device) {
        String msgToSetDeviceID = "";
        if(device.getDevice_type().equals("TK101B")) {
            msgToSetDeviceID = "Number0" + device.getDevice_number().substring(3);
        } else if(device.getDevice_type().equals("MT05(top10)")) {
            msgToSetDeviceID = "111111WWW:IPN:52.33.252.113;COM:5678;";
        } else {
            msgToSetDeviceID = "param#";
        }
        return msgToSetDeviceID;
    }

    private String messageToSend (String deviceType, int messageNumber) {
        String messageToSend = "";
        if(messageNumber < machineMessages.get(deviceType).size()) {
            messageToSend = machineMessages.get(deviceType).get(messageNumber);
        }
        return messageToSend;
    }
}