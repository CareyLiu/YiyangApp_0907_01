package com.yiyang.cn.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.roundview.RoundRelativeLayout;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TuanGouShengChengDingDanBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class TuanGouMaiDanDingDanActivity extends AbTuanGouShengChengDingDan {

    @BindView(R.id.tv_zanwu)
    TextView tvZanwu;
    @BindView(R.id.rl_2)
    RoundRelativeLayout rl2;
    @BindView(R.id.tv_hongbaodikou_huashu)
    TextView tvHongbaodikouHuashu;
    @BindView(R.id.view_line_1)
    View viewLine1;
    @BindView(R.id.tv_danqian_dikou)
    TextView tvDanqianDikou;
    @BindView(R.id.tv_dikoujine)
    TextView tvDikoujine;
    @BindView(R.id.rl_3)
    RoundRelativeLayout rl3;
    @BindView(R.id.tv_shifujine)
    TextView tvShifujine;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.view_line_2)
    View viewLine2;
    @BindView(R.id.tv_shoujihao)
    TextView tvShoujihao;
    @BindView(R.id.tv_shoujihao_number)
    TextView tvShoujihaoNumber;
    @BindView(R.id.rl_4)
    RoundRelativeLayout rl4;
    @BindView(R.id.rtv_jine)
    RoundTextView rtv_jine;
    @BindView(R.id.iv_choose)
    ImageView ivChoose;

    private String inst_id;//店家id
    private String shopType;
    private String money;
    private BigDecimal moneyZong;
    private BigDecimal moneyShiji;

    private String userHongBao;// 0什么也没用  1抵用券   2紅包
    private String diYongQuan;//抵用券
    private String diYongQuanID;

    private TuanGouShengChengDingDanBean.DataBean dataBean;
    private String available_balance;//红包金额
    private int count;//抵用券数量

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("生成订单");
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

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String money, String inst_id, String typeID) {
        Intent intent = new Intent(context, TuanGouMaiDanDingDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("money", money);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("typeID", typeID);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_maidan_sheng_cheng_ding_dan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initHuidiao();
        getNet();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DIYONGQUAN) {
                    String content = (String) message.content;
                    String[] split = content.split(",");
                    diYongQuan = split[0];
                    diYongQuanID = split[1];
                    tvZanwu.setText("抵用券减" + diYongQuan + "元");
                    userHongBao = "1";
                    BigDecimal available_balanceB = Y.getMoneyB(diYongQuan);
                    moneyShiji = moneyZong.subtract(available_balanceB);
                    setMoney();
                }
            }
        }));
    }

    private void init() {
        money = getIntent().getStringExtra("money");
        inst_id = getIntent().getStringExtra("inst_id");
        shopType = getIntent().getStringExtra("typeID");
        userHongBao = "0";
        moneyZong = Y.getMoneyB(money);
        moneyShiji = Y.getMoneyB(money);
        setMoney();

        rtv_jine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userHongBao.equals("1")) {
                    TuanGouMaiDanZhiFuActivity.actionStart(TuanGouMaiDanDingDanActivity.this, inst_id, money, "1", diYongQuanID);
                } else if (userHongBao.equals("2")) {
                    TuanGouMaiDanZhiFuActivity.actionStart(TuanGouMaiDanDingDanActivity.this, inst_id, money, "2", "");
                } else {
                    TuanGouMaiDanZhiFuActivity.actionStart(TuanGouMaiDanDingDanActivity.this, inst_id, money, "1", "");
                }

                finish();
            }
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userHongBao.equals("2")) {
                    userHongBao = "0";//什么都没用
                    ivChoose.setVisibility(View.INVISIBLE);
                    tvDanqianDikou.setVisibility(View.GONE);
                    tvDikoujine.setVisibility(View.GONE);

                    moneyShiji = moneyZong;
                    setMoney();
                } else {
                    userHongBao = "2";//用了红包
                    ivChoose.setVisibility(View.VISIBLE);
                    tvDanqianDikou.setVisibility(View.VISIBLE);
                    tvDikoujine.setVisibility(View.VISIBLE);

                    BigDecimal available_balanceB = Y.getMoneyB(available_balance);
                    moneyShiji = moneyZong.subtract(available_balanceB);
                    setMoney();

                    if (count > 0) {
                        tvZanwu.setText(count + "张可用");
                    } else {
                        tvZanwu.setText("暂无可用红包");
                    }
                }
            }
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    if (userHongBao.equals("2")) {
                        Y.t("使用余额抵扣时,无法使用抵用券");
                    } else {
                        TuanGouDiYongQuanActivity.actionStart(TuanGouMaiDanDingDanActivity.this, inst_id, money, shopType);
                    }
                } else {
                    Y.t("暂无可用红包");
                }
            }
        });

    }

    private void setMoney() {
        if (moneyShiji.compareTo(BigDecimal.ZERO) > 0) {
            tvMoney.setText("¥" + moneyShiji.toString());
        } else {
            moneyShiji = new BigDecimal("0.01");
            tvMoney.setText("¥" + moneyShiji.toString());
        }
    }

    @Override
    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "08025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TuanGouMaiDanDingDanActivity.this).getAppToken());
        map.put("money", money);
        map.put("inst_id", inst_id);
        map.put("shop_type", shopType);
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanGouShengChengDingDanBean.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouShengChengDingDanBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouShengChengDingDanBean.DataBean>> response) {
                        dataBean = response.body().data.get(0);
                        showPage();
                    }

                    @Override
                    public void onStart(Request<AppResponse<TuanGouShengChengDingDanBean.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    @Override
    public void showPage() {
        available_balance = dataBean.getAvailable_balance();
        count = Y.getInt(dataBean.getCount());

        if (count > 0) {
            tvZanwu.setText(count + "张可用");
        } else {
            tvZanwu.setText("暂无可用红包");
        }

        String strPhone = PreferenceHelper.getInstance(TuanGouMaiDanDingDanActivity.this).getString("user_phone", "");
        tvShoujihaoNumber.setText(strPhone);

        if (TextUtils.isEmpty(available_balance)) {
            available_balance = "0.00";
        }
        tvDikoujine.setText(available_balance);
        BigDecimal available_balanceB = Y.getMoneyB(available_balance);
        if (available_balanceB.compareTo(BigDecimal.ZERO) == 0) {
            userHongBao = "0";//什么也不用
            ivChoose.setVisibility(View.INVISIBLE);
            tvDanqianDikou.setVisibility(View.GONE);
            tvDikoujine.setVisibility(View.GONE);
        } else {
            userHongBao = "2";//用红包
            ivChoose.setVisibility(View.VISIBLE);
            tvDanqianDikou.setVisibility(View.VISIBLE);
            tvDikoujine.setVisibility(View.VISIBLE);

            moneyShiji = moneyZong.subtract(available_balanceB);
            setMoney();
        }
    }
}
