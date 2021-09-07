package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.SuiYiTieModel;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class SuiYiTieActivity extends BaseActivity {
    @BindView(R.id.iv_1)
    ImageButton iv1;
    @BindView(R.id.ll_1)
    RoundLinearLayout ll1;
    @BindView(R.id.iv_2)
    ImageButton iv2;
    @BindView(R.id.ll_2)
    RoundLinearLayout ll2;
    @BindView(R.id.iv_3)
    ImageButton iv3;
    @BindView(R.id.ll_3)
    RoundLinearLayout ll3;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add1)
    ImageView ivAdd1;
    @BindView(R.id.rl_left)
    RoundRelativeLayout rlLeft;
    @BindView(R.id.iv_add2)
    ImageView ivAdd2;
    @BindView(R.id.rl_center)
    RoundRelativeLayout rlCenter;
    @BindView(R.id.iv_add3)
    ImageView ivAdd3;
    @BindView(R.id.rl_right)
    RoundRelativeLayout rlRight;
    @BindView(R.id.rl_touming1)
    RelativeLayout rlTouming1;
    @BindView(R.id.rl_touming2)
    RelativeLayout rlTouming2;
    @BindView(R.id.rl_touming3)
    RelativeLayout rlTouming3;
    @BindView(R.id.tv_add1_name)
    TextView tvAdd1Name;
    @BindView(R.id.tv_add2_name)
    TextView tvAdd2Name;
    @BindView(R.id.tv_add3_name)
    TextView tvAdd3Name;
    @BindView(R.id.tvsuiyitie)
    TextView tvsuiyitie;
    @BindView(R.id.iv_one)
    ImageButton ivOne;
    @BindView(R.id.rll_one)
    RoundRelativeLayout rllOne;

    private String device_ccid = "";
    private String device_ccidup = "";
    private String paiwei = "1";
    private List<SuiYiTieModel.DataBean.DeviceListBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device_ccid = getIntent().getStringExtra("device_ccid");
        device_ccidup = getIntent().getStringExtra("device_ccid_up");
        kongJianClick();
        getnet();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_suiyitie;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("万能随意贴");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.mine_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "点击了设置");
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void kongJianClick() {
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setSelected(true);
                iv2.setSelected(false);
                iv3.setSelected(false);
                if (mDatas.size() == 3 || mDatas.size() == 2) {
                    zhiXingMqtt(0);
                }

            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setSelected(false);
                iv2.setSelected(true);
                iv3.setSelected(false);
                if (mDatas.size() == 1) {
                    zhiXingMqtt(1);
                } else if (mDatas.size() == 3) {
                    zhiXingMqtt(1);
                }

            }
        });
        rllOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setSelected(false);
                iv2.setSelected(true);
                iv3.setSelected(false);
                if (mDatas.size() == 1) {
                    zhiXingMqtt(1);
                } else if (mDatas.size() == 3) {
                    zhiXingMqtt(1);
                }
            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setSelected(false);
                iv2.setSelected(false);
                iv3.setSelected(true);
                if (mDatas.size() == 1) {
                } else if (mDatas.size() == 2) {
                    zhiXingMqtt(1);
                } else if (mDatas.size() == 3) {
                    zhiXingMqtt(2);
                }


            }
        });
        rlLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanChuang("0");
            }
        });
        rlCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanChuang("1");

            }
        });
        rlRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanChuang("2");

            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_ccid, String device_ccidup) {
        Intent intent = new Intent(context, SuiYiTieActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        intent.putExtra("device_ccid_up", device_ccidup);
        context.startActivity(intent);
    }

    public void tanChuang(String paiwei) {
        String[] items = {"设备"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Uri imageUri = Uri.fromFile(file);
                switch (position) {
//                    case 0:
//                        SuiYiTieTianJiaSheBeiActivity.actionStart(mContext, paiwei, device_ccid, device_ccidup, "1");
//                        break;
                    case 0:
                        String deviceCcidSt = "";
                        //UIHelper.ToastMessage(mContext, "绑定设备");
                        if (mDatas.get(Integer.valueOf(paiwei)) != null) {
                            deviceCcidSt = mDatas.get(Integer.valueOf(paiwei)).getDevice_ccid();
                        }
                        SuiYiTieTianJiaSheBeiActivity.actionStart(mContext, paiwei, device_ccid, device_ccidup, "2", deviceCcidSt);
                        break;
                }
                dialog.dismiss();

            }
        });

    }


    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16052");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_ccid_up", device_ccidup);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        showLoadSuccess();
                        flag = true;//第一次进入不走onresume
                        if (response.body().data.get(0).getDevice_category().equals("01")) {

                            ll2.setVisibility(View.INVISIBLE);
                            ll1.setVisibility(View.INVISIBLE);
                            ll3.setVisibility(View.INVISIBLE);

                            rlCenter.setVisibility(View.VISIBLE);
                            rlLeft.setVisibility(View.INVISIBLE);
                            rlRight.setVisibility(View.INVISIBLE);

                            ivAdd1.setVisibility(View.INVISIBLE);
                            ivAdd2.setVisibility(View.VISIBLE);
                            ivAdd3.setVisibility(View.INVISIBLE);

                            tvAdd2Name.setVisibility(View.VISIBLE);
                            tvAdd1Name.setVisibility(View.INVISIBLE);
                            tvAdd3Name.setVisibility(View.INVISIBLE);

                            tvLeft.setVisibility(View.INVISIBLE);
                            tvCenter.setVisibility(View.VISIBLE);
                            tvRight.setVisibility(View.INVISIBLE);

                            rllOne.setVisibility(View.VISIBLE);


                        } else if (response.body().data.get(0).getDevice_category().equals("02")) {

                            ll2.setVisibility(View.GONE);
                            ll1.setVisibility(View.VISIBLE);
                            ll3.setVisibility(View.VISIBLE);

                            rlCenter.setVisibility(View.GONE);
                            rlLeft.setVisibility(View.VISIBLE);
                            rlRight.setVisibility(View.VISIBLE);

                            ivAdd1.setVisibility(View.VISIBLE);
                            ivAdd2.setVisibility(View.GONE);
                            ivAdd3.setVisibility(View.VISIBLE);

                            tvAdd2Name.setVisibility(View.GONE);
                            tvAdd1Name.setVisibility(View.VISIBLE);
                            tvAdd3Name.setVisibility(View.VISIBLE);

                            tvLeft.setVisibility(View.VISIBLE);
                            tvCenter.setVisibility(View.GONE);
                            tvRight.setVisibility(View.VISIBLE);

                        } else if (response.body().data.get(0).getDevice_category().equals("03")) {

                            ll2.setVisibility(View.VISIBLE);
                            ll1.setVisibility(View.VISIBLE);
                            ll3.setVisibility(View.VISIBLE);

                            rlCenter.setVisibility(View.VISIBLE);
                            rlLeft.setVisibility(View.VISIBLE);
                            rlRight.setVisibility(View.VISIBLE);

                            ivAdd1.setVisibility(View.VISIBLE);
                            ivAdd2.setVisibility(View.VISIBLE);
                            ivAdd3.setVisibility(View.VISIBLE);

                            tvAdd2Name.setVisibility(View.VISIBLE);
                            tvAdd1Name.setVisibility(View.VISIBLE);
                            tvAdd3Name.setVisibility(View.VISIBLE);

                            tvLeft.setVisibility(View.VISIBLE);
                            tvCenter.setVisibility(View.VISIBLE);
                            tvRight.setVisibility(View.VISIBLE);
                        }

                        Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd1);
                        Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd2);
                        Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd3);

                        mDatas.addAll(response.body().data.get(0).getDevice_list());

                        showAddIcon(response);

                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<SuiYiTieModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void showAddIcon(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
        if (response.body().data.get(0).getBinding_type().equals("1")) {

            rlLeft.setVisibility(View.GONE);
            rlCenter.setVisibility(View.GONE);
            rlRight.setVisibility(View.GONE);

            tvsuiyitie.setVisibility(View.VISIBLE);


        } else if (response.body().data.get(0).getBinding_type().equals("2")) {

            tvsuiyitie.setVisibility(View.GONE);

            if (response.body().data.get(0).getDevice_category().equals("01")) {

                if (response.body().data.get(0).getDevice_list().get(0) == null) {
                    Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd2);
                } else {
                    SuiYiTieModel.DataBean dataBean1 = response.body().data.get(0);
                    Glide.with(mContext).load(dataBean1.getDevice_list().get(0).getBinding_device_type_pic()).into(ivAdd2);
                    tvAdd2Name.setText(dataBean1.getDevice_list().get(0).getBinding_device_name());
                }

            } else if (response.body().data.get(0).getDevice_category().equals("02")) {
                if (response.body().data.get(0).getDevice_list().get(0) == null) {
                    Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd1);
                } else {
                    SuiYiTieModel.DataBean dataBean0 = response.body().data.get(0);
                    Glide.with(mContext).load(dataBean0.getDevice_list().get(0).getBinding_device_type_pic()).into(ivAdd1);
                    tvAdd1Name.setText(dataBean0.getDevice_list().get(0).getBinding_device_name());
                }

                if (response.body().data.get(0).getDevice_list().get(1) == null) {
                    Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd3);
                } else {
                    SuiYiTieModel.DataBean dataBean1 = response.body().data.get(0);
                    Glide.with(mContext).load(dataBean1.getDevice_list().get(1).getBinding_device_type_pic()).into(ivAdd3);
                    tvAdd3Name.setText(dataBean1.getDevice_list().get(1).getBinding_device_name());
                }

            } else if (response.body().data.get(0).getDevice_category().equals("03")) {
                if (response.body().data.get(0).getDevice_list().get(0) == null) {
                    Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd1);
                } else {
                    SuiYiTieModel.DataBean dataBean0 = response.body().data.get(0);
                    Glide.with(mContext).load(dataBean0.getDevice_list().get(0).getBinding_device_type_pic()).into(ivAdd1);
                    tvAdd1Name.setText(dataBean0.getDevice_list().get(0).getBinding_device_name());
                }

                if (response.body().data.get(0).getDevice_list().get(1) == null) {
                    Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd2);
                } else {
                    SuiYiTieModel.DataBean dataBean1 = response.body().data.get(0);
                    Glide.with(mContext).load(dataBean1.getDevice_list().get(1).getBinding_device_type_pic()).into(ivAdd2);
                    tvAdd2Name.setText(dataBean1.getDevice_list().get(1).getBinding_device_name());
                }

                if (response.body().data.get(0).getDevice_list().get(2) == null) {
                    Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd3);
                } else {
                    SuiYiTieModel.DataBean dataBean2 = response.body().data.get(0);
                    Glide.with(mContext).load(dataBean2.getDevice_list().get(2).getBinding_device_type_pic()).into(ivAdd3);
                    tvAdd3Name.setText(dataBean2.getDevice_list().get(2).getBinding_device_name());
                }
            }


        }
    }

    private boolean flag = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (flag) {
            getnet();
        }

    }

    public ZnjjMqttMingLing mqttMingLing = null;
    String nowData;
    String hardwareData;

    public void zhiXingMqtt(int position) {

        mqttMingLing = new ZnjjMqttMingLing(mContext, mDatas.get(position).getDevice_ccid_up(), mDatas.get(position).getServer_id());

        nowData = "zn/app/" + mDatas.get(position).getServer_id() + mDatas.get(position).getDevice_ccid_up();

        hardwareData = "zn/hardware/" + mDatas.get(position).getServer_id() + mDatas.get(position).getDevice_ccid_up();
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(nowData)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅的地址:  " + nowData);
                //  UIHelper.ToastMessage(mContext, "增加订阅次数");


            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });
        mqttMingLing.subscribeShiShiXinXi(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "请求实时数据");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        mqttMingLing.setAction(mDatas.get(position).getBinding_device_ccid(), "01", new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                // UIHelper.ToastMessage(mContext, "当前装置开启");
                UIHelper.ToastMessage(mContext, "命令执行成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                UIHelper.ToastMessage(mContext, "未发送指令");
            }
        });
    }

}

