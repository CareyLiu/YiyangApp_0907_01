package com.yiyang.cn.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.chelianwang.ScanAddCarActivity;
import com.yiyang.cn.activity.saoma.ScanActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.util.AppToast;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Sl on 2019/6/18.
 */

public class BindBoxActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {
    private RelativeLayout mRlBack;
    private RelativeLayout mRlScanAdd;
    private RelativeLayout mRlHandAdd;

    @Override
    public void onCreate(Bundle savedInstanceStateundle) {
        super.onCreate(savedInstanceStateundle);
        initView();
    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_bind_box;
    }

    private void initView() {
        mRlBack = (RelativeLayout) findViewById(R.id.rl_back);
        mRlBack.setOnClickListener(this);
        mRlScanAdd = (RelativeLayout) findViewById(R.id.rl_scan_add);
        mRlScanAdd.setOnClickListener(this);
        mRlHandAdd = (RelativeLayout) findViewById(R.id.rl_hand_add);
        mRlHandAdd.setOnClickListener(this);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ADD_CHELIANG_SUCCESS) {
                    finish();
                }
            }
        }));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_scan_add:

                if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
                    ScanAddCarActivity.actionStart(BindBoxActivity.this);
                } else {
                    EasyPermissions.requestPermissions(this, getString(R.string.xjqx), 0, Manifest.permission.CAMERA);
                }
               // finish();
                break;
            case R.id.rl_hand_add:
                finish();
                startActivity(new Intent(this, HandAddActivity.class));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //startActivity(new Intent(this, ScanActivity.class));
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        AppToast.makeShortToast(this, getString(R.string.get_error));
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BindBoxActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("绑定设备");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }
}
