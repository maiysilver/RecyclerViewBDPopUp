package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class myDB {
    private static SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public final static String TABLE="planetas"; // name of table
    public final static String ID="_id"; // id value
    public final static String NAME="name";  // name
    public final static String GRAVITY="gravity";


    public myDB(Context context){
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public static long createRecords(String id, String name, String grav){
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(NAME, name);
        values.put(GRAVITY, grav);
        Log.e(id, name);
        return database.insert(TABLE, null, values);
    }

    public Cursor selectRecords() {
        String[] cols = new String[] {ID, NAME, GRAVITY};
        Cursor mCursor = database.query(true, TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }
}
