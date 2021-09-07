package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.chelianwang.ScanAddCarActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.CarBrand;
import com.yiyang.cn.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FengshuiSHandActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_number)
    EditText mEtNumber;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;
    @BindView(R.id.tv_name)
    TextView tvName;
    private String mingpan_id;

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String mingpan_id) {
        Intent intent = new Intent(context, FengshuiSHandActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mingpan_id", mingpan_id);
        context.startActivity(intent);
    }

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
        tv_title.setText("手动添加");
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
    public int getContentViewResId() {
        return R.layout.activity_hand_add;
    }

    private void initView() {
        mEtNumber.setHint("请输入风水摆件摆件ccid编码");
        tvName.setText("产品编码");
        mingpan_id = getIntent().getStringExtra("mingpan_id");
    }

    @OnClick({R.id.bt_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_submit:
                requestData();
                break;
        }
    }


    public void requestData() {
        String ccid = mEtNumber.getText().toString();
        ccid="202008020010000000000001";
        if (ccid.length() != 24) {
            UIHelper.ToastMessage(mContext, "请输入正确的编码格式");
            return;
        }
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
                        AlertUtil.t(mContext, response.getException().getMessage());
                    }
                });
    }
}
