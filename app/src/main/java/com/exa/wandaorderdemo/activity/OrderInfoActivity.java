package com.exa.wandaorderdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.exa.wandaorderdemo.R;
import com.exa.wandaorderdemo.model.Order;
import com.exa.wandaorderdemo.model.OrderManager;
import com.exa.wandaorderdemo.provider.DBHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2017/5/15.
 */

public class OrderInfoActivity extends Activity implements View.OnClickListener{
    private Context context;
    private List<Order> list = new ArrayList<>();
    private DBHelper mHelper;
    private SQLiteDatabase db;
    private String dbpath = "/data/data/com.exa.wandaorderdemo/databases/order_info.db";
    private TextView customer_num;
    private TextView customer_name;
    private TextView order_num;
    private TextView palte_num;
    private TextView weight;
    private TextView price;
    private TextView notes;
    private TextView description;
    private TextView date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        context = this;
        mHelper = DBHelper.getInstance(context);
        createDB();
        getDate();
        initView();
        initClickListener();
    }

    private void initView() {
        customer_num = (TextView) findViewById(R.id.customer_num);
        customer_name = (TextView) findViewById(R.id.customer_name);
        order_num = (TextView) findViewById(R.id.order_num);
        palte_num = (TextView) findViewById(R.id.palet_number);
        weight = (TextView) findViewById(R.id.weight);
        price = (TextView) findViewById(R.id.price);
        notes = (TextView) findViewById(R.id.notes);
        description = (TextView) findViewById(R.id.description);
        date = (TextView) findViewById(R.id.date);
        customer_name.setText(list.get(1).getCustomer_name());
    }

    public void initClickListener() {
        customer_num.setOnClickListener(this);
        customer_name.setOnClickListener(this);
        order_num.setOnClickListener(this);
        palte_num.setOnClickListener(this);
        weight.setOnClickListener(this);
        price.setOnClickListener(this);
        notes.setOnClickListener(this);
        description.setOnClickListener(this);
        date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public void getDate() {
        SQLiteDatabase db = createDB();
        Cursor cursor = db.query("\""+Order.TABLE_NAME+"\"", null, null, null, null, null, null);
        list = OrderManager.getOrders(cursor);
        Toast.makeText(this,"cursor===>"+cursor.getCount(),Toast.LENGTH_SHORT).show();
        cursor.close();
    }

    private SQLiteDatabase  createDB() {
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
        return createDB();
    }
}
