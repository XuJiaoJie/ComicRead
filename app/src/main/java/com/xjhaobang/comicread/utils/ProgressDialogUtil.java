package com.xjhaobang.comicread.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import static com.xjhaobang.comicread.constants.Constant.GRAVITY_BOTTOM;
import static com.xjhaobang.comicread.constants.Constant.GRAVITY_CENTER;
import static com.xjhaobang.comicread.constants.Constant.GRAVITY_TOP;

public class ProgressDialogUtil {
    private static ProgressDialog sProgressDialog;
    private static boolean isShow = false;


    public static void showDefaultDialog(Context context) {
        showProgressDialog(context, "正在加载数据，请稍候...");
    }

    public static void showProgressDialog(Context context, CharSequence msg) {
        sProgressDialog = new ProgressDialog(context);
        sProgressDialog.setMessage(msg);
        sProgressDialog.setCanceledOnTouchOutside(false);
        sProgressDialog.setCancelable(true);
        sProgressDialog.show();
        isShow = true;
    }

    public static void setMsg(CharSequence msg) {
        if (sProgressDialog != null) {
            sProgressDialog.setMessage(msg);
        }
    }

    public static void setGravity(int gravity) {
        Window window = sProgressDialog.getWindow();
        switch (gravity) {
            case GRAVITY_TOP:
                window.setGravity(Gravity.TOP);
                break;
            case GRAVITY_CENTER:
                window.setGravity(Gravity.CENTER);
                break;
            case GRAVITY_BOTTOM:
                window.setGravity(Gravity.BOTTOM);
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.y = 400;
                window.setAttributes(layoutParams);
                break;
            default:
                window.setGravity(Gravity.CENTER);
                break;
        }

    }

    public static void dismiss() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            sProgressDialog.dismiss();
            isShow = false;
        }
    }

    public static boolean isShow() {
        return isShow;
    }
}