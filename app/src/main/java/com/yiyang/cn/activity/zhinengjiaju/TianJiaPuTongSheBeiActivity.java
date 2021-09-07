package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.flyco.roundview.RoundRelativeLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.OneImageAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.FenLeiContentModel;
import com.yiyang.cn.model.ZhiNengJiaJu_0007Model;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.app.ConstanceValue.MSG_PEIWANG_SUCCESS;

public class TianJiaPuTongSheBeiActivity extends BaseActivity {
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.rlv_shebeilist)
    RecyclerView rlvShebeilist;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    @BindView(R.id.tv_huashu)
    TextView tvHuashu;
    @BindView(R.id.rl_tuichu)
    RoundRelativeLayout rlTuichu;
    @BindView(R.id.ll_main_tianjia)
    RelativeLayout llMainTianjia;
    private String ccid;
    private String serverId;
    private ZhiNengJiaJu_0007Model zhiNengJiaJu_0007Model;

    private String zhuji_device_ccid_up;
    private FenLeiContentModel fenLeiContentModel;

    private List<ZhiNengJiaJu_0007Model.MatchListBean> mDatas = new ArrayList<>();
    private OneImageAdapter oneImageAdapter;
    private TishiDialog tishiDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        zhuji_device_ccid_up = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");

        fenLeiContentModel = (FenLeiContentModel) getIntent().getSerializableExtra("fenLeiContentModel");
        animationView.setAnimation("lottie.json");
        animationView.setImageAssetsFolder("images/");
        animationView.loop(true);
        animationView.playAnimation();

        oneImageAdapter = new OneImageAdapter(R.layout.item_rlv_shebeilist, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rlvShebeilist.setLayoutManager(linearLayoutManager);
        rlvShebeilist.setAdapter(oneImageAdapter);

        zhuangZhiLeixing = fenLeiContentModel.type;
        zhuangZhiLeiXingXingHao = fenLeiContentModel.category;
        sendMqttTianJiaSheBei();
        initHuidiao();

        rlTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice notice = new Notice();
                notice.type = MSG_PEIWANG_SUCCESS;
                RxBus.getDefault().sendRx(notice);
                finish();
            }
        });
    }

    private ZnjjMqttMingLing znjjMqttMingLing;

    private String zhuangZhiLeixing;//装置类型
    private String zhuangZhiLeiXingXingHao;//装置类型的型号

    public void sendMqttTianJiaSheBei() {
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
//        znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(zhuji_device_ccid_up, serverId, new IMqttActionListener() {
//            @Override
//            public void onSuccess(IMqttToken asyncActionToken) {
//
//            }
//
//            @Override
//            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//
//            }
//        });
        // TODO: 2021/2/2 添加的命令待赋值
        String str = "M12" + zhuangZhiLeixing + zhuangZhiLeiXingXingHao + "2.";
        Log.i("Rair", str);


        znjjMqttMingLing.tianJiaSheBei(zhuji_device_ccid_up, serverId, str, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    public void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_TIANJIASHEBEI) {
                    llMainTianjia.setVisibility(View.VISIBLE);
                    zhiNengJiaJu_0007Model = (ZhiNengJiaJu_0007Model) message.content;
                    for (int i = 0; i < zhiNengJiaJu_0007Model.getMatch_list().size(); i++) {
                        mDatas.add(zhiNengJiaJu_0007Model.getMatch_list().get(i));
                    }
                    oneImageAdapter.notifyDataSetChanged();


                    if (tishiDialog==null){
                        tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {
                                Notice notice = new Notice();
                                notice.type = MSG_PEIWANG_SUCCESS;
                                RxBus.getDefault().sendRx(notice);
                                finish();
                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {
                                sendMqttTianJiaSheBei();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent("成功添加设备，是否继续？");
                        tishiDialog.setTextCancel("退出");
                        tishiDialog.setTextConfirm("继续");
                        tishiDialog.setCancelable(false);
                        tishiDialog.setCanceledOnTouchOutside(false);
                        tishiDialog.setDismissAfterClick(true);
                    }

                   if (!tishiDialog.isShowing()){
                       tishiDialog.show();
                   }
                } else if (message.type == ConstanceValue.MSG_PEIWANG_ERROR) {
                    TishiDialog tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            Notice notice = new Notice();
                            notice.type = MSG_PEIWANG_SUCCESS;
                            RxBus.getDefault().sendRx(notice);
                            finish();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            sendMqttTianJiaSheBei();
                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });
                    tishiDialog.setTextContent((String) message.content);
                    tishiDialog.setTextCancel("退出");
                    tishiDialog.setTextConfirm("重试");
                    tishiDialog.setCancelable(false);
                    tishiDialog.setCanceledOnTouchOutside(false);
                    tishiDialog.setDismissAfterClick(true);
                    tishiDialog.show();
                }
            }
        }));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tianjia_putongshebei;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加设备");

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
     *
     * @param context
     */
    public static void actionStart(Context context, FenLeiContentModel fenLeiContentModel) {
        Intent intent = new Intent(context, TianJiaPuTongSheBeiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("fenLeiContentModel", fenLeiContentModel);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (znjjMqttMingLing != null) {
            znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }
}
