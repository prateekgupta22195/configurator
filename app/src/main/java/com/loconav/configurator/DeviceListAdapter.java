package com.loconav.configurator;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loconav.configurator.activity.LookUp;
import com.loconav.configurator.activity.MainActivity;
import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;
import com.loconav.configurator.model.Machine;

import org.greenrobot.eventbus.EventBus;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.loconav.configurator.MessagesList.machineMessages;

/**
 * Created by prateek on 10/02/18.
 */
public class DeviceListAdapter extends ArrayAdapter<Device> {

    private SmsManager sms;
    private ArrayList<Device> users;
    private DeviceHelper deviceHelper = new DeviceHelper();
    public DeviceListAdapter(MainActivity mainActivity, ArrayList<Device> users) {
        super(mainActivity, 0, users);
        this.users = users;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        final Device device = getItem(position);
        sms = SmsManager.getDefault();
        if (BuildConfig.VERSION_CODE>22) {
            sms = SmsManager.getSmsManagerForSubscriptionId(1);
        } else {
            sms = SmsManager.getDefault();
        }

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }

        // Lookup view for data population
        ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
        TextView deviceNumber = (TextView) convertView.findViewById(R.id.device_number);
        TextView deviceType = (TextView) convertView.findViewById(R.id.device_type);
        TextView time = (TextView)convertView.findViewById(R.id.time);
        String machineName = device.getDevice_type();
        float status = 0.0f;
        try {
            status = ((Number) NumberFormat.getInstance().parse(device.getSuccess_count().toString())).floatValue();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        float statusFraction = (status / (scriptLength));
//        int statusPercentage = (int) (statusFraction * 100);
//        String statusPercentageString = Integer.toString(statusPercentage);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(device.getTimeStamp());

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
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
        time.setText(hour + ":" + minute + " " + am_pm);
//        txtTitle2.setText(machineName + "      " + statusPercentageString + "%");

        if (device.getSuccess_count() == machineMessages.get(device.getDevice_type()).size()) {
            Drawable drawable = progressBar.getProgressDrawable();
            drawable.setColorFilter(new LightingColorFilter(0xFF000000, Color.parseColor("#6B8E23")));
            progressBar.setProgressDrawable(drawable);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), LookUp.class);
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
                if(device.getSuccess_count() < machineMessages.get(device.getDevice_type()).size())
                    sms.sendTextMessage(device.getDevice_number(), null, machineMessages.get(device.getDevice_type()).get(device.getSuccess_count()), null, null);
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
}