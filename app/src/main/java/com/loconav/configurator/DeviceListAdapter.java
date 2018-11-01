package com.loconav.configurator;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loconav.configurator.activity.MainActivity;
import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static com.loconav.configurator.Constants.DEVICE_ID;


/**
 * Created by prateek on 10/02/18.
 */
public class DeviceListAdapter extends ArrayAdapter<Device> {

    private SmsManager sms;
    private DeviceHelper deviceHelper = new DeviceHelper();
    public Map<String , Map<Integer, String>> machineMessages;
    private  MessagesList messagesList;

    public DeviceListAdapter(Context context, ArrayList<Device> users) {
        super(context, 0, users);
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
        ImageView lookupAvailable = convertView.findViewById(R.id.look_up);
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
        messagesList.setPassword(device.getPassword());
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
        if(device.isLookupAvailable() == 1) {
            lookupAvailable.setVisibility(View.VISIBLE);
        } else{
            lookupAvailable.setVisibility(View.INVISIBLE);
        }
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
        switch (device.getDevice_type()) {
            case "TK101B":
                msgToSetDeviceID = "Number0" + device.getDevice_number().substring(3);
                break;
            case "MT05(top10)":
                msgToSetDeviceID = "111111WWW:IPN:52.33.252.113;COM:5678;";
                break;
            case "GT-05":
                msgToSetDeviceID = "Check123456";
                break;
            case "L-100":
                msgToSetDeviceID = "GETGPS<6906>";
                break;
            case "M2C":
                msgToSetDeviceID = "1,M2C,300.0=?,301.0=?,302.0=?,303.1=?,305.0=?,306.0=?";
                break;
            case "ST903":
                msgToSetDeviceID = "Rconf";
                break;
            case "iTriangle":
                msgToSetDeviceID = "%CHKCFG%aquila123";
                break;
            case "JV200":
                msgToSetDeviceID = "Param,666666#";
                break;
            case "Zenda ZDVT2":
                msgToSetDeviceID = "Param,0000#";
                break;
            case "Ruptela":
                msgToSetDeviceID = " imei";
                break;
            case "Time Watch":
                msgToSetDeviceID = "Imei123456";
                break;
            case "GT02 Password":
                msgToSetDeviceID  = "Param," + device.getPassword() + "#";
            case "WeTrack Password":
                msgToSetDeviceID = "Param," + device.getPassword() + "#";
            case "GT02":
                msgToSetDeviceID = "Param,666666#";
            case "G200 Portable":
                msgToSetDeviceID = "Check123456";
            default:
                msgToSetDeviceID = "param#";
                break;
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