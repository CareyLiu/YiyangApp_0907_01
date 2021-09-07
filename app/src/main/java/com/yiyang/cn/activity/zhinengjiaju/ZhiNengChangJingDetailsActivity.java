package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.ShuRuInterView;
import com.yiyang.cn.adapter.ZhiNengChangJingSheBeiAdapter;
import com.yiyang.cn.adapter.ZhiNengChangJingSheBeiAdapter1;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.ShuRuDIalog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.BianJiDingShiNeedModel;
import com.yiyang.cn.model.ChangJingWhereModel;
import com.yiyang.cn.model.ChangJingXiangQingModel;
import com.yiyang.cn.model.ChangJingZhiXingModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.dialog.newdia.TishiDialog.TYPE_CAOZUO;
import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengChangJingDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.rl_yijianzhixing)
    RoundRelativeLayout rlYijianzhixing;
    @BindView(R.id.rrl_shebei)
    RecyclerView rrlShebei;
    List<ChangJingXiangQingModel.DataBean.ImplementListBean> mDatas = new ArrayList<>();

    ZhiNengChangJingSheBeiAdapter zhiNengChangJingSheBeiAdapter;
    @BindView(R.id.iv_image_shengxiao)
    ImageView ivImageShengxiao;
    @BindView(R.id.rl_shengxiaoshijian)
    RoundRelativeLayout rlShengxiaoshijian;
    @BindView(R.id.rl_yijianzhixing_tianjiatiaojian)
    RoundRelativeLayout rlYijianzhixingTianjiatiaojian;
    @BindView(R.id.ll_yijian_zhixing)
    LinearLayout llYijianZhixing;

    @BindView(R.id.tv_changjingmingcheng)
    TextView tvChangjingmingcheng;
    @BindView(R.id.rll_tianjiashebei)
    RoundRelativeLayout rllTianjiashebei;
    @BindView(R.id.rl_changjing)
    RoundRelativeLayout rlChangjing;
    @BindView(R.id.ll_yijianzhixing_tianjia_tiaojian)
    LinearLayout llYijianzhixingTianjiaTiaojian;
    @BindView(R.id.tv_yijianzhixing)
    TextView tvYijianzhixing;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_shanchu)
    RoundRelativeLayout rlShanchu;
    private String senceType;
    private String scene_id;
    ShuRuDIalog shuRuDIalog;
    ImageView icIcon;
    private String familyId;
    String iconStr;
    ZhiNengChangJingSheBeiAdapter1 zhiNengChangJingSheBeiAdapter1;
    private List<ChangJingZhiXingModel> mSheBeiDatas = new ArrayList<>();
    private String guanLiYuan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecycleView();
        senceType = getIntent().getStringExtra("senceType");
        scene_id = getIntent().getStringExtra("scene_id");
        familyId = getIntent().getStringExtra("familyId");
        guanLiYuan = PreferenceHelper.getInstance(mContext).getString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "0");
        rlChangjing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guanLiYuan.equals("1")) {
                    shuRuDIalog = new ShuRuDIalog(mContext, new ShuRuInterView() {
                        @Override
                        public void cannel() {
                            shuRuDIalog.dismiss();
                        }

                        @Override
                        public void submit(String str) {
                            tvChangjingmingcheng.setText(str);
                            bianJiChangJingMingCheng(str);
                        }
                    });
                    shuRuDIalog.show();
                } else {
                    UIHelper.ToastMessage(mContext, "您不是管理员暂无此权限");
                }

            }
        });

        if (senceType.equals("1")) {
            //一键执行

            llYijianzhixingTianjiaTiaojian.setVisibility(View.GONE);

        } else if (senceType.equals("2")) {
            tvTime.setVisibility(View.VISIBLE);
            //2定时
            llYijianzhixingTianjiaTiaojian.setVisibility(View.GONE);
            rlYijianzhixing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ChuangJianZhiNengDingShiActivity.actionStart(mContext,"youzhi");
                    if (guanLiYuan.equals("1")) {
                        Intent intent = new Intent(mContext, ChuangJianZhiNengDingShiActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.putExtra("XiangQingUse", "xiangQingUse");
                        startActivityForResult(intent, 1);
                    } else {
                        UIHelper.ToastMessage(mContext, "您不是管理员暂无此权限");
                    }
                }
            });
        } else if (senceType.equals("3")) {
            //3 动作触发
            llYijianzhixingTianjiaTiaojian.setVisibility(View.GONE);
            tvTime.setVisibility(View.VISIBLE);
            //一键执行 动作触发
        }

        getChangJingQing();

        rlYijianzhixingTianjiatiaojian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_ICON) {
                    iconStr = (String) message.content;
                    Glide.with(mContext).load(iconStr).into(icIcon);
                    changeChangJingIcon(iconStr);
                } else if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_SHEBEILIEBIAO) {

                    ChangJingZhiXingModel changJingZhiXingModel = (ChangJingZhiXingModel) message.content;
                    List<ChangJingZhiXingModel> changJingZhiXingModels = new ArrayList<>();
                    changJingZhiXingModels.add(changJingZhiXingModel);
                    tianJiaChangJingSheBei(changJingZhiXingModels);

                } else if (message.type == ConstanceValue.MSG_DINGSHI_NEED) {
                    BianJiDingShiNeedModel bianJiDingShiNeedModel = (BianJiDingShiNeedModel) message.content;
                    leiXing = bianJiDingShiNeedModel.leiXing;
                    kaiShiSHiJian = bianJiDingShiNeedModel.kaiShiSHiJian;
                    chongFuLeiXing = bianJiDingShiNeedModel.chongFuLeiXing;
                    xingQiList = bianJiDingShiNeedModel.xingQiList;
                    bianJiChangJingShiJian();
                }
            }
        }));

        rlShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TishiDialog tishiDialog = new TishiDialog(mContext, TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
                    @Override
                    public void onClickCancel(View v, TishiDialog dialog) {

                    }

                    @Override
                    public void onClickConfirm(View v, TishiDialog dialog) {
                        getShanChuNet();
                    }

                    @Override
                    public void onDismiss(TishiDialog dialog) {

                    }
                });
                tishiDialog.setTextContent("是否确定删除场景");
                tishiDialog.show();
            }
        });
    }

    public void getShanChuNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16063");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("scene_id", scene_id);
        map.put("family_id", familyId);
        map.put("type", "1");

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        Notice notice = new Notice();
                        notice.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                        sendRx(notice);

                        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_DELETE, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {
                                finish();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        dialog.setTextContent("场景删除成功");
                        dialog.setTextConfirm("确定");
                        dialog.show();

                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }
                });
    }

    private void initRecycleView() {
        zhiNengChangJingSheBeiAdapter = new ZhiNengChangJingSheBeiAdapter(R.layout.item_zhinengchangjing_shebei, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rrlShebei.setLayoutManager(linearLayoutManager);
        rrlShebei.setAdapter(zhiNengChangJingSheBeiAdapter);

        View view = View.inflate(mContext, R.layout.item_zhinengchangjing_shebei_fotter, null);
        icIcon = view.findViewById(R.id.iv_icon);
        RelativeLayout rlZhiNengTuBiao = view.findViewById(R.id.rl_zhinengtubiao);
        rlZhiNengTuBiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guanLiYuan.equals("1")) {
                    ChangJingTuBiaoActivity.actionStart(mContext);
                } else {
                    UIHelper.ToastMessage(mContext, "您不是管理员暂无此权限");
                }
            }
        });

        zhiNengChangJingSheBeiAdapter.addFooterView(view);
        zhiNengChangJingSheBeiAdapter.setNewData(mDatas);

        rllTianjiashebei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guanLiYuan.equals("1")) {
                    SheBeiBianHuaActivity.actionStart(mContext, familyId, "");
                } else {
                    UIHelper.ToastMessage(mContext, "您不是管理员暂无此权限");
                }
            }
        });
        zhiNengChangJingSheBeiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {

                    case R.id.ll_item:
                        if (guanLiYuan.equals("1")) {
                            String[] items = {"删除"};
                            final ActionSheetDialog dialog = new ActionSheetDialog(mContext, items, null);
                            dialog.isTitleShow(false).show();
                            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                @Override
                                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    switch (position) {
                                        case 0:
                                            shanChuChangJingSheBei(mDatas.get(position).getScene_oper_id());
                                            break;
                                    }
                                    dialog.dismiss();

                                }
                            });
                        } else {
                            UIHelper.ToastMessage(mContext, "您不是管理员暂无此权限");
                        }

                        break;
                }
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_zhinengchangjing_details;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("场景");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        //   tv_rightTitle.setVisibility(View.VISIBLE);
        //tv_rightTitle.setText("编辑");
