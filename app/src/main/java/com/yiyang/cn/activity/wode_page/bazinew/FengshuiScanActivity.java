package com.yiyang.cn.activity.wode_page.bazinew;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.chelianwang.ScanAddCarActivity;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.CarBrand;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

public class FengshuiScanActivity extends BaseActivity implements QRCodeView.Delegate {
    private static final String tag = ScanAddCarActivity.class.getSimpleName();
    @BindView(R.id.zxingview)
    ZBarView mQRCodeView;
    @BindView(R.id.capture_flash)
    ImageView captureFlash;

    boolean flag = true;
    ProgressDialog waitdialog;
    private String mingpan_id;

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String mingpan_id) {
        Intent intent = new Intent(context, FengshuiScanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mingpan_id", mingpan_id);
        context.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        mQRCodeView.startSpot();
        mQRCodeView.setDelegate(this);
        mingpan_id = getIntent().getStringExtra("mingpan_id");
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_scan1;
    }


    private void light() {
        if (flag) {
            flag = false;
            // 开闪光灯
            mQRCodeView.openFlashlight();
            captureFlash.setTag(null);
            captureFlash.setBackgroundResource(R.drawable.flash_open);
        } else {
            flag = true;
            // 关闪光灯
            mQRCodeView.closeFlashlight();
            captureFlash.setTag("1");
            captureFlash.setBackgroundResource(R.drawable.flash_default);
        }
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.e(tag, result);
        waitdialog = ProgressDialog.show(FengshuiScanActivity.this, null, "已扫描，正在处理···", true, true);
        waitdialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                mQRCodeView.stopSpot();
            }
        });
        vibrate();
        waitdialog.dismiss();
        if (result.length() == 24) {
            requestData(result);
        } else {
            UIHelper.ToastMessage(mContext, "您的风水摆件ccid不正确");
        }
    }

    public void requestData(String ccid) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11023");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("mingpan_id", mingpan_id);
        map.put("ccid", ccid);
        Gson gson = new Gson();
        OkGo.<AppResponse<CarBrand.DataBean>>post(Urls.BAZIAPP )
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CarBrand.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CarBrand.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "添加成功");
                        Notice notice = new Notice();
                        notice.type = ConstanceValue.MSG_BAZI_FSBJ1;
                        sendRx(notice);
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<CarBrand.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, response.message());
                    }
                });
    }


    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(tag, "打开相机出错");
        mQRCodeView.startCamera();
    }

    /**
     * 扫描结果对话框
     *
     * @param msg
     */
    public void showDialog(final String msg) {
        new AlertDialog.Builder(FengshuiScanActivity.this).setTitle("扫描结果").setMessage(msg)
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        waitdialog.dismiss();
                        dialog.dismiss();
                        mQRCodeView.startSpotAndShowRect();
                    }
                }).show();
    }


    @OnClick({R.id.capture_flash})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.capture_flash:
                light();
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        mQRCodeView.stopCamera();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (waitdialog != null) {
            waitdialog.dismiss();
        }
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("风水摆件扫一扫");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

}
