package com.loconav.configurator.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.loconav.configurator.model.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 28/11/17.
 */

public class DeviceHelper extends DBHelper {

    private static final String TABLE_DEVICE = "table_device";
    private static final String KEY_MACHINE_TYPE = "machine_type";
    private static final String KEY_DEVICE_NO = "machine_no";
    private static final String KEY_DEVICE_ID = "device_id";
    private static final String KEY_SUCCESS_COUNT = "success_count";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_SIM_TYPE = "sim_type";
    private static final String KEY_DEVICE_STATUS = "device_status";
    private static final String KEY_LOOKUP_AVAILABLE = "lookup_avalaible";
    private static final String KEY_PASSWORD = "password";


    public DeviceHelper() {
        super();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DEVICE_TABLE = "CREATE TABLE " + TABLE_DEVICE + "("
                + KEY_DEVICE_NO + " TEXT,"
                + KEY_TIMESTAMP + " INTEGER,"
                + KEY_MACHINE_TYPE + " TEXT,"
                + KEY_DEVICE_ID + " TEXT,"
                + KEY_SUCCESS_COUNT + " INTEGER,"
                + KEY_SIM_TYPE + " TEXT,"
                + KEY_DEVICE_STATUS + " TEXT,"
                + KEY_LOOKUP_AVAILABLE + " INTEGER,"
                + KEY_PASSWORD + " TEXT"
                + ")";
        db.execSQL(CREATE_DEVICE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE);
        // Create tables again
        onCreate(db);
    }

    public void createOrUpdateDevice(Device device) {

        if(getDevice(device.getDevice_number())== null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_MACHINE_TYPE, device.getDevice_type());
            values.put(KEY_DEVICE_NO, device.getDevice_number()); // Contact Name
            values.put(KEY_SUCCESS_COUNT, device.getSuccess_count());
            values.put(KEY_DEVICE_ID, device.getDevice_id());
            values.put(KEY_TIMESTAMP, device.getTimeStamp());
            values.put(KEY_SIM_TYPE, device.getSimType());
            values.put(KEY_DEVICE_STATUS, "ACTIVE");
            values.put(KEY_LOOKUP_AVAILABLE, 0);
            values.put(KEY_PASSWORD, device.getPassword());
            db.insert(TABLE_DEVICE, null, values);
            db.close();
        } else
            updateDevice(device, true);
 // Closing database connection
    }

    // Getting single contact
    public Device getDevice(String deviceNumber) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DEVICE, new String[]{
                KEY_DEVICE_NO,
                KEY_MACHINE_TYPE,
                KEY_DEVICE_ID,
                KEY_SUCCESS_COUNT,
                KEY_TIMESTAMP,
                KEY_SIM_TYPE,
                KEY_DEVICE_STATUS,
                KEY_LOOKUP_AVAILABLE,
                KEY_PASSWORD
                }, KEY_DEVICE_NO + "=?",
                new String[] { deviceNumber }, null, null, null, null);
        Log.e("cursor count ", cursor.getCount() + "");
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Device device= new Device((cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) ,
                    cursor.getLong(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getInt(7), cursor.getString(8));
            return device;
        } else {
            return null;
        }

    }

    public Device getDeviceByID(String deviceID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DEVICE, new String[]{
                KEY_DEVICE_NO,
                KEY_MACHINE_TYPE,
                KEY_DEVICE_ID,
                KEY_SUCCESS_COUNT,
                KEY_TIMESTAMP,
                KEY_SIM_TYPE,
                KEY_DEVICE_STATUS,
                KEY_LOOKUP_AVAILABLE,
                KEY_PASSWORD
                }, KEY_DEVICE_ID + "=?",
                new String[] { deviceID }, null, null, null, null);
        Log.e("cursor count ", cursor.getCount() + "");
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Device device= new Device((cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) ,
                    cursor.getLong(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getString(8));
            return device;
        } else {
            return null;
        }

    }

    // Getting All Contacts
    public List<Device> getAllDevices(String deviceStatus) {
        List<Device> devicesList = new ArrayList<Device>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DEVICE +" WHERE " + KEY_DEVICE_STATUS + " = " + "'" +deviceStatus + "'" + " ORDER BY "+ KEY_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Device device= new Device();
                device.setDevice_number((cursor.getString(0)));
                device.setTimeStamp(cursor.getLong(1));
                device.setDevice_type(cursor.getString(2));
                device.setDevice_id(cursor.getString(3));
                device.setSuccess_count((cursor.getInt(4)));
                device.setSimType(cursor.getString(5));
                device.setDeviceStatus(cursor.getString(6));
                device.setLookupAvailable(cursor.getInt(7));
                device.setPassword(cursor.getString(8));
                // Adding contact to list
                devicesList.add(device);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return devicesList;
    }

    // Updating single contact
    public void updateDevice(Device device, boolean updateTimeStamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DEVICE_NO, device.getDevice_number());
        values.put(KEY_MACHINE_TYPE, device.getDevice_type());
        values.put(KEY_SUCCESS_COUNT, device.getSuccess_count());
        values.put(KEY_DEVICE_ID, device.getDevice_id());
        values.put(KEY_TIMESTAMP, System.currentTimeMillis());
        values.put(KEY_SIM_TYPE, device.getSimType());
        values.put(KEY_DEVICE_STATUS, device.getDeviceStatus());
        values.put(KEY_LOOKUP_AVAILABLE, device.isLookupAvailable());
        values.put(KEY_PASSWORD, device.getPassword());
        // updating row
        db.update(TABLE_DEVICE, values, KEY_DEVICE_NO + " = ?",
                new String[] { device.getDevice_number() });
    }

    // Deleting single contact
    public void deleteDevice(Device device) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEVICE, KEY_DEVICE_NO + " = ?",
                new String[] { device.getDevice_number() });
        db.close();
    }
    // Getting contacts Count

    public List<String> getAllUnavailLookupDevices() {
        List<String> devicesList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_DEVICE +" WHERE " + KEY_LOOKUP_AVAILABLE + " = " + "'" + 0 + "'" + " ORDER BY "+ KEY_TIMESTAMP + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                if(!cursor.getString(3).equals("")) {
                    devicesList.add(cursor.getString(3));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return devicesList;
    }


}
