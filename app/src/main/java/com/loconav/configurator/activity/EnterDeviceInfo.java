package com.loconav.configurator.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loconav.configurator.MessagesList;
import com.loconav.configurator.R;
import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EnterDeviceInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.et_device_number) EditText deviceNumber;
    @BindView(R.id.button_submit) Button submit;
    @BindView(R.id.select_device) Spinner selectDevice;
    @BindView(R.id.select_sim) Spinner selectSim;
    DeviceHelper deviceHelper = new DeviceHelper();
    SmsManager sms = SmsManager.getDefault();
    MessagesList messagesList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_device_info);
        ButterKnife.bind(this);
        messagesList = new MessagesList();
        setSelectDeviceSpinner();
        setSelectSimSpinner();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(!deviceNumber.getText().toString().equals("")) {
                    String number = deviceNumber.getText().toString();
                    String dbNumber = "+91"+number;
                    Device device = new Device();
                    device.setTimeStamp(System.currentTimeMillis());
                    device.setSuccess_count(-1);
                    device.setDevice_number(dbNumber);
                    device.setDevice_type(selectDevice.getSelectedItem().toString());
                    device.setSimType(selectSim.getSelectedItem().toString());
                    device.setDevice_id("");
                    deviceHelper.createOrUpdateDevice(device);
                    String msgToSetDeviceID = getMsgToSetDeviceId(device);
                    sms.sendTextMessage(device.getDevice_number(), null,msgToSetDeviceID,
                            null, null);
                    setResult(1);
                    finish();
                } else
                    Toast.makeText(getBaseContext(), "Please Enter Device Id", Toast.LENGTH_LONG).show();
            }
        });
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
                msgToSetDeviceID = "imei";
                break;
            case "Time Watch":
                msgToSetDeviceID = "Imei123456";
                break;
            default:
                msgToSetDeviceID = "param#";
                break;
        }
        return msgToSetDeviceID;
    }

    private void setSelectDeviceSpinner() {
        ArrayAdapter<String> deviceAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, messagesList.machineList);
        deviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectDevice.setAdapter(deviceAdapter);
    }

    private void setSelectSimSpinner() {
        ArrayAdapter<String> simAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, messagesList.simList);
        simAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSim.setAdapter(simAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
