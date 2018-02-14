package com.loconav.configurator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.loconav.configurator.R;

public class EnterDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        LinearLayout newinstallation = (LinearLayout) findViewById(R.id.newinstallation);
        LinearLayout simchange = (LinearLayout) findViewById(R.id.simchange);
        LinearLayout devicechange = (LinearLayout) findViewById(R.id.devicechange);
        LinearLayout vehiclechange = (LinearLayout) findViewById(R.id.vehiclechange);

        final EditText dealer_name = (EditText) findViewById(R.id.dealer_name);
        final EditText owner_name = (EditText) findViewById(R.id.owner_name);
        final EditText contact_no = (EditText) findViewById(R.id.contact_no);
        final EditText location = (EditText) findViewById(R.id.location);
        final EditText registration_no = (EditText) findViewById(R.id.registration_no);
        final EditText chassis_no = (EditText) findViewById(R.id.chassis_no);
        final  EditText manufacture = (EditText) findViewById(R.id.manufacture);
        final EditText model = (EditText) findViewById(R.id.model);
        final EditText type_of_goods = (EditText) findViewById(R.id.type_of_goods);
        final EditText odometer_reading = (EditText) findViewById(R.id.odometer_reading);
        final EditText sim_no = (EditText) findViewById(R.id.sim_no);
        final EditText imei = (EditText) findViewById(R.id.imei);
        final EditText device_model = (EditText) findViewById(R.id.device_model);
        final RadioGroup radioSexGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        Button share = (Button) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                if(selectedId != R.id.new_customer && selectedId != R.id.old_customer){
                    Toast.makeText(EnterDetails.this, "Please Select Customer New or Old",Toast.LENGTH_LONG).show();
                    radioSexGroup.setFocusable(true);
                }else if(dealer_name.getText().toString().equals("")){
                    dealer_name.setError("Cannot Be Empty");
                    dealer_name.setFocusable(true);
                    Toast.makeText(EnterDetails.this, "Dealer Name Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(owner_name.getText().toString().equals("")){
                    dealer_name.setError(null);
                    owner_name.setError("Cannot Be Empty");
                    owner_name.setFocusable(true);
                    Toast.makeText(EnterDetails.this, "Owner Name Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(contact_no.getText().toString().equals("")){
                    dealer_name.setError(null);
                    owner_name.setError(null);
                    contact_no.setError("Cannot Be Empty");
                    contact_no.requestFocus();
                    Toast.makeText(EnterDetails.this, "Contact No Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(location.getText().toString().equals("")){
                    dealer_name.setError(null);
                    owner_name.setError(null);
                    contact_no.setError(null);
                    location.setError("Cannot Be Empty");
                    location.requestFocus();
                    Toast.makeText(EnterDetails.this, "Location Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(manufacture.getText().toString().equals("")){
                    dealer_name.setError(null);
                    owner_name.setError(null);
                    contact_no.setError(null);
                    location.setError(null);
                    manufacture.setError("Cannot Be Empty");
                    manufacture.requestFocus();
                    Toast.makeText(EnterDetails.this, "Manufacturer Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(model.getText().toString().equals("")){
                    dealer_name.setError(null);
                    owner_name.setError(null);
                    contact_no.setError(null);
                    location.setError(null);
                    manufacture.setError(null);
                    model.setError("Cannot Be Empty");
                    model.requestFocus();
                    Toast.makeText(EnterDetails.this, "Model Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(sim_no.getText().toString().equals("")){
                    dealer_name.setError(null);
                    owner_name.setError(null);
                    contact_no.setError(null);
                    location.setError(null);
                    manufacture.setError(null);
                    model.setError(null);
                    sim_no.setError("Cannot Be Empty");
                    sim_no.requestFocus();
                    Toast.makeText(EnterDetails.this, "Sim No Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(imei.getText().toString().equals("")){
                    dealer_name.setError(null);
                    owner_name.setError(null);
                    contact_no.setError(null);
                    location.setError(null);
                    manufacture.setError(null);
                    model.setError(null);
                    sim_no.setError(null);
                    imei.setError("Cannot Be Empty");
                    imei.requestFocus();
                    Toast.makeText(EnterDetails.this, "Imei Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else{
                    dealer_name.setError(null);
                    owner_name.setError(null);
                    contact_no.setError(null);
                    location.setError(null);
                    manufacture.setError(null);
                    model.setError(null);
                    sim_no.setError(null);
                    imei.setError(null);
                    String message = "";
                    if(selectedId == R.id.new_customer){
                        message += "New Customer" + "\n";
                    }else{
                        message += "Old Customer" + "\n";
                    }
                    message += "Dealer's name: "+dealer_name.getText().toString() + "\n";
                    message += "Owner's name: "+owner_name.getText().toString() + "\n";
                    message += "Contact no: "+contact_no.getText().toString() + "\n";
                    message += "Location: "+location.getText().toString() + "\n";
                    message += "Registration no.: "+registration_no.getText().toString()+ "\n";
                    message += "Chassis no: "+chassis_no.getText().toString() + "\n";
                    message += "Manufacture: "+manufacture.getText().toString() + "\n";
                    message += "Model: "+model.getText().toString() + "\n";
                    message += "Type of goods: "+type_of_goods.getText().toString() + "\n";
                    message += "Odometer Reading: "+odometer_reading.getText().toString() + "\n";
                    message += "Sim No: "+sim_no.getText().toString() + "\n";
                    message += "IMEI: "+imei.getText().toString() + "\n";
                    message += "Device Model: "+device_model.getText().toString();
                    sendAppMsg(message);
                }
            }
        });

        final EditText owner_namesc = (EditText) findViewById(R.id.owner_namesc);
        final EditText vehicle_nosc = (EditText) findViewById(R.id.vehicle_nosc);
        final EditText old_sim_no_sc = (EditText) findViewById(R.id.old_sim_no_sc);
        final EditText new_sim_no_sc = (EditText) findViewById(R.id.new_sim_no_sc);
        Button share_sc = (Button) findViewById(R.id.share_sc);
        share_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(owner_namesc.getText().toString().equals("")){
                    owner_namesc.setError("Cannot Be Empty");
                    owner_namesc.requestFocus();
                    Toast.makeText(EnterDetails.this, "Owner Name Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(vehicle_nosc.getText().toString().equals("")){
                    owner_namesc.setError(null);
                    vehicle_nosc.setError("Cannot Be Empty");
                    vehicle_nosc.requestFocus();
                    Toast.makeText(EnterDetails.this, "Vehicle Name Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(old_sim_no_sc.getText().toString().equals("")){
                    owner_namesc.setError(null);
                    vehicle_nosc.setError(null);
                    old_sim_no_sc.setError("Cannot Be Empty");
                    old_sim_no_sc.requestFocus();
                    Toast.makeText(EnterDetails.this, "Old Sim No Cannot Be Empty",Toast.LENGTH_LONG).show();
                }else if(new_sim_no_sc.getText().toString().equals("")) {
                    owner_namesc.setError(null);
                    vehicle_nosc.setError(null);
                    old_sim_no_sc.setError(null);
                    new_sim_no_sc.setError("Cannot Be Empty");
                    new_sim_no_sc.requestFocus();
                    Toast.makeText(EnterDetails.this, "New Sim No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else{
                    owner_namesc.setError(null);
                    vehicle_nosc.setError(null);
                    old_sim_no_sc.setError(null);
                    new_sim_no_sc.setError(null);
                    String message = "Sim Change" + "\n";
                    message += "Owner's name: "+ owner_namesc.getText().toString() + "\n";
                    message += "Vehicle  no.: "+ vehicle_nosc.getText().toString() + "\n";
                    message += "Old Sim No: "+ old_sim_no_sc.getText().toString() + "\n";
                    message += "New Sim No: "+ new_sim_no_sc.getText().toString() + "\n";
                    sendAppMsg(message);
                }
            }
        });

        final EditText old_vehicle_no = (EditText) findViewById(R.id.old_vehicle_no);
        final EditText new_vehicle_no = (EditText) findViewById(R.id.new_vehicle_no);
        final EditText sim_no_vc = (EditText) findViewById(R.id.sim_no_vc);
        Button share_vc = (Button) findViewById(R.id.share_vc);
        share_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(old_vehicle_no.getText().toString().equals("")){
                    old_vehicle_no.setError("Cannot Be Empty");
                    old_vehicle_no.requestFocus();
                    Toast.makeText(EnterDetails.this, "Old Vehicle No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else if(new_vehicle_no.getText().toString().equals("")){
                    old_vehicle_no.setError(null);
                    new_vehicle_no.setError("Cannot Be Empty");
                    new_vehicle_no.requestFocus();
                    Toast.makeText(EnterDetails.this, "New Vehicle No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else if(sim_no_vc.getText().toString().equals("")){
                    old_vehicle_no.setError(null);
                    new_vehicle_no.setError(null);
                    sim_no_vc.setError("Cannot Be Empty");
                    sim_no_vc.requestFocus();
                    Toast.makeText(EnterDetails.this, "Sim No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else{
                    old_vehicle_no.setError(null);
                    new_vehicle_no.setError(null);
                    sim_no_vc.setError(null);
                    String message = "Vehicle number Change"+"\n";
                    message += "Old Vehicle No.: " + old_vehicle_no.getText().toString() + "\n";
                    message += "New Vehicle No.: " + new_vehicle_no.getText().toString() + "\n";
                    message += "Sim no.: " + sim_no_vc.getText().toString() + "\n";
                    sendAppMsg(message);
                }
            }
        });

        final EditText vehicle_no_dc = (EditText) findViewById(R.id.vehicle_no_dc);
        final EditText old_device_no = (EditText) findViewById(R.id.old_device_no);
        final EditText new_device_no = (EditText) findViewById(R.id.new_device_no);
        final EditText old_sim_no_dc = (EditText) findViewById(R.id.old_sim_no_dc);
        final EditText new_sim_no_dc = (EditText) findViewById(R.id.new_sim_no_dc);
        Button share_dc = (Button) findViewById(R.id.share_dc);
        share_dc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vehicle_no_dc.getText().toString().equals("")){
                    vehicle_no_dc.setError("Cannot Be Empty");
                    vehicle_no_dc.requestFocus();
                    Toast.makeText(EnterDetails.this, "Vehicle No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else if(old_device_no.getText().toString().equals("")){
                    vehicle_no_dc.setError(null);
                    old_device_no.setError("Cannot Be Empty");
                    old_device_no.requestFocus();
                    Toast.makeText(EnterDetails.this, "Old IMEI No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else if(new_device_no.getText().toString().equals("")){
                    vehicle_no_dc.setError(null);
                    old_device_no.setError(null);
                    new_device_no.setError("Cannot Be Empty");
                    new_device_no.requestFocus();
                    Toast.makeText(EnterDetails.this, "New IMEI No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else if(old_sim_no_dc.getText().toString().equals("")){
                    vehicle_no_dc.setError(null);
                    old_device_no.setError(null);
                    new_device_no.setError(null);
                    old_sim_no_dc.setError("Cannot Be Empty");
                    old_sim_no_dc.requestFocus();
                    Toast.makeText(EnterDetails.this, "Old Sim No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else if(new_sim_no_dc.getText().toString().equals("")){
                    vehicle_no_dc.setError(null);
                    old_device_no.setError(null);
                    new_device_no.setError(null);
                    old_sim_no_dc.setError(null);
                    new_sim_no_dc.setError("Cannot Be Empty");
                    new_sim_no_dc.requestFocus();
                    Toast.makeText(EnterDetails.this, "New Sim No Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else {
                    vehicle_no_dc.setError(null);
                    old_device_no.setError(null);
                    new_device_no.setError(null);
                    old_sim_no_dc.setError(null);
                    new_sim_no_dc.setError(null);
                    String message = "Device Change" + "\n";
                    message += "Vehicle no.: " + vehicle_no_dc.getText().toString() + "\n";
                    message += "Old IMEI no.: " + old_device_no.getText().toString() + "\n";
                    message += "New IMEI no.: " + new_device_no.getText().toString() + "\n";
                    message += "Old Sim No.: " + old_sim_no_dc.getText().toString() + "\n";
                    message += "New Sim No.: " + new_sim_no_dc.getText().toString() + "\n";
                    sendAppMsg(message);
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String type = bundle.getString("type");
            if(type.equals("new")) {
                newinstallation.setVisibility(View.VISIBLE);
                simchange.setVisibility(View.GONE);
                devicechange.setVisibility(View.GONE);
                vehiclechange.setVisibility(View.GONE);
            } else if(type.equals("sim_change")){
                newinstallation.setVisibility(View.GONE);
                simchange.setVisibility(View.VISIBLE);
                devicechange.setVisibility(View.GONE);
                vehiclechange.setVisibility(View.GONE);
            } else if(type.equals("device_change")){
                newinstallation.setVisibility(View.GONE);
                simchange.setVisibility(View.GONE);
                devicechange.setVisibility(View.VISIBLE);
                vehiclechange.setVisibility(View.GONE);
            } else if(type.equals("vehicle_change")){
                newinstallation.setVisibility(View.GONE);
                simchange.setVisibility(View.GONE);
                devicechange.setVisibility(View.GONE);
                vehiclechange.setVisibility(View.VISIBLE);
            }
        }
    }
    public void sendAppMsg(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = message;

        // change with required  application package
        intent.setPackage("com.whatsapp");

        if (intent != null) {
            intent.putExtra(Intent.EXTRA_TEXT, text);//
            startActivity(Intent.createChooser(intent, text));
        } else {
            Toast.makeText(this, "Whatapp not found", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
