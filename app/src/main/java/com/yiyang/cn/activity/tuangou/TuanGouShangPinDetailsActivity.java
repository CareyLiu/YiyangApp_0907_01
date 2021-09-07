package com.yiyang.cn.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatRatingBar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TuanGouShangPinDetailsModel;
import com.yiyang.cn.util.GlideShowImageUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yiyang.cn.get_net.Urls.LIBAOLIST;

public class TuanGouShangPinDetailsActivity extends AbTuanGouShangPinDetails {

    TuanGouShangPinDetailsModel.DataBean dataBean = new TuanGouShangPinDetailsModel.DataBean();
    Response<AppResponse<TuanGouShangPinDetailsModel.DataBean>> response;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_meishiming)
    TextView tvMeishiming;
    @BindView(R.id.tv_jieshao)
    TextView tvJieshao;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_meishiming1)
    TextView tvMeishiming1;
    @BindView(R.id.ll_taocan_details)
    LinearLayout llTaocanDetails;
    @BindView(R.id.tv_wenxin_tishi)
    TextView tvWenxinTishi;
    @BindView(R.id.ll_wenxin)
    LinearLayout llWenxin;
    @BindView(R.id.tv_dianjia_jiashao)
    TextView tvDianjiaJiashao;
    @BindView(R.id.iv_shop_image)
    ImageView ivShopImage;
    @BindView(R.id.tv_dianjia)
    TextView tvDianjia;
    @BindView(R.id.star)
    AppCompatRatingBar star;
    @BindView(R.id.tv_shop_addr)
    TextView tvShopAddr;
    @BindView(R.id.tv_yonghu_pingjia)
    TextView tvYonghuPingjia;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_click_more)
    TextView tvClickMore;
    @BindView(R.id.tv_zuigao_money)
    TextView tvZuigaoMoney;
    @BindView(R.id.rtv_liji_qianggou)
    RoundTextView rtvLijiQianggou;


    private String shopId;

    private String warseId;

    private String shopImage;
    private String shopName;
    private String shopStar;
    private String shopAddr;
    private String shopType;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_shang_pin_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        shopId = getIntent().getStringExtra("shopId");
        warseId = getIntent().getStringExtra("wares_id");
        shopImage = getIntent().getStringExtra("shopImage");
        shopName = getIntent().getStringExtra("shopName");
        shopStar = getIntent().getStringExtra("shopStar");
        shopAddr = getIntent().getStringExtra("shopAddr");
        // shopType = getIntent().getStringExtra("shopType");
        getNet();
    }


    @Override
    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "08013");
        map.put("key", Urls.key);
        map.put("wares_id", warseId);
        map.put("inst_id", shopId);
        map.put("shopType", shopType);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<TuanGouShangPinDetailsModel.DataBean>>post(LIBAOLIST)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouShangPinDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouShangPinDetailsModel.DataBean>> response) {
                        TuanGouShangPinDetailsActivity.this.response = response;
                        showPage();
                    }
                });
    }

    @Override
    public void showPage() {
        dataBean = response.body().data.get(0);
        shopType = response.body().data.get(0).getShop_type();
        tvName.setText(dataBean.getInst_name());
        tvMeishiming.setText(dataBean.getShop_title());
        tvXiaoliang.setText("半年销量：" + dataBean.getPay_count());
        Glide.with(TuanGouShangPinDetailsActivity.this).load(dataBean.getImg_url()).into(ivImage);

        if (dataBean.getText_1() != null) {
            for (int i = 0; i < dataBean.getText_1().size(); i++) {

//                View view = View.inflate(TuanGouShangPinDetailsActivity.this, R.layout.item_taocan_details, null);
//                TextView tvName = view.findViewById(R.id.tv_name);
//                LinearLayout llParent = view.findViewById(R.id.ll_parent);
//                tvName.setText(dataBean.getText_1().get(i).getMenu_title());
//                for (int j = 0; j < dataBean.getText_1().get(i).getMenu().size(); j++) {

                View view1 = View.inflate(TuanGouShangPinDetailsActivity.this, R.layout.item_taocan_erji, null);
                TextView tvShuoMing = view1.findViewById(R.id.tv_shuoming);
                TextView tvFen = view1.findViewById(R.id.tv_fen);
                tvShuoMing.setText(dataBean.getText_1().get(i).menu_text);
                tvFen.setText(dataBean.getText_1().get(i).menu_count);

//                }
                llTaocanDetails.addView(view1);
            }
        }


//        tvYouxiaoqi.setText(dataBean.getText_2().get(0).getPrompt().get(0).getPrompt_text());

        if (dataBean.getText_2() != null) {

            for (int i = 0; i < dataBean.getText_2().size(); i++) {

                View view = View.inflate(TuanGouShangPinDetailsActivity.this, R.layout.item_wenxin_tishi, null);

                TextView tvYouXiaoQi = view.findViewById(R.id.tv_youxiaoqi);
                TextView tvData = view.findViewById(R.id.tv_data);

             //   tvYouXiaoQi.setText(dataBean.getText_2().get(i).prompt_text);
                tvData.setText(dataBean.getText_2().get(i).prompt_text);
                llWenxin.addView(view);
            }
        }
        Glide.with(TuanGouShangPinDetailsActivity.this).applyDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(shopImage).into(ivShopImage);
        tvDianjia.setText(shopName);
        if (!StringUtils.isEmpty(shopStar)){
            star.setRating(Float.parseFloat(shopStar));
        }
        tvShopAddr.setText(dataBean.getAddr());

        tvMoney.setText("¥" + dataBean.getShop_money_now());
        tvZuigaoMoney.setText("最高门市价¥" + dataBean.getShop_money_old());
        rtvLijiQianggou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TuanGouShengChengDingDanActivity.actionStart(TuanGouShangPinDetailsActivity.this, shopId, dataBean.getShop_money_now(), dataBean.getImg_url(), dataBean.getShop_title(), shopType, warseId);
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String shopId, String warseId, String shopName, String shopImage, String star, String addr) {
        Intent intent = new Intent(context, TuanGouShangPinDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("shopId", shopId);
        intent.putExtra("wares_id", warseId);

        intent.putExtra("shopName", shopName);
        intent.putExtra("shopImage", shopImage);
        intent.putExtra("shopStar", star);
        intent.putExtra("shopAddr", addr);

        context.startActivity(intent);

    }

    @Override
    public boolean showToolBar() {
        return true;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("详情");
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
}
