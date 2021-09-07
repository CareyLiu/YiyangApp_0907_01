package com.yiyang.cn.aakefudan.chat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class GuzhangHuiActivity extends BaseActivity {

    private String sub_user_name;
    private String sub_accid;
    private String sub_user_id;
    private String inst_id;
    private String user_car_id;
    private String zhu_car_stoppage_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加会话界面
        ConversationFragment conversationFragment = new ConversationFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, conversationFragment);
        transaction.commit();
        sub_user_name = getIntent().getStringExtra("sub_user_name");
        sub_accid = getIntent().getStringExtra("sub_accid");
        sub_user_id = getIntent().getStringExtra("sub_user_id");
        inst_id = getIntent().getStringExtra("inst_id");
        user_car_id = getIntent().getStringExtra("user_car_id");
        zhu_car_stoppage_no = getIntent().getStringExtra("zhu_car_stoppage_no");
        tv_title.setText(sub_user_name);
        getLiaoTian(mContext);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_RONGYUN_STATE) {
                    RongIMClient.ConnectionStatusListener.ConnectionStatus status = (RongIMClient.ConnectionStatusListener.ConnectionStatus) message.content;
                    if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONN_USER_BLOCKED)) {
                        //* 用户被开发者后台封禁
                        // notice.content = status.CONN_USER_BLOCKED;
                        UIHelper.ToastMessage(mContext, "该商户已被封禁");
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
                        //连接成功
                        showLoadSuccess();
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTING)) {
                        //连接中
                        // notice.content = status.CONNECTING;
                        showLoading();
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                        //断开连接
                        // notice.content = status.DISCONNECTED;
                        showLoadFailed();
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                        //用户账户在其他设备登录，本机会被踢掉线。
                        UIHelper.ToastMessage(mContext, "用户账户在其他设备登录，本机会被踢掉线。");
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.NETWORK_UNAVAILABLE)) {
                        //网络不可用
                        showLoadFailed();
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.SERVER_INVALID)) {
                        //TOKEN_INCORRECT
                        //连接失败
                        UIHelper.ToastMessage(mContext, "连接失败");
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.TOKEN_INCORRECT)) {
                        //Token 不正确
                        //notice.content = status.TOKEN_INCORRECT;
                    }
                }
            }
        }));


    }

    @Override
    public int getContentViewResId() {
        return R.layout.conversation;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("关于我们");
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

    public void getLiaoTian(Context context) {
        Map<String, String> map = new HashMap<>();
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("code", "03312");
        map.put("key", Urls.key);
        map.put("user_car_id", user_car_id);
        map.put("sub_user_id", sub_user_id);
        map.put("sub_accid", sub_accid);
        map.put("inst_id", inst_id);
        map.put("sub_user_name", sub_user_name);
        map.put("zhu_car_stoppage_no", zhu_car_stoppage_no);

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>
                post(Urls.MESSAGE_URL).
                tag(context).
                upJson(gson.toJson(map)).
                execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(mContext, "建立聊天成功");
                        showLoadSuccess();
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        AlertUtil.t(context, response.getException().getMessage());
                        showLoadSuccess();
                    }

                    @Override
                    public void onStart(Request<AppResponse<Object>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }
                });
    }

}