/**
 *
 */
package com.yiyang.cn.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyang.cn.R;


/**
 * 弹出框管理类
 * @author yantong
 * 下午12:19:31
 */
public class DialogManager {

    private static DialogManager mInstance;
    private static Dialog mDialog;
    private static Context mContext;
    static Animation rotate;
    //显示提示内容
    private TextView mTipsTv;
    private static ImageView ivLeft,ivRight;

    private DialogManager(Activity activity){
        initDialog(activity);
    }

    public static DialogManager getManager(Activity activity){
        if (mInstance == null || mContext != activity) {
            mContext = activity;
            mInstance = new DialogManager(activity);
        }
        return mInstance;
    }

    /**
     * @param activity
     */
    private void initDialog(Activity activity) {
        if (activity!=null){
            mDialog = new Dialog(activity, R.style.dialog);
            mDialog.setContentView(R.layout.update_dialog);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setOwnerActivity(activity);
            rotate = AnimationUtils.loadAnimation(activity, R.anim.rotate_anim);
            ivLeft =  mDialog.findViewById(R.id.iv_left);
            ivRight = mDialog.findViewById(R.id.iv_right);
            Window window = mDialog.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            int width = getScreenWidth(activity);
            lp.width = (int) (0.35 * width);
            lp.height = (int)(0.3 * width);
            mTipsTv = (TextView) mDialog.findViewById(R.id.tvLoad);
        }


    }

    /**
     * 得到屏幕的宽度
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    private int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public void showMessage(String msg){
        mTipsTv.setText(msg);
        Activity activity = mDialog.getOwnerActivity();
        if (mDialog != null && activity != null && !activity.isFinishing()) {
            ivLeft.setAnimation(rotate);
            ivRight.setAnimation(rotate);
            ivLeft.startAnimation(rotate);
            ivRight.startAnimation(rotate);
            mDialog.show();
        }
    }

    public void dismiss(){
        mDialog.dismiss();

    }

    public void show(){
        if (mDialog != null) {
            mDialog.show();
        }
    }

    /**
     * 销毁
     */
    public void destroy() {
        mInstance = null;
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