//        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UIHelper.ToastMessage(mContext, "编辑");
//            }
//        });
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    public static void actionStart(Context context, String senceType, String scene_id, String familyId) {
        Intent intent = new Intent(context, ZhiNengChangJingDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("senceType", senceType);
        intent.putExtra("scene_id", scene_id);
        intent.putExtra("familyId", familyId);
        context.startActivity(intent);
    }

    /**
     * 获得场景详情
     */
    private void getChangJingQing() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16066");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("scene_id", scene_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        tvChangjingmingcheng.setText(response.body().data.get(0).getScene_title());
                        Glide.with(mContext).load(response.body().data.get(0).getScene_pic()).into(icIcon);

                        Glide.with(mContext).load(response.body().data.get(0).getCondition_list().get(0).getImg_url()).into(ivImage);
                        tvYijianzhixing.setText(response.body().data.get(0).getCondition_list().get(0).getDevice_name());

                        tvTime.setText(response.body().data.get(0).getCondition_list().get(0).getImplement_detail());

                        mDatas.clear();
                        mDatas.addAll(response.body().data.get(0).getImplement_list());
                        zhiNengChangJingSheBeiAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }
                });
    }

    private void bianJiChangJingMingCheng(String strMingCheng) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16063");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("scene_id", scene_id);
        map.put("family_id", familyId);
        map.put("type", "2");
        map.put("scene_title", strMingCheng);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "名称修改成功");

                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }
                });
    }


    private void shanChuChangJingSheBei(String scene_oper_id) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16063");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("scene_id", scene_id);
        map.put("family_id", familyId);
        map.put("type", "3");
        map.put("scene_oper_id", scene_oper_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "删除成功");
                        getChangJingQing();

                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }
                });
    }


    private void changeChangJingIcon(String iconUrl) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16063");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("scene_id", scene_id);
        map.put("family_id", familyId);
        map.put("type", "6");
        map.put("scene_pic", iconUrl);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "图片修改成功");
                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }
                });
    }

    private void tianJiaChangJingSheBei(List<ChangJingZhiXingModel> changJingZhiXingModels) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "16063");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("scene_id", scene_id);
        map.put("family_id", familyId);
        map.put("type", "5");
        map.put("go", changJingZhiXingModels);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "增加场景成功");
                        getChangJingQing();

                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }
                });
    }

    private void bianJiChangJingShiJian() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "16063");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("scene_id", scene_id);
        map.put("family_id", familyId);
        map.put("type", "4");
        ChangJingWhereModel changJingWhereModel = new ChangJingWhereModel();
        changJingWhereModel.week_time_begin = kaiShiSHiJian;
        changJingWhereModel.week_time_oper = chongFuLeiXing;
        List<ChangJingWhereModel> changJingWhereModels = new ArrayList<>();

        if (xingQiList != null && xingQiList.size() > 0) {
            if (xingQiList.get(0).equals("1")) {
                changJingWhereModel.week_1 = "1";
            } else {
                changJingWhereModel.week_1 = "2";
            }

            if (xingQiList.get(1).equals("1")) {
                changJingWhereModel.week_2 = "1";
            } else {
                changJingWhereModel.week_2 = "2";
            }

            if (xingQiList.get(2).equals("1")) {
                changJingWhereModel.week_3 = "1";
            } else {
                changJingWhereModel.week_3 = "2";
            }

            if (xingQiList.get(3).equals("1")) {
                changJingWhereModel.week_4 = "1";
            } else {
                changJingWhereModel.week_4 = "2";
            }
            if (xingQiList.get(4).equals("1")) {
                changJingWhereModel.week_5 = "1";
            } else {
                changJingWhereModel.week_5 = "2";
            }
            if (xingQiList.get(5).equals("1")) {
                changJingWhereModel.week_6 = "1";
            } else {
                changJingWhereModel.week_6 = "2";
            }
            if (xingQiList.get(6).equals("1")) {
                changJingWhereModel.week_7 = "1";
            } else {
                changJingWhereModel.week_7 = "2";
            }
            changJingWhereModels.add(changJingWhereModel);
            map.put("where", changJingWhereModels);
        }
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "时间修改成功");
                        getChangJingQing();

                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }
                });
    }


    private String leiXing;
    private String kaiShiSHiJian;
    private String chongFuLeiXing;
    private List<String> xingQiList = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if (resultCode == 3) {
//                Bundle bundle = intent.getExtras();
//                leiXing = bundle.getString("leiXing");
//                kaiShiSHiJian = bundle.getString("kaiShiShiJian");
//                chongFuLeiXing = bundle.getString("chongFuLeiXing");
//                xingQiList = (List<String>) bundle.getSerializable("mDatas");
//
//                bianJiChangJingShiJian();
            }
        }

    }
}
