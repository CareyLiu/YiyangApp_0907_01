package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.PaipanDetailsModes;
import com.yiyang.cn.activity.wode_page.bazinew.model.PaipanModel;
import com.yiyang.cn.activity.wode_page.bazinew.view.BaziMingpanView;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MingpanActivity extends BaziBaseActivity {


    @BindView(R.id.ming6)
    BaziMingpanView ming6;
    @BindView(R.id.ming7)
    BaziMingpanView ming7;
    @BindView(R.id.ming8)
    BaziMingpanView ming8;
    @BindView(R.id.ming9)
    BaziMingpanView ming9;
    @BindView(R.id.ming5)
    BaziMingpanView ming5;
    @BindView(R.id.ming4)
    BaziMingpanView ming4;
    @BindView(R.id.ming10)
    BaziMingpanView ming10;
    @BindView(R.id.ming11)
    BaziMingpanView ming11;
    @BindView(R.id.ming3)
    BaziMingpanView ming3;
    @BindView(R.id.ming2)
    BaziMingpanView ming2;
    @BindView(R.id.ming1)
    BaziMingpanView ming1;
    @BindView(R.id.ming12)
    BaziMingpanView ming12;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_shengxiao)
    TextView tv_shengxiao;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_yangli)
    TextView tv_yangli;
    @BindView(R.id.tv_yili)
    TextView tv_yili;
    @BindView(R.id.tv_shengnian)
    TextView tv_shengnian;
    @BindView(R.id.tv_mingju)
    TextView tv_mingju;
    @BindView(R.id.tv_mingzhu)
    TextView tv_mingzhu;
    @BindView(R.id.tv_shenzhu)
    TextView tv_shenzhu;
    @BindView(R.id.tv_zidou)
    TextView tv_zidou;
    @BindView(R.id.tv_liudou)
    TextView tv_liudou;
    @BindView(R.id.ll_main)
    ScrollView ll_main;
    @BindView(R.id.ming_main)
    BaziMingpanView ming_main;
    @BindView(R.id.iv_line)
    ImageView iv_line;
    @BindView(R.id.tv_bulun1)
    TextView tv_bulun1;
    @BindView(R.id.tv_bulun2)
    TextView tv_bulun2;
    @BindView(R.id.tv_bulun3)
    TextView tv_bulun3;
    @BindView(R.id.tv_bulun4)
    TextView tv_bulun4;
    @BindView(R.id.tv_sizhu1)
    TextView tv_sizhu1;
    @BindView(R.id.tv_sizhu2)
    TextView tv_sizhu2;
    @BindView(R.id.tv_sizhu3)
    TextView tv_sizhu3;
    @BindView(R.id.tv_sizhu4)
    TextView tv_sizhu4;
    @BindView(R.id.fl_main)
    FrameLayout fl_main;
    @BindView(R.id.iv_jiexi)
    ImageView iv_jiexi;

    private PaipanModel.DataBean model;
    private List<PaipanDetailsModes> panModes = new ArrayList<>();
    private String mingpan_id;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_mingpan;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("本命盘");
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("解析");
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(Color.WHITE);
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickJiexi();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mingpan_id = getIntent().getStringExtra("mingpan_id");
        getMingPan(mingpan_id);
    }

    private void setData() {
        PaipanModel.DataBean.MingpanZiweiBean ziwei = model.getMingpan_ziwei().get(0);
        List<String> bulunBaZi = ziwei.getBaZi();
        List<String> jieqiBazi = ziwei.getSolarTermsbaZi();

        tv_name.setText(ziwei.getName());
        tv_shengxiao.setText(ziwei.getShengXiao());
        tv_age.setText(ziwei.getAge());
        tv_sex.setText(ziwei.getYinyang());
        tv_yangli.setText(ziwei.getSolar_birthday());
        tv_yili.setText(ziwei.getLunar_birthday());

        tv_mingju.setText(ziwei.getWuXingJu() + "  (" + ziwei.getMingJuNaYin() + ")");
        tv_mingzhu.setText(ziwei.getMingZhu());
        tv_shenzhu.setText(ziwei.getShenZhu());
        tv_zidou.setText(ziwei.getZiDou());
        tv_liudou.setText(ziwei.getLiuDou());
        tv_shengnian.setText(bulunBaZi.get(0) + "  (" + ziwei.getShengNianNaYin() + ")");

        tv_bulun1.setText(bulunBaZi.get(0));
        tv_bulun2.setText(bulunBaZi.get(1));
        tv_bulun3.setText(bulunBaZi.get(2));
        tv_bulun4.setText(bulunBaZi.get(3));

        tv_sizhu1.setText(jieqiBazi.get(0));
        tv_sizhu2.setText(jieqiBazi.get(1));
        tv_sizhu3.setText(jieqiBazi.get(2));
        tv_sizhu4.setText(jieqiBazi.get(3));
    }

    private void setMing() {
        PaipanModel.DataBean.MingpanZiweiBean ziwei = model.getMingpan_ziwei().get(0);
        for (int i = 0; i < 12; i++) {
            PaipanDetailsModes model = new PaipanDetailsModes();
            model.setShiErGong(ziwei.getShiErGong().get(i));
            model.setXing(ziwei.getXing().get(i));
            model.setXing_se(ziwei.getXing_se().get(i));
            model.setXiaoXian(ziwei.getXiaoXian().get(i));
            model.setDaXian(ziwei.getDaXian().get(i));
            model.setBoShi(ziwei.getBoShi().get(i));
            model.setJiangQian(ziwei.getJiangQian().get(i));
            model.setSuiQian(ziwei.getSuiQian().get(i));
            model.setWuXingChangSheng(ziwei.getWuXingChangSheng().get(i));
            model.setShiErGongNaYin(ziwei.getShiErGongNaYin().get(i));
            model.setShiErGongTianGan(ziwei.getShiErGongTianGan().get(i));
            panModes.add(model);
        }

        ming1.setModel(panModes.get(0), "子");
        ming2.setModel(panModes.get(1), "丑");
        ming3.setModel(panModes.get(2), "寅");
        ming4.setModel(panModes.get(3), "卯");
        ming5.setModel(panModes.get(4), "辰");
        ming6.setModel(panModes.get(5), "巳");
        ming7.setModel(panModes.get(6), "午");
        ming8.setModel(panModes.get(7), "未");
        ming9.setModel(panModes.get(8), "申");
        ming10.setModel(panModes.get(9), "酉");
        ming11.setModel(panModes.get(10), "戌");
        ming12.setModel(panModes.get(11), "亥");
    }

    @OnClick({R.id.iv_jiexi, R.id.ming6, R.id.ming7, R.id.ming8, R.id.ming9, R.id.ming5, R.id.ming4, R.id.fl_main, R.id.ming10, R.id.ming11, R.id.ming3, R.id.ming2, R.id.ming1, R.id.ming12})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ming6:
                selectMing(6);
                break;
            case R.id.ming7:
                selectMing(7);
                break;
            case R.id.ming8:
                selectMing(8);
                break;
            case R.id.ming9:
                selectMing(9);
                break;
            case R.id.ming5:
                selectMing(5);
                break;
            case R.id.ming4:
                selectMing(4);
                break;
            case R.id.fl_main:
                clickMain();
                break;
            case R.id.ming10:
                selectMing(10);
                break;
            case R.id.ming11:
                selectMing(11);
                break;
            case R.id.ming3:
                selectMing(3);
                break;
            case R.id.ming2:
                selectMing(2);
                break;
            case R.id.ming1:
                selectMing(1);
                break;
            case R.id.ming12:
                selectMing(12);
                break;
            case R.id.iv_jiexi:
                clickJiexi();
                break;

        }
    }

    private void clickJiexi() {
        Intent intent = new Intent(this, JiexiMainActivity.class);
        intent.putExtra("mingpan_id", model.getMingpan_id());
        startActivity(intent);
    }

    private void selectMing(int i) {
        ll_main.setVisibility(View.GONE);
        ming_main.setVisibility(View.VISIBLE);
        iv_line.setVisibility(View.VISIBLE);

        switch (i) {
            case 1:
                ming_main.setCenterModel(ming1.getModel(), "子");
                iv_line.setImageResource(R.mipmap.line_n_1);
                break;
            case 2:
                ming_main.setCenterModel(ming2.getModel(), "丑");
                iv_line.setImageResource(R.mipmap.line_n_2);
                break;
            case 3:
                ming_main.setCenterModel(ming3.getModel(), "寅");
                iv_line.setImageResource(R.mipmap.line_n_3);
                break;
            case 4:
                ming_main.setCenterModel(ming4.getModel(), "卯");
                iv_line.setImageResource(R.mipmap.line_n_4);
                break;
            case 5:
                ming_main.setCenterModel(ming5.getModel(), "辰");
                iv_line.setImageResource(R.mipmap.line_n_5);
                break;
            case 6:
                ming_main.setCenterModel(ming6.getModel(), "巳");
                iv_line.setImageResource(R.mipmap.line_n_6);
                break;
            case 7:
                ming_main.setCenterModel(ming7.getModel(), "午");
                iv_line.setImageResource(R.mipmap.line_n_7);
                break;
            case 8:
                ming_main.setCenterModel(ming8.getModel(), "未");
                iv_line.setImageResource(R.mipmap.line_n_8);
                break;
            case 9:
                ming_main.setCenterModel(ming9.getModel(), "申");
                iv_line.setImageResource(R.mipmap.line_n_9);
                break;
            case 10:
                ming_main.setCenterModel(ming10.getModel(), "酉");
                iv_line.setImageResource(R.mipmap.line_n_10);
                break;
            case 11:
                ming_main.setCenterModel(ming11.getModel(), "戌");
                iv_line.setImageResource(R.mipmap.line_n_11);
                break;
            case 12:
                ming_main.setCenterModel(ming12.getModel(), "亥");
                iv_line.setImageResource(R.mipmap.line_n_12);
                break;

        }
    }

    private void clickMain() {
        ll_main.setVisibility(View.VISIBLE);
        ming_main.setVisibility(View.GONE);
        iv_line.setVisibility(View.GONE);
    }


    private void getMingPan(String mingpan_id) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11014");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<PaipanModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<PaipanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<PaipanModel.DataBean>> response) {
                        showLoadSuccess();
                        model = response.body().data.get(0);
                        setData();
                        setMing();
                    }

                    @Override
                    public void onStart(Request<AppResponse<PaipanModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }
                });
    }
}
