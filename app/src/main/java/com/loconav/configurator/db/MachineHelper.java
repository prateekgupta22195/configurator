package com.loconav.configurator.db;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.loconav.configurator.model.Machine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 28/11/17.
 */

public class MachineHelper extends DBHelper {

    private static final String TABLE_MACHINE = "table_machine";
    private static final String KEY_MACHINE_NO = "machine_no";
    private static final String KEY_MACHINE_NAME = "machine_name";
    private static final String KEY_TOTAL_MSG = "total_messages";

    public MachineHelper() {
        super();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MACHINE_TABLE = "CREATE TABLE " + TABLE_MACHINE + "("
                + KEY_MACHINE_NO + " INTEGER PRIMARY KEY," + KEY_MACHINE_NAME + " TEXT,"
                + KEY_TOTAL_MSG + " INTEGER" + ")";
        db.execSQL(CREATE_MACHINE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MACHINE);
        // Create tables again
        onCreate(db);
    }

    public void addMachine(Machine machine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MACHINE_NAME, machine.getMachine_name());
        values.put(KEY_MACHINE_NO, machine.getMachine_no()); // Contact Name
        values.put(KEY_TOTAL_MSG, machine.getTotal_msg()); // Contact Phone
        // Inserting Row
        db.insert(TABLE_MACHINE, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Machine getMachine(int mNumber) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MACHINE, new String[] { KEY_MACHINE_NO,
                        KEY_MACHINE_NAME, KEY_TOTAL_MSG }, KEY_MACHINE_NO + "=?",
                new String[] { String.valueOf(mNumber) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Machine machine = new Machine(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        // return contact
        return machine;
    }

    // Getting All Contacts
    public List<Machine> getAllMachines() {
        List<Machine> machineList = new ArrayList<Machine>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MACHINE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Machine machine = new Machine();
                machine.setMachine_no(Integer.parseInt(cursor.getString(0)));
                machine.setName(cursor.getString(1));
                machine.setTotal_msg(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                machineList.add(machine);
            } while (cursor.moveToNext());
        }
        // return contact list
        return machineList;
    }

    // Updating single contact
    public int updateMachine(Machine machine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MACHINE_NAME, machine.getMachine_name());
        values.put(KEY_TOTAL_MSG, machine.getTotal_msg());
        // updating row
        return db.update(TABLE_MACHINE, values, KEY_MACHINE_NO + " = ?",
                new String[] { String.valueOf(machine.getMachine_no()) });
    }

    // Deleting single contact
    public void deleteMachine(Machine machine) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MACHINE, KEY_MACHINE_NO + " = ?",
                new String[] { String.valueOf(machine.getMachine_no()) });
        db.close();
    }
    // Getting contacts Count
    public int getMachinesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MACHINE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}
