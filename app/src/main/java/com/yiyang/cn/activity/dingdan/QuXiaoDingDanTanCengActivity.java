package com.yiyang.cn.activity.dingdan;


import android.app.Activity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuXiaoDingDanTanCengActivity extends Activity {

    @BindView(R.id.constrain)
    ConstraintLayout constrain;
    @BindView(R.id.iv_buxiangmaile)
    ImageView ivBuxiangmaile;
    @BindView(R.id.rl_buxiangmaile)
    RelativeLayout rlBuxiangmaile;
    @BindView(R.id.iv_chongxinpai)
    ImageView ivChongxinpai;
    @BindView(R.id.rl_chongxinpai)
    RelativeLayout rlChongxinpai;
    @BindView(R.id.iv_maijiaquehuo)
    ImageView ivMaijiaquehuo;
    @BindView(R.id.rl_quehuo)
    RelativeLayout rlQuehuo;
    @BindView(R.id.iv_mianjiaoyi)
    ImageView ivMianjiaoyi;
    @BindView(R.id.rl_mianjiaoyi)
    RelativeLayout rlMianjiaoyi;
    @BindView(R.id.tv_quedingquxiao)
    TextView tvQuedingquxiao;
    @BindView(R.id.tv_zanbuquxiao)
    TextView tvZanbuquxiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quxiao_dingdan_tanceng);
        ButterKnife.bind(this);
        constrain.getBackground().setAlpha(200);// 0~255透明度值


        rlBuxiangmaile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIt();
                ivBuxiangmaile.setBackgroundResource(R.mipmap.quxiaodingdan_button_sel);

            }
        });
        rlChongxinpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIt();
                ivChongxinpai.setBackgroundResource(R.mipmap.quxiaodingdan_button_sel);
            }
        });
        rlMianjiaoyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIt();
                ivMianjiaoyi.setBackgroundResource(R.mipmap.quxiaodingdan_button_sel);
            }
        });
        rlQuehuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIt();
                ivMaijiaquehuo.setBackgroundResource(R.mipmap.quxiaodingdan_button_sel);
            }
        });


        tvQuedingquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvZanbuquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    public void setIt() {
        ivBuxiangmaile.setBackgroundResource(R.mipmap.quxiaodingdan_button_nor);
        ivChongxinpai.setBackgroundResource(R.mipmap.quxiaodingdan_button_nor);
        ivMaijiaquehuo.setBackgroundResource(R.mipmap.quxiaodingdan_button_nor);
        ivMianjiaoyi.setBackgroundResource(R.mipmap.quxiaodingdan_button_nor);
    }



}
