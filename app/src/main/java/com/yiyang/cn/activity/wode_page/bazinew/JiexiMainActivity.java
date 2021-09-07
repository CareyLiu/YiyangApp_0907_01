package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.DanganModel;
import com.yiyang.cn.activity.wode_page.bazinew.utils.BaziCode;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiexiMainActivity extends BaziBaseActivity {

    @BindView(R.id.ll_tab_minggong)
    LinearLayout llTabMinggong;
    @BindView(R.id.ll_tab_fuqi)
    LinearLayout llTabFuqi;
    @BindView(R.id.ll_tab_fumu)
    LinearLayout llTabFumu;
    @BindView(R.id.ll_tab_xiongdi)
    LinearLayout llTabXiongdi;
    @BindView(R.id.ll_tab_caibo)
    LinearLayout llTabCaibo;
    @BindView(R.id.ll_tab_jie)
    LinearLayout llTabJie;
    @BindView(R.id.ll_tab_fude)
    LinearLayout llTabFude;
    @BindView(R.id.ll_tab_zinv)
    LinearLayout llTabZinv;
    @BindView(R.id.ll_tab_tianzhai)
    LinearLayout llTabTianzhai;
    @BindView(R.id.ll_tab_guanlu)
    LinearLayout llTabGuanlu;
    @BindView(R.id.ll_tab_puyi)
    LinearLayout llTabPuyi;
    @BindView(R.id.ll_tab_qianyi)
    LinearLayout llTabQianyi;
    @BindView(R.id.ll_ys_nian)
    LinearLayout llYsNian;
    @BindView(R.id.ll_ys_yue)
    LinearLayout llYsYue;
    @BindView(R.id.ll_ys_ri)
    LinearLayout llYsRi;

    private String mingpan_id;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_jiexi_main;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("解析");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mingpan_id =getIntent().getStringExtra("mingpan_id");
    }

    @OnClick({R.id.ll_tab_minggong, R.id.ll_tab_fuqi, R.id.ll_tab_fumu, R.id.ll_tab_xiongdi, R.id.ll_tab_caibo, R.id.ll_tab_jie, R.id.ll_tab_fude, R.id.ll_tab_zinv, R.id.ll_tab_tianzhai, R.id.ll_tab_guanlu, R.id.ll_tab_puyi, R.id.ll_tab_qianyi, R.id.ll_ys_nian, R.id.ll_ys_yue, R.id.ll_ys_ri})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_minggong:
                clickJiexi(BaziCode.ll_tab_minggong);
                break;
            case R.id.ll_tab_fuqi:
                clickJiexi(BaziCode.ll_tab_fuqi);
                break;
            case R.id.ll_tab_fumu:
                clickJiexi(BaziCode.ll_tab_fumu);
                break;
            case R.id.ll_tab_xiongdi:
                clickJiexi(BaziCode.ll_tab_xiongdi);
                break;
            case R.id.ll_tab_caibo:
                clickJiexi(BaziCode.ll_tab_caibo);
                break;
            case R.id.ll_tab_jie:
                clickJiexi(BaziCode.ll_tab_jie);
                break;
            case R.id.ll_tab_fude:
                clickJiexi(BaziCode.ll_tab_fude);
                break;
            case R.id.ll_tab_zinv:
                clickJiexi(BaziCode.ll_tab_zinv);
                break;
            case R.id.ll_tab_tianzhai:
                clickJiexi(BaziCode.ll_tab_tianzhai);
                break;
            case R.id.ll_tab_guanlu:
                clickJiexi(BaziCode.ll_tab_guanlu);
                break;
            case R.id.ll_tab_puyi:
                clickJiexi(BaziCode.ll_tab_puyi);
                break;
            case R.id.ll_tab_qianyi:
                clickJiexi(BaziCode.ll_tab_qianyi);
                break;
            case R.id.ll_ys_nian:
                clickYunshi(BaziCode.ST_nian);
                break;
            case R.id.ll_ys_yue:
                clickYunshi(BaziCode.ST_yue);
                break;
            case R.id.ll_ys_ri:
                clickYunshi(BaziCode.ST_ri);
                break;
        }
    }

    private void clickJiexi(int jiexi) {
        Intent intent = new Intent(this, JiexiDetailsActivity.class);
        intent.putExtra("mingpan_id", mingpan_id);
        intent.putExtra("jiexi", jiexi);
        startActivity(intent);
    }

    private void clickYunshi(int code) {
        Intent intent = new Intent(this, YunshiActivity.class);
        intent.putExtra("mingpan_id", mingpan_id);
        intent.putExtra("code", code);
        startActivity(intent);
    }
}
