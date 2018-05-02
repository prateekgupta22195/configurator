package com.loconav.configurator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.loconav.configurator.Constants.USER_ID;
import static com.loconav.configurator.Utility.isStringEmptyOrNull;
import static com.loconav.configurator.app.AppController.editor;
import static com.loconav.configurator.app.AppController.sharedPreferences;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{
    EditText userID;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userID = (EditText) findViewById(R.id.user_id);
        submit = (Button) findViewById(R.id.submit);
        attachClickListener();
        fillUserId();
    }


    private void attachClickListener() {
        submit.setOnClickListener(this);
    }

    private void fillUserId() {
        userID.setText(sharedPreferences.getString(USER_ID, ""));
    }

    @Override
    public void onClick(View view) {
        if(isStringEmptyOrNull(userID.getText().toString())) {
            Toast.makeText(getBaseContext(), getString(R.string.enter_user_id), Toast.LENGTH_LONG).show();
        } else {
            editor.putString(USER_ID, userID.getText().toString());
            editor.commit();
            finish();
        }
    }
}
