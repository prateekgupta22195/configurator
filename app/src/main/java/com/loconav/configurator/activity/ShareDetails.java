package com.loconav.configurator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.loconav.configurator.R;


public class ShareDetails extends AppCompatActivity {
    int selectedId;
    RadioGroup radioSexGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup1);
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedId = radioSexGroup.getCheckedRadioButtonId();
                Intent intent = new Intent(ShareDetails.this, EnterDetails.class);
                switch (selectedId){
                    case R.id.radio0:
                        intent.putExtra("type","new");
                        break;
                    case R.id.radio1:
                        intent.putExtra("type","sim_change");
                        break;
                    case R.id.radio2:
                        intent.putExtra("type","device_change");
                        break;
                    case R.id.radio3:
                        intent.putExtra("type","vehicle_change");
                        break;
                }
                startActivity(intent);
            }
        });
    }
}


