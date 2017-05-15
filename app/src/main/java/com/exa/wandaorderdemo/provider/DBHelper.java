package com.exa.wandaorderdemo.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by song on 2017/5/12.
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final String DATA_NAME = "order_info.db";
    public static final int VERSION_CODE = 1;

    private static DBHelper dbHelper;

    DBHelper(Context context) {
        super(context,DATA_NAME,null,VERSION_CODE);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if (dbHelper == null){
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert() {

    }

}
