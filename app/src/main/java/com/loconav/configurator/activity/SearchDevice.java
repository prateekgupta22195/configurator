package com.loconav.configurator.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.loconav.configurator.R;

public class SearchDevice extends AppCompatActivity {
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_device);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        e1=(EditText)findViewById(R.id.editText4);
    }
    public	void getJson(View v) {
        String no = e1.getText().toString();
        if(no.equals("")){
            Toast.makeText(getApplicationContext(), "Enter Device ID", Toast.LENGTH_LONG).show();
        }else{
            if (no.length() == 10) {
                Intent intent = new Intent(this, LookUp.class);
                intent.putExtra("message", "00"+e1.getText().toString());
                startActivity(intent);
                return;
            }
            Intent intent = new Intent(this, LookUp.class);
            intent.putExtra("message", e1.getText().toString());
            startActivity(intent);
        }
    }
}
