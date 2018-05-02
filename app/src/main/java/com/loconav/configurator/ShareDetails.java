package com.loconav.configurator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class ShareDetails extends AppCompatActivity {

    int selectedId;
    RadioGroup radioSexGroup;
    CustomActionBar customActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);
        customActionBar = new CustomActionBar();
        customActionBar.getActionBar(this, R.drawable.leftarrow, R.string.share_details, true );

        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup1);
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedId = radioSexGroup.getCheckedRadioButtonId();
                Intent intent = new Intent(ShareDetails.this, EnterDetails.class);
                if(selectedId == R.id.radio0) {
                    intent.putExtra("type","new_installation");
                } else if(selectedId == R.id.radio1) {
                    intent.putExtra("type","sim_change");
                } else if (selectedId == R.id.radio2) {
                    intent.putExtra("type","device_change");
                } else if (selectedId == R.id.radio3) {
                    intent.putExtra("type","vehicle_change");
                }
                startActivity(intent);
            }
        });
    }
}
