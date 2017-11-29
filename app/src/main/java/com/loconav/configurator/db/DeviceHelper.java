package com.loconav.configurator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.loconav.configurator.model.Device;
import com.loconav.configurator.model.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 28/11/17.
 */

public class DeviceHelper extends DBHelper {

    private static final String TABLE_DEVICE = "table_device";
    private static final String KEY_MACHINE_TYPE = "machine_type";
    private static final String KEY_MACHINE_NO = "machine_no";
    private static final String KEY_DEVICE_ID = "device_id";
    private static final String KEY_SUCCESS_COUNT = "success_count";

    public DeviceHelper() {
        super();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DEVICE_TABLE = "CREATE TABLE " + TABLE_DEVICE + "("
                + KEY_MACHINE_NO + " INTEGER,"
                + KEY_MACHINE_TYPE + " TEXT,"
                + KEY_DEVICE_ID + " TEXT PRIMARY KEY,"
                + KEY_SUCCESS_COUNT + " INTEGER" + ")";
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

    public void addDevice(Device device) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MACHINE_TYPE, device.getType());
        values.put(KEY_MACHINE_NO, device.getMachine_no()); // Contact Name
        values.put(KEY_SUCCESS_COUNT, device.getSuccess_count());
        values.put(KEY_DEVICE_ID, device.getDevice_id());
        db.insert(TABLE_DEVICE, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Device getDevice(String deviceId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DEVICE, new String[] { KEY_MACHINE_NO,
                        KEY_MACHINE_TYPE, KEY_DEVICE_ID, KEY_SUCCESS_COUNT }, KEY_MACHINE_NO + "=?",
                new String[] { deviceId }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Device device= new Device(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                Integer.parseInt(cursor.getString(3)));
        // return contact
        return device;
    }

    // Getting All Contacts
    public List<Device> getAllDevices() {
        List<Device> devicesList = new ArrayList<Device>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DEVICE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Device device= new Device();
                device.setMachine_no(Integer.parseInt(cursor.getString(0)));
                device.setType(cursor.getString(1));
                device.setDevice_id(cursor.getString(2));
                device.setMachine_no(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                devicesList.add(device);
            } while (cursor.moveToNext());
        }
        // return contact list
        return devicesList;
    }

    // Updating single contact
    public int updateDevice(Device device) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MACHINE_NO, device.getMachine_no());
        values.put(KEY_MACHINE_TYPE, device.getType());
        values.put(KEY_SUCCESS_COUNT, device.getSuccess_count());
        // updating row
        return db.update(TABLE_DEVICE, values, KEY_DEVICE_ID + " = ?",
                new String[] { device.getDevice_id() });
    }

    // Deleting single contact
    public void deleteDevice(Device device) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEVICE, KEY_DEVICE_ID + " = ?",
                new String[] { device.getDevice_id() });
        db.close();
    }
    // Getting contacts Count
    public int getDeviceCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DEVICE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

}
