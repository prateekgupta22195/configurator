package com.loconav.configurator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.loconav.configurator.sharedetailsfragmants.DeviceChange;
import com.loconav.configurator.sharedetailsfragmants.NewInstallation;
import com.loconav.configurator.sharedetailsfragmants.SimChange;
import com.loconav.configurator.sharedetailsfragmants.VehicleChange;


public class EnterDetails extends AppCompatActivity {
    CustomActionBar customActionBar ;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        customActionBar = new CustomActionBar();
        customActionBar.getActionBar(this,R.drawable.leftarrow, R.string.enter_details, true);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            String type = bundle.getString("type");
            if(type.equals("new_installation")) {
                NewInstallation f1 = new NewInstallation();
                fragmentTransaction.replace(android.R.id.content, f1);

            } else if(type.equals("sim_change")){
                SimChange f1 = new SimChange();
                fragmentTransaction.replace(android.R.id.content, f1);

            } else if(type.equals("device_change")){
                DeviceChange f1 = new DeviceChange();
                fragmentTransaction.replace(android.R.id.content, f1);

            } else if(type.equals("vehicle_change")){
                VehicleChange f1 = new VehicleChange();
                fragmentTransaction.replace(android.R.id.content, f1);
            }
            fragmentTransaction.commit();
        }
    }

}
