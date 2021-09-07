package com.yiyang.cn.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.aakefudan.base.ServiceBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.utils.BaziCode;
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
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonInfoAcctivity extends ServiceBaseActivity implements Observer {

    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_service_info;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("个人资料");
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        AppEvent.getClassEvent().addObserver(this);
        ButterKnife.bind(this);
        requestData();
    }

    @OnClick({R.id.tv_nick_name, R.id.tv_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_nick_name:
                startActivity(new Intent(this, ReviseNicknameActivity.class).putExtra("nick_name", tvNickName.getText()));
                break;
            case R.id.tv_phone:
                AlertUtil.t(this, "手机号码不能修改");
                break;
        }
    }

    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03314");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(PersonInfoAcctivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<ServiceInfo.DataBean>>post(Urls.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ServiceInfo.DataBean>>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(final Response<AppResponse<ServiceInfo.DataBean>> response) {
                        tvNickName.setText(response.body().data.get(0).getUser_name());
                        tvPhone.setText(response.body().data.get(0).getUser_phone());

                    }

                    @Override
                    public void onError(Response<AppResponse<ServiceInfo.DataBean>> response) {
                        AlertUtil.t(PersonInfoAcctivity.this, response.getException().getMessage());
                    }
                });
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("update_nick"))
            requestData();

    }
}
