package com.yiyang.cn.activity.wode_page.bazinew;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.HandAddActivity;
import com.yiyang.cn.activity.chelianwang.ScanAddCarActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.util.AppToast;
import com.yiyang.cn.util.PaySuccessUtils;

import java.util.List;

import androidx.annotation.NonNull;
import pub.devrel.easypermissions.EasyPermissions;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Sl on 2019/6/18.
 */

public class FengshuiBindActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {
    private RelativeLayout mRlBack;
    private RelativeLayout mRlScanAdd;
    private RelativeLayout mRlHandAdd;
    private String mingpan_id;

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("风水摆件绑定");
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


    public static void actionStart(Context context, String mingpan_id) {
        Intent intent = new Intent(context, FengshuiBindActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mingpan_id", mingpan_id);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceStateundle) {
        super.onCreate(savedInstanceStateundle);
        setContentView(R.layout.bazi_activity_fengshui_bind);
        StatusBarUtil.setLightMode(this);
        initView();
    }

    private void initView() {
        mRlBack = (RelativeLayout) findViewById(R.id.rl_back);
        mRlBack.setOnClickListener(this);
        mRlScanAdd = (RelativeLayout) findViewById(R.id.rl_scan_add);
        mRlScanAdd.setOnClickListener(this);
        mRlHandAdd = (RelativeLayout) findViewById(R.id.rl_hand_add);
        mRlHandAdd.setOnClickListener(this);

        mingpan_id = getIntent().getStringExtra("mingpan_id");

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_BAZI_FSBJ1) {
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_BAZI_FSBJ2;
                    sendRx(notice);
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
                    FengshuiScanActivity.actionStart(FengshuiBindActivity.this, mingpan_id);
                } else {
                    EasyPermissions.requestPermissions(this, getString(R.string.xjqx), 0, Manifest.permission.CAMERA);
                }
                break;
            case R.id.rl_hand_add:
                FengshuiSHandActivity.actionStart(FengshuiBindActivity.this, mingpan_id);
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
}
