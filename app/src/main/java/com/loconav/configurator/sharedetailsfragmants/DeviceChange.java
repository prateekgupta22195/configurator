package com.loconav.configurator.sharedetailsfragmants;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.loconav.configurator.CommonFunction;
import com.loconav.configurator.CustomActionBar;
import com.loconav.configurator.R;

import static com.loconav.configurator.Constants.USER_ID;
import static com.loconav.configurator.app.AppController.sharedPreferences;


/**
 * Created by prateek on 13/11/17.
 */

public class DeviceChange extends Fragment {

    EditText ownerName, vehicleNo, oldImei, newImei, oldSimNo,
            newSimNo, deviceModel, clientID;
    Button share;
    CommonFunction commonFunction;
    public View onCreateView(LayoutInflater inflater, ViewGroup vg,
                             Bundle savedInstanceState) {
        CustomActionBar customActionBar = new CustomActionBar();
        customActionBar.getActionBar((AppCompatActivity)getActivity(),R.drawable.leftarrow,R.string.device_change,true);
        View view = inflater.inflate(R.layout.devicechange, vg, false);
        ownerName = (EditText)view.findViewById(R.id.owner_name);
        vehicleNo = (EditText)view.findViewById(R.id.vehicle_no);
        oldImei = (EditText)view.findViewById(R.id.old_imei);
        newImei = (EditText)view.findViewById(R.id.new_imei);
        oldSimNo = (EditText)view.findViewById(R.id.old_sim_no);
        newSimNo = (EditText)view.findViewById(R.id.new_sim_no);
        deviceModel = (EditText)view.findViewById(R.id.device_model);
        clientID = (EditText)view.findViewById(R.id.client_id);
        share = (Button)view.findViewById(R.id.share);
        commonFunction = new CommonFunction();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commonFunction.validate(new EditText[]{ownerName, vehicleNo,
                        oldImei, newImei, oldSimNo, newSimNo, deviceModel})) {
                    String message = "Device Change" + "\n";
                    message += "Owner's name: "+ ownerName.getText().toString() + "\n";
                    message += "Vehicle  no.: "+ vehicleNo.getText().toString() + "\n";
                    message += "Old Imei No: "+ oldImei.getText().toString() + "\n";
                    message += "New Imei No: "+ newImei.getText().toString() + "\n";
                    message += "Old Sim No: "+ oldSimNo.getText().toString() + "\n";
                    message += "New Sim No: "+ newSimNo.getText().toString() + "\n";
                    message += "Device Model: " + deviceModel.getText().toString()+ "\n";
                    message += "Client ID: " + clientID.getText().toString()+ "\n";
                    message += "USER ID: " + sharedPreferences.getString(USER_ID, "");
                    commonFunction.sendAppMsg(getContext(), message);
                }

            }
        });
        String deviceId = sharedPreferences.getString("deviceid","");
        commonFunction.setDeviceId(newSimNo, newImei, deviceId);
        return view;
    }
}
