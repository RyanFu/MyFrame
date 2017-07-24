package com.jey.mymvp.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.jey.mymvp.base.BaseApplication;

/**
 * Toast 工具类
 * Created by xyan on 2016/8/4.
 */
public class ToastUtil {

    static ToastUtil td;

    public static void show(int resId) {
        show(BaseApplication.getInstance().getString(resId));

    }

    public static void show(String msg) {
        if (td == null) {
            td = new ToastUtil(BaseApplication.getInstance());
        }
        td.setText(msg);
        td.createLong().show();
    }

    public static void shortShow(String msg) {
        if (td == null) {
            td = new ToastUtil(BaseApplication.getInstance());
        }
        td.setText(msg);
        td.createShort().show();
    }


    Context context;
    Toast toast;
    String msg;

    public ToastUtil(Context context) {
        this.context = context;
    }

    public Toast createLong() {
        /*View contentView = View.inflate(context, R.layout.layout_toast, null);
        TextView tvMsg = (TextView) contentView.findViewById(R.id.content_tv);*/
        toast = new Toast(context);
        //toast.setView(contentView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        //tvMsg.setText(msg);
        return toast;
    }

    public Toast createShort() {
        /*View contentView = View.inflate(context, R.layout.layout_toast, null);
        TextView tvMsg = (TextView) contentView.findViewById(R.id.content_tv);*/
        toast = new Toast(context);
        //toast.setView(contentView);
       // toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setGravity(Gravity.BOTTOM, 0, SystemUtil.dp2px(BaseApplication.getInstance(),40));
        toast.setDuration(Toast.LENGTH_SHORT);
        //tvMsg.setText(msg);
        return toast;
    }

    public void show() {
        if (toast != null) {
            toast.show();
        }
    }

    public void setText(String text) {
        msg = text;
    }
}
