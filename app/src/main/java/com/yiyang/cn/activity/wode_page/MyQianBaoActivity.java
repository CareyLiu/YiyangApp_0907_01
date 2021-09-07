package com.yiyang.cn.activity.wode_page;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.readystatesoftware.chuck.internal.ui.MainActivity;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.PhoneCheckActivity;
import com.yiyang.cn.activity.SettingActivity;
import com.yiyang.cn.adapter.MyQianBaoAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.config.Wetch_S;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.MyQianBaoModel;
import com.yiyang.cn.model.MyQianBaoXianFeiMingXiModel;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class MyQianBaoActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    MyQianBaoAdapter myQianBaoAdapter;
    List<MyQianBaoXianFeiMingXiModel.DataBean.CunsumerListBean> stringList = new ArrayList<>();
    Response<AppResponse<MyQianBaoModel.DataBean>> response;
    @BindView(R.id.iv_bacground)
    ImageView ivBacground;
    @BindView(R.id.tv_ketixianjine)
    TextView tvKetixianjine;
    @BindView(R.id.tv_ketixian_price)
    TextView tvKetixianPrice;
    @BindView(R.id.tv_kedikou)
    TextView tvKedikou;
    @BindView(R.id.tv_kedikou_price)
    TextView tvKedikouPrice;
    @BindView(R.id.tv_dongjiejine_price)
    TextView tvDongjiejinePrice;
    @BindView(R.id.tv_dongjiejine)
    TextView tvDongjiejine;
    @BindView(R.id.ll_tixian)
    LinearLayout llTixian;
    @BindView(R.id.cl_back)
    ConstraintLayout clBack;
    @BindView(R.id.tv_mingxi)
    TextView tvMingxi;
    @BindView(R.id.tv_zhichu)
    TextView tvZhichu;
    @BindView(R.id.view_line)
    View viewLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlvList.setLayoutManager(new LinearLayoutManager(MyQianBaoActivity.this));
        myQianBaoAdapter = new MyQianBaoAdapter(R.layout.item_my_qianbao, stringList);
        rlvList.setAdapter(myQianBaoAdapter);
        myQianBaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (stringList!=null&&stringList.size()>position){
                    String user_pay_id = stringList.get(position).getUser_pay_id();
                    String er_type = stringList.get(position).getEr_type();
                    switch (view.getId()) {
                        case R.id.constrain:
                            MingxiActivity.actionStart(mContext, user_pay_id,er_type);
                            break;
                    }
                }
            }
        });

        getNet();
        llTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(response.body().data.get(0).getMoney_use())) {
                    response.body().data.get(0).setMoney_use("0.00");
                }
                BigDecimal bigDecimal = new BigDecimal(response.body().data.get(0).getMoney_use());
                if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                    //跳正常页面
                    showWeiXinOrZhiFuBaoSelect();
                } else {
                    UIHelper.ToastMessage(MyQianBaoActivity.this, "当前金额为0，不可提现");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNet();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_my_qian_bao;
    }

    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "04203");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(MyQianBaoActivity.this).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<MyQianBaoModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MyQianBaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MyQianBaoModel.DataBean>> response) {
                        MyQianBaoActivity.this.response = response;
                        tvKetixianPrice.setText("¥" + response.body().data.get(0).getMoney_use());
                        tvKedikou.setText("¥" + response.body().data.get(0).getRed_money_use());
                        tvDongjiejinePrice.setText("¥" + response.body().data.get(0).getRed_money_lock());
                        getMingXiNet();
                    }
                });
    }

    public void getMingXiNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "04141");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(MyQianBaoActivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>> response) {
                        stringList.clear();
                        stringList.addAll(response.body().data.get(0).getCunsumerList());
                        myQianBaoAdapter.setNewData(stringList);
                        myQianBaoAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MyQianBaoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("钱包");
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


    @Override
    public boolean showToolBar() {
        return true;
    }

    private AlertDialog.Builder builder;

    /**
     * 两个按钮的 dialog
     */
    private void showTwo() {
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.logi_icon).setTitle("账号绑定")
                .setMessage("是否前去绑定支付宝账号").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        PhoneCheckActivity.actionStart(mContext, "0008", "1");
                        // startActivity(new Intent(MyQianBaoActivity.this, PhoneCheckActivity.class).putExtra("mod_id", "0008"));
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }


    private int choice = 0;

    /**
     * 单选 dialog
     */
    private void showWeiXinOrZhiFuBaoSelect() {
        //默认选中第一个
        final String[] items = {"微信", "支付宝"};
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("选择您的提现方式")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice == 0) {//微信
                            String weixinPay = PreferenceHelper.getInstance(mContext).getString(App.CUNCHUBIND_WEIXINPAY, "0x11");
                            if (weixinPay.equals("1")) {
                                TiXianActivity.actionStart(MyQianBaoActivity.this, response.body().data.get(0).getMoney_use(), "0", "2");
                            } else {
                                PhoneCheckActivity.actionStart_WeiBind(mContext, "0320", true);
                            }
                        } else {//支付宝
                            String checkAliPay = PreferenceHelper.getInstance(MyQianBaoActivity.this).getString(App.CUNCHUBIND_ALIPAY, "0x11");
                            if (checkAliPay.equals("1")) {//已经设置
                                TiXianActivity.actionStart(MyQianBaoActivity.this, response.body().data.get(0).getMoney_use(), "0", "1");
                            } else {//2 未设置
                                showTwo();
                            }
                        }
                    }
                });
        builder.create().show();
    }
}






