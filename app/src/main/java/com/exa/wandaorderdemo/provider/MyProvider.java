package com.exa.wandaorderdemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.exa.wandaorderdemo.model.Order;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Song on 2017/5/15.
 */

public class MyProvider extends ContentProvider {
    private Context mContext;
    private DBHelper mDBHelper;
    public static UriMatcher matcher;

    public static final String DATA_BASE_AUTHORITY = "com.exa.wandaorderdemo";
    public static final String VISIT_DB_URL = "content://" + DATA_BASE_AUTHORITY + "/";
    public static final int OREDER_CODE = 0;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(DATA_BASE_AUTHORITY,"order",OREDER_CODE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDBHelper = DBHelper.getInstance(mContext);
        File file = new File("/data/data/com.exa.wandaorderdemo/databases/test_order.db");
        if (!file.exists()) {
            createDbFromAssets();
        }
        return true;
    }

    private void createDbFromAssets() {
        AssetManager am = mContext.getAssets();
        OutputStream os = null;
        InputStream is = null;
        final File dir = new File("/data/data/com.exa.wandaorderdemo/databases/test_order.db");
        dir.mkdir();
        final File file = new File(dir,Order.DB_NAME);
        try {
            os = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int r;
            is = am.open(Order.DB_NAME);
            while ((r = is.read(b)) != -1){
                os.write(b, 0, r);
                os.flush();
            }

            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(Order.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    }
                cursor.close();
            }
            db.close();
        } catch (Exception e) {
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
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int code = matcher.match(uri);
        Uri result = null;
        long rowId = 0;
        switch (code){
            case 0:
                rowId = db.insert(Order.TABLE_NAME, Order.customer_name,values);
                mContext.getContentResolver().notifyChange(uri, null);
                break;
        }
        if (rowId > 0){
            result = Uri.parse(uri + "/" + rowId);
            mContext.getContentResolver().notifyChange(result, null);
        }
        return result;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int code = matcher.match(uri);
        int result = -1;
        switch (code){
            case 0:
                result = db.delete(Order.TABLE_NAME, selection,
                        selectionArgs);
                mContext.getContentResolver().notifyChange(uri, null);
                break;
        }
        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int code = matcher.match(uri);
        int result = -1;
        switch (code){
            case 0:
                result = db.update(Order.TABLE_NAME, values, selection,
                        selectionArgs);
                mContext.getContentResolver().notifyChange(uri, null);
                break;
        }
        return result;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int num = 0;
        db.beginTransaction();
        try {
            num = values.length;
            for (int i = 0; i < num; i++) {
                insert(uri, values[i]);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return num;
    }
}
