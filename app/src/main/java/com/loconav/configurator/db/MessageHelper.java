package com.loconav.configurator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.loconav.configurator.model.Machine;
import com.loconav.configurator.model.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 28/11/17.
 */

public class MessageHelper extends DBHelper {
    private static final String TABLE_MESSAGE = "table_message";
    private static final String KEY_MSG_TXT = "msg_text";
    private static final String KEY_MACHINE_NO = "machine_no";
    private static final String KEY_MSG_NO = "message_no";

    public MessageHelper() {
        super();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_MESSAGE + "("
                + KEY_MACHINE_NO + " INTEGER," + KEY_MSG_TXT + " TEXT,"
                + KEY_MSG_NO + " INTEGER" + ")";
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGE);
        // Create tables again
        onCreate(db);
    }

    public void addMessage(Messages message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MSG_TXT, message.getMSG_TEXT());
        values.put(KEY_MACHINE_NO, message.getMACHINE_NO()); // Contact Name
        values.put(KEY_MSG_NO, message.getMSG_NO()); // Contact Phone
        // Inserting Row
        db.insert(TABLE_MESSAGE, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Messages getMesage(int mNumber) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MESSAGE, new String[] { KEY_MSG_NO,
                        KEY_MSG_TXT, KEY_MACHINE_NO }, KEY_MACHINE_NO + "=?",
                new String[] { String.valueOf(mNumber) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Messages message = new Messages(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        // return contact
        return message;
    }

    // Getting All Contacts
    public List<Messages> getAllMessages() {
        List<Messages> messagesList = new ArrayList<Messages>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MESSAGE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Messages messages = new Messages();
                messages.setMSG_NO(Integer.parseInt(cursor.getString(0)));
                messages.setMSG_TEXT(cursor.getString(1));
                messages.setMACHINE_NO(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                messagesList.add(messages);
            } while (cursor.moveToNext());
        }
        // return contact list
        return messagesList;
    }

    // Updating single contact
    public int updateMessages(Messages messages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MSG_TXT, messages.getMACHINE_NO());
        values.put(KEY_MSG_NO, messages.getMSG_NO());
        // updating row
        return db.update(TABLE_MESSAGE, values, KEY_MACHINE_NO + " = ?",
                new String[] { String.valueOf(messages.getMACHINE_NO()) });
    }

    // Deleting single contact
    public void deleteMessages(Messages messages) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MESSAGE, KEY_MACHINE_NO + " = ?",
                new String[] { String.valueOf(messages.getMACHINE_NO()) });
        db.close();
    }
    // Getting contacts Count
    public int getMessageCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MESSAGE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

}
