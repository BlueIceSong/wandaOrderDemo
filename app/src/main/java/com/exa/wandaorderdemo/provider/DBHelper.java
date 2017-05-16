package com.exa.wandaorderdemo.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exa.wandaorderdemo.model.Order;

/**
 * Created by Administrator on 2017/5/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper mDBHelper;
    public static final String DATA_BASE_NAME = "order_info.db";
    public static final int VERSION_CODE = 1;

    public DBHelper(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION_CODE);
    }

    public static DBHelper getInstance(Context context) {
        if(mDBHelper == null) {
            mDBHelper = new DBHelper(context);
        }
        return mDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE order (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " customer_num CHAR, customer_name CHAR, order_num CHAR, palet_number CHAR，" +
                " WEIGHT DOUBLE, price DOUBLE, date DATE, notes CHAR, description CHAR); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
