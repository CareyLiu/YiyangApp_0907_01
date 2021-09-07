package com.yiyang.cn.activity.tuya_device.utils;

import android.content.Context;
import android.view.View;

import com.yiyang.cn.activity.tuya_device.dialog.TuyaTishiDialog;

public class TuyaDialogUtils {

    public static void t(Context context, String msg) {
        TuyaTishiDialog tishiDialog = new TuyaTishiDialog(context, new TuyaTishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TuyaTishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TuyaTishiDialog dialog) {

            }

            @Override
            public void onDismiss(TuyaTishiDialog dialog) {

            }
        });
        tishiDialog.setTextCancel("");
        tishiDialog.setTextCont(msg);
        tishiDialog.show();
    }


    public static void t(Context context, String msg, TuyaTishiDialog.TishiDialogListener listener) {
        TuyaTishiDialog tishiDialog = new TuyaTishiDialog(context, listener);
        tishiDialog.setTextCancel("");
        tishiDialog.setTextCont(msg);
        tishiDialog.show();
    }
}
