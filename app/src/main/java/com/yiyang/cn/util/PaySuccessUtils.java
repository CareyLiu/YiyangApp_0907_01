package com.yiyang.cn.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.UIHelper;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;

import java.util.HashMap;
import java.util.Map;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;
import static com.yiyang.cn.get_net.Urls.PAYSUCCESS;

public class PaySuccessUtils {

    public static void getNet(final Context context, String payId) {
        Map<String, String> map = new HashMap<>();
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("key", Urls.key);
        map.put("form_id", payId);
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>
                post(PAYSUCCESS).
                tag(context).
                upJson(gson.toJson(map)).

                execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        // UIHelper.ToastMessage(context, "发送成功", Toast.LENGTH_SHORT);

                        Log.i("PaySuccessUtils", "success");
                        //刷新我得接口
                        Notice n1 = new Notice();
                        n1.type = ConstanceValue.MSG_PAY_SUCCESS_REFRESH_WODE;
                        //  n.content = message.toString();
                        RxBus.getDefault().sendRx(n1);

                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        AlertUtil.t(context, response.getException().getMessage());
                    }
                });
    }

    public static void getNetFail(final Context context, String payId) {
        Map<String, String> map = new HashMap<>();
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("code", "04250");
        map.put("key", Urls.key);
        map.put("form_id", payId);
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>
                post(HOME_PICTURE_HOME).
                tag(context).
                upJson(gson.toJson(map)).

                execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        //  UIHelper.ToastMessage(context, "发送成功", Toast.LENGTH_SHORT);


                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        AlertUtil.t(context, response.getException().getMessage());
                    }
                });
    }


}
