package com.exa.wandaorderdemo.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exa.wandaorderdemo.model.Order;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Song on 2017/5/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final String dbpath = "/data/data/com.exa.wandaorderdemo/databases/order_info.db";
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
        db.execSQL("CREATE TABLE "+Order.TABLE_NAME +"(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " customer_num CHAR, customer_name CHAR, order_num CHAR, palet_number CHAR，" +
                " WEIGHT DOUBLE, price DOUBLE, date DATE, notes CHAR, description CHAR); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase  createDB(Context context) {
        File path= new File(dbpath);
        if (path.exists()){
            return SQLiteDatabase.openOrCreateDatabase(dbpath, null);
        }
        AssetManager am = context.getAssets();
        FileOutputStream os = null;
        InputStream is = null;
        final File dir = new File("/data/data/com.exa.wandaorderdemo/databases");
        dir.mkdir();
        final File file = new File(dir, "order_info.db");
        try {
            is = am.open("order_info.db");
            os = new FileOutputStream(file);
            byte[] b = new byte[1024];

            int r;
            while ((r = is.read(b)) >0) {
                os.write(b, 0, r);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return createDB(context);
    }
}
