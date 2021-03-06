package com.yiyang.cn.aakefudan.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.HomeActivity;
import com.yiyang.cn.activity.SelectLoginActivity;
import com.yiyang.cn.activity.ServiceActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.LoginUser;
import com.yiyang.cn.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

import static com.yiyang.cn.get_net.Urls.SERVER_URL;

public class LoginBtnView extends LinearLayout {
    private Context mContext;
    private View mView;

    private View ll_main;
    private TextView tv_name;
    private ImageView iv_img;

    private LoginUser.DataBean user;
    private BaseActivity activity;

    public LoginBtnView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public LoginBtnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LoginBtnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.kefu_view_loginbt, this, true);
        ll_main = mView.findViewById(R.id.ll_main);
        tv_name = mView.findViewById(R.id.tv_name);
        iv_img = mView.findViewById(R.id.iv_img);

        ll_main.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    login(user.getSubsystem_id(), user.getUser_id_key(), user.getPower_state());
                }
            }
        });
    }

    public void setUser(LoginUser.DataBean user, BaseActivity activity) {
        this.user = user;
        this.activity = activity;

        String power_state_name = user.getPower_state_name();
        String power_state = user.getPower_state();

        tv_name.setText(power_state_name);
        if (power_state.equals("1")) {//????????????
            iv_img.setImageResource(R.mipmap.login_icon_chezhu);
        } else if (power_state.equals("2")) {//?????????
            iv_img.setImageResource(R.mipmap.login_icon_qiche);
        } else {//?????????
            iv_img.setImageResource(R.mipmap.login_icon_kefu);
        }
    }

    /**
     * ??6.5	??????????????????????????????????????????????????????????????????
     * <p>
     * code	?????????(00051)
     * key	????????????
     * power_state	???????????????1.?????? 2.?????????3.????????????????????? 4.???????????????
     * subsystem_id	?????????id(??????)?????????????????????????????????
     * ???????????????witwork  ???????????????wit ????????????jcz
     * ????????????????????????
     * user_id_key	???????????????id
     * phone_model	????????????
     */
    private void login(String subsystem_id, String user_id_key, final String power_state) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00051");
        map.put("key", Urls.key);
        map.put("subsystem_id", subsystem_id);
        map.put("user_id_key", user_id_key);
        map.put("power_state", power_state);
        Gson gson = new Gson();
        OkGo.<AppResponse<LoginUser.DataBean>>post(SERVER_URL + "index/login")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<LoginUser.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<LoginUser.DataBean>> response) {
                        UserManager.getManager(activity).saveUser(response.body().data.get(0));
                        PreferenceHelper.getInstance(activity).putString("power_state", power_state);
                        String rongYunTouken = UserManager.getManager(mContext).getRongYun();
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_CONNET_MQTT;
                        RxBus.getDefault().sendRx(n);
                        if (!StringUtils.isEmpty(rongYunTouken)) {
                            Notice notice = new Notice();
                            notice.type = ConstanceValue.MSG_RONGYUN_CHONGZHI;
                            RxBus.getDefault().sendRx(notice);
                        }

                        switch (power_state) {
                            case "1"://????????????
                                activity.startActivity(new Intent(activity, HomeActivity.class));
                                activity.finish();
                                break;
                            case "2"://?????????
                                break;
                            case "3"://?????????
                                activity.startActivity(new Intent(activity, ServiceActivity.class));
                                activity.finish();
                                break;
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<LoginUser.DataBean>> response) {
                        AlertUtil.t(activity, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<LoginUser.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        activity.showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        activity.showLoadSuccess();
                    }
                });
    }
}
