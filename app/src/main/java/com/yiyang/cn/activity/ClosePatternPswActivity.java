package com.yiyang.cn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.yiyang.cn.R;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.PreferenceCache;
import com.yiyang.cn.view.ChaosGestureView;

import java.util.List;

public class ClosePatternPswActivity extends BaseActivity implements ChaosGestureView.GestureCallBack {
    private ChaosGestureView gestureView;
    private TextView tv_user_name;
    private int gestureFlg = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_pattern_psw);

        gestureFlg = getIntent().getIntExtra("gestureFlg", -1);
        gestureView = (ChaosGestureView) findViewById(R.id.gesture1);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        gestureView.setGestureCallBack(ClosePatternPswActivity.this);
        gestureView.clearCacheLogin();
    }

    @Override
    public void gestureVerifySuccessListener(int stateFlag, List<ChaosGestureView.GestureBean> data, boolean success) {
        if (success) {
            if (gestureFlg==1){
                //删除密码
                PreferenceCache.putGestureFlag(false);
                gestureView.clearCache();
                AlertUtil.t(ClosePatternPswActivity.this,"清空手势密码成功");
                finish();
            }else if (gestureFlg==2){
                //修改密码
                AlertUtil.t(ClosePatternPswActivity.this,"验证手势密码成功,请重新设置");
                Intent intent = new Intent(ClosePatternPswActivity.this,SettingPatternPswActivity.class);
                startActivity(intent);
                finish();
            }else if (gestureFlg==3){
                Intent intent = new Intent(ClosePatternPswActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }else{

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
