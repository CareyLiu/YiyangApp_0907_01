package com.yiyang.cn.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.aakefudan.base.ServiceBaseActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppEvent;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ServiceInfo;
import com.yiyang.cn.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviseNicknameActivity extends ServiceBaseActivity {
    @BindView(R.id.et_nick_name)
    EditText etNickName;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_revise_nickname;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("修改昵称");
        tv_rightTitle.setText("保存");
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        etNickName.setText(getIntent().getStringExtra("nick_name"));
    }

    public void requestData(){
        Map<String, String> map = new HashMap<>();
        map.put("code", "00701");
        map.put("key", Urls.key);
        map.put("update_type","1");
        map.put("user_name",etNickName.getText().toString());
        map.put("of_user_id",UserManager.getManager(ReviseNicknameActivity.this).getUserId());
        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(final Response<AppResponse> response) {
                        AppEvent.setMessage("update_nick");
                        AlertUtil.t(ReviseNicknameActivity.this,response.body().msg);
                        finish();

                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        AlertUtil.t(ReviseNicknameActivity.this,response.getException().getMessage());
                    }
                });
    }

}
