package com.yiyang.cn.activity.wode_page.bazinew.view;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.model.PaipanDetailsModes;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;

public class BaziMingpanView extends RelativeLayout {//命盘详情view

    LinearLayout ll_top;
    LinearLayout ll_three;
    LinearLayout ll_two;
    TextView tv_left;
    TextView tv_left2;
    TextView tv_left3;
    TextView tv_num;
    TextView tv_minggong;
    TextView tv_right;
    TextView tv_right2;
    TextView tv_right3;
    TextView tv_data;

    private Context mContext;
    private View mView;


    private PaipanDetailsModes model;
    private List<SpannableString> xingList = new ArrayList<>();
    private SpannableString tiangan;

    public BaziMingpanView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public BaziMingpanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public BaziMingpanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.bazi_view_mingpan, this, true);
        ll_top = mView.findViewById(R.id.ll_top);
        ll_three = mView.findViewById(R.id.ll_three);
        ll_two = mView.findViewById(R.id.ll_two);
        tv_left = mView.findViewById(R.id.tv_left);
        tv_left2 = mView.findViewById(R.id.tv_left2);
        tv_left3 = mView.findViewById(R.id.tv_left3);
        tv_num = mView.findViewById(R.id.tv_num);
        tv_minggong = mView.findViewById(R.id.tv_minggong);
        tv_right = mView.findViewById(R.id.tv_right);
        tv_right2 = mView.findViewById(R.id.tv_right2);
        tv_right3 = mView.findViewById(R.id.tv_right3);
        tv_data = mView.findViewById(R.id.tv_data);
    }

    public PaipanDetailsModes getModel() {
        return model;
    }

    public void setModel(PaipanDetailsModes model, String shu) {//设置外侧12宫的数据
        this.model = model;

        tv_left.setText(model.getBoShi());
        tiangan = new SpannableString(model.getShiErGongTianGan() + shu);
        tiangan.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tiangan.setSpan(new ForegroundColorSpan(Color.parseColor("#16119B")), 1, tiangan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_right.setText(tiangan);
        tv_data.setText(model.getXiaoXian());
        tv_num.setText(model.getDaXian());
        tv_minggong.setText(model.getShiErGong());

        List<String> xing = model.getXing();
        List<String> xing_se = model.getXing_se();
        for (int i = 0; i < xing.size(); i++) {
            TextView tvTop = (TextView) View.inflate(mContext, R.layout.bazi_view_mingpan_text, null);
            String xingText = xing.get(i);
            String xingSeText = xing_se.get(i);
            if (xingText.length() == 2) {
                SpannableString xingnew = new SpannableString(xingText);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor(xingSeText)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvTop.setText(xingnew);
            } else if (xingText.length() == 4) {
                String s1 = xingText.substring(0, 2);
                String s2 = xingText.substring(3);
                SpannableString xingnew = new SpannableString(s1 + s2);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor(xingSeText)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor("#CB8492")), 2, xingnew.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvTop.setText(xingnew);
            } else if (xingText.length() == 6) {
                String s1 = xingText.substring(0, 2);
                String s2 = xingText.substring(3, 4);
                String s3 = xingText.substring(5);
                SpannableString xingnew = new SpannableString(s1 + s2 + s3);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor(xingSeText)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor("#CB8492")), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 3, xingnew.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvTop.setText(xingnew);
            }

            if (i < 5) {
                ll_top.addView(tvTop, 0);
            }
        }
    }

    public SpannableString getTiangan() {
        return tiangan;
    }


    public void setCenterModel(PaipanDetailsModes model, String shu) {//设置中间点击的数据
        this.model = model;
        ll_top.removeAllViews();

        tv_left2.setVisibility(VISIBLE);
        tv_left3.setVisibility(VISIBLE);
        tv_right2.setVisibility(VISIBLE);
        tv_right3.setVisibility(VISIBLE);

        tv_left.setText(model.getBoShi());
        tiangan = new SpannableString(model.getShiErGongTianGan() + shu);
        tiangan.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tiangan.setSpan(new ForegroundColorSpan(Color.parseColor("#16119B")), 1, tiangan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_right.setText(tiangan);
        tv_data.setText(model.getXiaoXian());
        tv_num.setText(model.getDaXian());
        tv_minggong.setText(model.getShiErGong());

        tv_left2.setText(model.getJiangQian());
        tv_left3.setText(model.getSuiQian());
        tv_right3.setText(model.getWuXingChangSheng());
        tv_right2.setText(model.getShiErGongNaYin());

        List<String> xing = model.getXing();
        List<String> xing_se = model.getXing_se();
        for (int i = 0; i < xing.size(); i++) {
            TextView tvTop = (TextView) View.inflate(mContext, R.layout.bazi_view_mingpan_text, null);
            String xingText = xing.get(i);
            String xingSeText = xing_se.get(i);
            if (xingText.length() == 2) {
                SpannableString xingnew = new SpannableString(xingText);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor(xingSeText)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvTop.setText(xingnew);
            } else if (xingText.length() == 4) {
                String s1 = xingText.substring(0, 2);
                String s2 = xingText.substring(3);
                SpannableString xingnew = new SpannableString(s1 + s2);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor(xingSeText)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor("#CB8492")), 2, xingnew.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvTop.setText(xingnew);
            } else if (xingText.length() == 6) {
                String s1 = xingText.substring(0, 2);
                String s2 = xingText.substring(3, 4);
                String s3 = xingText.substring(5);
                SpannableString xingnew = new SpannableString(s1 + s2 + s3);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor(xingSeText)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor("#CB8492")), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                xingnew.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 3, xingnew.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvTop.setText(xingnew);
            }
            ll_top.addView(tvTop, 0);
        }
    }
}
