package com.jmacek.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fitness.db";
    public static final String TABLE_NAME = "training";
    public static final String id = "ID";
    public static final String sport = "SPORT";
    public static final String date = "DATE";
    public static final String distance = "DISTANCE";
    public static final String time = "TIME";
    public static final String max_speed = "MAX_SPEED";
    public static final String heart_rate = "HR";
    public static final String calories = "CALORIES";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,SPORT TEXT,DATE TEXT,DISTANCE TEXT,TIME TEXT,MAX_SPEED TEXT,HR TEXT,CALORIES TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Map<String, String> data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sport, data.get("sport"));
        contentValues.put(date, data.get("date"));
        contentValues.put(distance, data.get("distance"));
        contentValues.put(time, data.get("time"));
        contentValues.put(max_speed, data.get("max_speed"));
        contentValues.put(heart_rate, data.get("heart_rate"));
        contentValues.put(calories, data.get("calories"));
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<String> getDates(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select date from  " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            do {
                list.add(res.getString(0));
            }while (res.moveToNext());
        }
        res.close();
        db.close();
        return list;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getSelectedRow(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM training where date='" + date + "'", null);
        return res;
    }

    public Integer deleteSelectedRow(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "date=?";
        String[] whereArgs = new String[] { date };
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public HashMap<Date, Double> getStats(String sport, String stat) throws ParseException {
        HashMap<Date, Double> data = new HashMap<Date, Double>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from training where sport='" + sport + "'", null);
        Integer index = res.getColumnIndex(stat);
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

        if (res.moveToFirst()) {
            do {
                data.put(formatter.parse(res.getString(2)), res.getDouble(index));
            }while (res.moveToNext());
        }
        res.close();
        db.close();
        return data;
    }

}
