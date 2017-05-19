package com.exa.wandaorderdemo.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.exa.wandaorderdemo.R;

import java.util.List;

import static com.exa.wandaorderdemo.R.id.dialog_edit_list;

/**
 * Created by Song on 2017/5/18.
 */

public class ListviewDialog extends Dialog {
    private Context context;
    private Button confirm;
    private Button cancel;
    private ListView listView;

    public ListviewDialog(@NonNull Context context) {
        super(context);
        this.initView(context);
    }

    public ListviewDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.initView(context);
    }

    protected ListviewDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener
            cancelListener) {
        super(context, cancelable, cancelListener);
        this.initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.list_order_dialog,null);
        confirm = (Button) view.findViewById(R.id.dialog_bt_confirm);
        cancel = (Button) view.findViewById(R.id.dialog_bt_cancel);
        listView = (ListView) view.findViewById(dialog_edit_list);
        setContentView(view);
    }

    public void setConfirmOnClick(View.OnClickListener listener){
        if (listener != null){
            confirm.setOnClickListener(listener);
        }
    }

    public void setCancelOnClick(View.OnClickListener listener) {
        if (listener != null){
            cancel.setOnClickListener(listener);
        }
    }

    public void setListView(List<String> data){
        listView.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_expandable_list_item_1,
                data));
    }
}
