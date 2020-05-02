package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public final static String DABASE_NAME = "prince.db";
    public final static String TABLE_NAME = "TABLE1";
    public final static String COL1 = "ID";
    public final static String COL2 = "NAME";
    public final static String COL3 = "EMAIL";
    public final static String COL4 = "AGE";
    Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT,AGE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "Table is already present", Toast.LENGTH_SHORT).show();
    }


    public boolean insertData(String name, String email, String age) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put(COL2, name);
        contentValues.put(COL3, email);
        contentValues.put(COL4, age);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


//    public boolean getUpdateData(String id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor=getData(id);
//
//        if(cursor.moveToNext())
//        {
//
//        }
//
//
//    }

    public boolean updateData(String id, String name, String email, String age) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, id);
        contentValues.put(COL2, name);
        contentValues.put(COL3, email);
        contentValues.put(COL4, age);

        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});

        return true;


    }

    public Cursor getData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            Toast.makeText(context, "Data will be shown", Toast.LENGTH_SHORT).show();
            return cursor;
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            return null;
        }


    }


    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "ID=?", new String[]{id});
    }


    public Cursor viewAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;

    }

}
