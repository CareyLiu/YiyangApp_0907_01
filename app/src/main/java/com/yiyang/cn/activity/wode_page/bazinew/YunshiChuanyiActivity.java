package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.gouwuche.GouWuCheQueRenDingDanActivity;
import com.yiyang.cn.activity.tuangou.TuanGouMaiDanZhiFuActivity;
import com.yiyang.cn.activity.tuangou.TuanGouZhiFuActivity;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.dialog.JiesuoDialog;
import com.yiyang.cn.activity.wode_page.bazinew.model.YunshiModel;
import com.yiyang.cn.activity.wode_page.bazinew.utils.TimeUtils;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.YuZhiFuModel;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YunshiChuanyiActivity extends BaziBaseActivity {

    @BindView(R.id.tv_name_sex)
    TextView tv_name_sex;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.tv_data_jiexi)
    TextView tv_data_jiexi;
    @BindView(R.id.tv_yunshi)
    TextView tv_yunshi;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.ll_jiesuo)
    LinearLayout ll_jiesuo;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.tv_select_data)
    TextView tv_select_data;
    @BindView(R.id.ll_data)
    LinearLayout ll_data;

    private int code;
    private String mingpan_id;
    private String ex_type;
    private String ex_factor;
    private int year;
    private int month;
    private int day;
    private TimePickerView timePicker;
    private YuZhiFuModel.DataBean dataBean;
    private IWXAPI api;
    private String name_text;
    private String birthday_text;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_yunshi_chuanyi;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("运势");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        code = getIntent().getIntExtra("code", 0);
        mingpan_id = getIntent().getStringExtra("mingpan_id");

        name_text = getIntent().getStringExtra("name_text");
        birthday_text = getIntent().getStringExtra("birthday_text");
        tv_name_sex.setText(name_text);
        tv_birthday.setText(birthday_text);

        tv_title.setText("穿衣指数");
        tv_yunshi.setText("穿衣指数");
        ex_type = "4";
        year = TimeUtils.getYear();
        month = TimeUtils.getMonth();
        day = TimeUtils.getDay();

        String monthS = "";
        String dayS = "";
        if (month >= 10) {
            monthS = "" + month;
        } else {
            monthS = "0" + month;
        }
        if (day >= 10) {
            dayS = "" + day;
        } else {
            dayS = "0" + day;
        }
        ex_factor = "" + year + monthS + dayS;
        tv_select_data.setText(year + "-" + monthS + "-" + dayS);

        getNet();
    }


    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        map.put("ex_type", ex_type);
        map.put("ex_factor", ex_factor);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<YunshiModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YunshiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<YunshiModel.DataBean>> response) {
                        showLoadSuccess();
                        List<YunshiModel.DataBean> data = response.body().data;
                        if (data != null && data.size() > 0) {
                            YunshiModel.DataBean bean = data.get(0);
//                            tv_name_sex.setText(bean.getName() + "  " + bean.getSex());
//                            tv_birthday.setText(bean.getBirthday());
                            tv_data_jiexi.setText(bean.getTime_text());
                            String lock = bean.getLock();
                            if (TextUtils.isEmpty(lock) || "1".equals(lock)) {
                                ll_data.setVisibility(View.VISIBLE);
                                ll_jiesuo.setVisibility(View.GONE);
                                tv_content.setText(bean.getEx_dress());
                                tv_yunshi.setText("财神方位：" + bean.getEx_caishenwei());
                            } else {
                                ll_data.setVisibility(View.GONE);
                                ll_jiesuo.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<YunshiModel.DataBean>> response) {
                        super.onError(response);
                        showLoadFailed();
                        tv_content.setVisibility(View.GONE);
                        ll_jiesuo.setVisibility(View.GONE);
                        String msg = response.getException().getMessage();
                        String[] msgToast = msg.split("：");
                        if (msgToast.length == 3) {
                            t(msgToast[2]);
                        }
                    }

                    @Override
                    public void onStart(Request<AppResponse<YunshiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }
                });
    }

    @OnClick({R.id.ll_jiesuo, R.id.iv_left, R.id.iv_right, R.id.tv_select_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_jiesuo:
                clickJiesuo();
                break;
            case R.id.iv_left:
                clickLeft();
                break;
            case R.id.iv_right:
                clickRight();
                break;
            case R.id.tv_select_data:
                selectData();
                break;
        }
    }

    private void clickJiesuo() {
        JiesuoDialog dialog = new JiesuoDialog(this, 1);
        dialog.setPayClick(new JiesuoDialog.JieSuoPayClick() {
            @Override
            public void payCi() {
                Intent intent = new Intent(YunshiChuanyiActivity.this, BaziPayActivity.class);
                intent.putExtra("mingpan_id", mingpan_id);
                intent.putExtra("payType", 101);
                intent.putExtra("time", tv_select_data.getText().toString());
                startActivityForResult(intent, 100);
            }

            @Override
            public void payNian() {
                Intent intent = new Intent(YunshiChuanyiActivity.this, BaziPayActivity.class);
                intent.putExtra("mingpan_id", mingpan_id);
                intent.putExtra("payType", 102);
                intent.putExtra("time", tv_select_data.getText().toString());
                startActivityForResult(intent, 100);
            }
        });
        dialog.show();
    }

    private void selectData() {
        if (timePicker == null) {
            //时间选择器
            timePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(date);
                    year = instance.get(Calendar.YEAR);
                    month = instance.get(Calendar.MONTH) + 1;
                    day = instance.get(Calendar.DATE);
                    tv_select_data.setText(TimeUtils.getData(date));
                    ex_factor = TimeUtils.getData(date, "yyyyMMdd");
                    getNet();
                }
            }).build();
        }
        timePicker.show();
    }

    private void clickRight() {
        int nowDay = TimeUtils.getMonthLastDay(year, month);
        if (day < nowDay) {
            day++;
        } else {
            if (month < 12) {
                month++;
            } else {
                year++;
                month = 1;
            }
            day = 1;
        }

        String monthS = "";
        String dayS = "";
        if (month >= 10) {
            monthS = "" + month;
        } else {
            monthS = "0" + month;
        }
        if (day >= 10) {
            dayS = "" + day;
        } else {
            dayS = "0" + day;
        }
        ex_factor = "" + year + monthS + dayS;
        tv_select_data.setText(year + "-" + monthS + "-" + dayS);

        getNet();
    }

    private void clickLeft() {
        if (day > 1) {
            day--;
        } else {
            if (month > 1) {
                month--;
            } else {
                year--;
                month = 12;
            }
            day = TimeUtils.getMonthLastDay(year, month);
        }

        String monthS = "";
        String dayS = "";
        if (month >= 10) {
            monthS = "" + month;
        } else {
            monthS = "0" + month;
        }
        if (day >= 10) {
            dayS = "" + day;
        } else {
            dayS = "0" + day;
        }
        ex_factor = "" + year + monthS + dayS;
        tv_select_data.setText(year + "-" + monthS + "-" + dayS);

        getNet();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && requestCode == 100) {
            getNet();
        }
    }
}
