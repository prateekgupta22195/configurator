package com.loconav.configurator.application;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by prateek on 28/11/17.
 */

public class AppController extends Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
    @Override
    public Context getApplicationContext() {
        appContext = super.getApplicationContext();
        return appContext;
    }

}
