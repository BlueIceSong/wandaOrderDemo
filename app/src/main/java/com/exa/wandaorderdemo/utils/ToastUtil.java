package com.exa.wandaorderdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ToastUtil {
    static Toast mToast = null;

    public static void showToast(Context context,int StringId){
        showToast(context,context.getString(StringId));
    }

    public static void showToast(Context context, String string){
         if (mToast == null){
             mToast = Toast.makeText(context,string,Toast.LENGTH_SHORT);
         } else {
             mToast.setText(string);
         }
         mToast.show();
    }
}
