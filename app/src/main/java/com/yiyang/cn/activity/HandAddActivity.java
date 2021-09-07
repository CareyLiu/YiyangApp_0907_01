package com.yiyang.cn.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.BangdingFailDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.CarBrand;
import com.yiyang.cn.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sl on 2019/6/18.
 */

public class HandAddActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_number)
    EditText mEtNumber;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;


    @Override
    public void onCreate(Bundle savedInstanceStateundle) {
        super.onCreate(savedInstanceStateundle);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("手动添加设备");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_ADD_CHELIANG_SUCCESS;
                sendRx(notice);
                finish();
            }
        });
    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_hand_add;
    }

    private void initView() {

    }

    @OnClick({R.id.bt_submit})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_submit:
                if (!StringUtils.isEmpty(mEtNumber.getText().toString().trim())) {
                    addSheBei(mEtNumber.getText().toString());
                } else {
                    UIHelper.ToastMessage(mContext, "请输入设备码后重新尝试");
                }
                break;
        }
    }


    public void addSheBei(String ccid) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03509");//正式的
//        map.put("code", "03519");//测试用
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("ccid", ccid);
        Gson gson = new Gson();
        OkGo.<AppResponse<CarBrand.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CarBrand.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CarBrand.DataBean>> response) {
                        mEtNumber.setText("");
                        BangdingFailDialog dialog = new BangdingFailDialog(mContext);
                        dialog.setClick(new BangdingFailDialog.BangdingClick() {
                            @Override
                            public void close() {
                                Notice notice = new Notice();
                                notice.type = ConstanceValue.MSG_ADD_CHELIANG_SUCCESS;
                                sendRx(notice);
                                finish();
                            }

                            @Override
                            public void jixu() {

                            }
                        });
                        dialog.bt_jixusao.setText("继续添加");
                        dialog.setTextContent("设备添加成功");
                        dialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<CarBrand.DataBean>> response) {
                        String msg = response.getException().getMessage();
                        BangdingFailDialog dialog = new BangdingFailDialog(mContext);
                        dialog.setClick(new BangdingFailDialog.BangdingClick() {
                            @Override
                            public void close() {
                                Notice notice = new Notice();
                                notice.type = ConstanceValue.MSG_ADD_CHELIANG_SUCCESS;
                                sendRx(notice);
                                finish();
                            }

                            @Override
                            public void jixu() {

                            }
                        });
                        dialog.bt_jixusao.setText("继续添加");
                        dialog.setTextContent(msg);
                        dialog.show();
                    }
                });
    }
}