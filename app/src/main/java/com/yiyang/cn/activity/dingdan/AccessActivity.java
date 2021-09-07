package com.yiyang.cn.activity.dingdan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.bumptech.glide.Glide;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.get_net.Urls;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class AccessActivity extends BaseActivity {

    @BindView(R.id.iv_product)
    ImageView ivProduct;
    @BindView(R.id.tv_pinglun_huashu)
    TextView tvPinglunHuashu;
    @BindView(R.id.rb_bar1)
    AppCompatRatingBar rbBar1;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.rrl_tijiao)
    RoundRelativeLayout rrlTijiao;
    @BindView(R.id.et_text)
    EditText etText;
    private String imgString;
    private String shopFromId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgString = getIntent().getStringExtra("imgString");
        shopFromId = getIntent().getStringExtra("shopFromId");
        Glide.with(AccessActivity.this).load(imgString).into(ivProduct);


        rrlTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (StringUtils.isEmpty(etText.getText().toString())) {
                    UIHelper.ToastMessage(AccessActivity.this, "请填写评论");
                    return;
                }
                if (rbBar1.getRating() == 0) {

                    UIHelper.ToastMessage(AccessActivity.this, "请输入评分");
                    return;
                }


                showDngDanCaoZuo(shopFromId, "是否确认提交申请", "04165");
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_access;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("发表评论");
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String imgString, String shopFromId) {
        Intent intent = new Intent(context, AccessActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("imgString", imgString);
        intent.putExtra("shopFromId", shopFromId);
        context.startActivity(intent);
    }

    private int choice;
    private AlertDialog.Builder builder;

    /**
     * 两个按钮的 dialog
     */
    private void showDngDanCaoZuo(String shopFormId, String quXiaoDingDanHuaShu, String code) {

        builder = new AlertDialog.Builder(AccessActivity.this).setIcon(R.mipmap.logi_icon).setTitle("订单操作")
                .setMessage(quXiaoDingDanHuaShu).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Toast.makeText(getActivity(), "确定按钮", Toast.LENGTH_LONG).show();
                        getNet_CaoZuo(shopFormId, code);


                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

    private String user_to_score;
    private String user_to_text;

    public void getNet_CaoZuo(String form_id, String code) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(AccessActivity.this).getString("app_token", "0"));
        map.put("shop_form_id", form_id);//全部
        map.put("user_to_score", String.valueOf(rbBar1.getRating()));
        map.put("user_to_text", etText.getText().toString().trim());

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.HOME_PICTURE_HOME)
                .tag(AccessActivity.this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(AccessActivity.this, "提交成功");
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);
                    }
                });

    }
}
