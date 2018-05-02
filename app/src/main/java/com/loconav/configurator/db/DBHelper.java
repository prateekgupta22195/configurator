package com.loconav.configurator.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.loconav.configurator.app.AppController;

/**
 * Created by prateek on 28/11/17.
 */

public abstract class DBHelper extends SQLiteOpenHelper {
    private static final Integer DB_VERSION = 3;
    private static final String DB_NAME = "db_confi";

    public DBHelper() {
        super(AppController.appContext, DB_NAME , null, DB_VERSION);
    }

    @Override
    public abstract void onCreate(SQLiteDatabase db);

    @Override
    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
