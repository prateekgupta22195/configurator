package com.loconav.configurator.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;

import static com.loconav.configurator.Constants.LOOK_UP_PREFERENCES;

/**
 * Created by prateek on 28/11/17.
 */

public class AppController extends Application {
    public static Context appContext;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        sharedPreferences = getSharedPreferences(LOOK_UP_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        new DBHelper();
    }
    @Override
    public Context getApplicationContext() {
        appContext = super.getApplicationContext();
        return appContext;
    }

}
