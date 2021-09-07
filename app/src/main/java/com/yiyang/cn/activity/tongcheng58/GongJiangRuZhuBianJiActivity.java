package com.yiyang.cn.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.LatLonPoint;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.ShuRuInterView;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tongcheng58.model.TcUpLoadModel;
import com.yiyang.cn.adapter.XiangQingTuAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.TongYongShuRuDIalog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.BianMinFaBuBean;
import com.yiyang.cn.model.GongJiangRuZhuBean;
import com.yiyang.cn.model.GongJiangXinXiModel;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.util.AlertUtil;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.app.App.JINGDU;
import static com.yiyang.cn.app.App.WEIDU;
import static com.yiyang.cn.get_net.Urls.TONGCHENG;

public class GongJiangRuZhuBianJiActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.tv_gongjiang_huashu)
    TextView tvGongjiangHuashu;
    @BindView(R.id.rl_gongjiang_touxiang)
    RelativeLayout rlGongjiangTouxiang;
    @BindView(R.id.rl_gongjiang_xingming)
    RelativeLayout rlGongjiangXingming;
    @BindView(R.id.rl_fuwugongzhong)
    RelativeLayout rlFuwugongzhong;
    @BindView(R.id.iv_shenfenzheng_zhengmian)
    ImageView ivShenfenzhengZhengmian;
    @BindView(R.id.iv_shenfengzheng_fanmian)
    ImageView ivShenfengzhengFanmian;
    @BindView(R.id.rl_tianxie_jianjie)
    RelativeLayout rlTianxieJianjie;
    @BindView(R.id.rl_fuwu_quyu)
    RelativeLayout rlFuwuQuyu;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @BindView(R.id.ll_yuedu)
    LinearLayout llYuedu;
    @BindView(R.id.rrl_tijiao)
    RoundRelativeLayout rrlTijiao;
    @BindView(R.id.tv_gongjiang_xingming)
    TextView tvGongjiangXingming;
    @BindView(R.id.ll_fuwugongzhong)
    LinearLayout llFuwugongzhong;
    @BindView(R.id.tv_geren_jianjie)
    TextView tvGerenJianjie;
    @BindView(R.id.tv_fuwu_gongzhong)
    TextView tvFuwuGongzhong;
    @BindView(R.id.iv_yuedu)
    ImageView ivYuedu;
    @BindView(R.id.rlv_tupian)
    RecyclerView rlvTupian;
    @BindView(R.id.iv_touxiang)
    ImageView ivTouxiang;
    @BindView(R.id.tv_weixinhao)
    TextView tvWeixinhao;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;

    private String xuanZe = "0";//0未选择 1已选择
    GongJiangRuZhuBean gongJiangRuZhuBean = new GongJiangRuZhuBean();
    XiangQingTuAdapter xiangQingTuAdapter;
    List<BianMinFaBuBean.ProBean> list = new ArrayList<>();

    private String shangChuanTuPianLeiXing = "0";//0是头像 1是服务项目 2是身份证正面 3.身份证反面
    TishiDialog tishiDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
        irid = getIntent().getStringExtra("irid");
        gongJiangRuZhuBean.proBeanList = new ArrayList<>();
        rlGongjiangTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shangChuanTuPianLeiXing = "0";
                getPaiZhaoPhone();
            }
        });

        rrlTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(gongJiangRuZhuBean.touXiang)) {
                    UIHelper.ToastMessage(mContext, "头像不能为空");

                    return;
                } else if (StringUtils.isEmpty(gongJiangRuZhuBean.lianXiRenMingCheng)) {
                    UIHelper.ToastMessage(mContext, "联系人名称不能为空");
                } else if (StringUtils.isEmpty(gongJiangRuZhuBean.fuWuGongZhong)) {
                    UIHelper.ToastMessage(mContext, "服务工种不能为空");
                } else if (gongJiangRuZhuBean.proBeanList.size() == 0) {
                    UIHelper.ToastMessage(mContext, "上传图片不能为空");
                } else if (StringUtils.isEmpty(gongJiangRuZhuBean.shenFenZhengZhengMian)) {
                    UIHelper.ToastMessage(mContext, "请上传身份证正面");
                } else if (StringUtils.isEmpty(gongJiangRuZhuBean.shenFenZhengFanMian)) {
                    UIHelper.ToastMessage(mContext, "请上传身份证反面");
                } else if (StringUtils.isEmpty(gongJiangRuZhuBean.diZhi)) {
                    UIHelper.ToastMessage(mContext, "请上传地址");
                } else if (StringUtils.isEmpty(gongJiangRuZhuBean.weiXinHao)) {
                    UIHelper.ToastMessage(mContext, "请上传微信号");
                }

                tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                    @Override
                    public void onClickCancel(View v, TishiDialog dialog) {

                    }

                    @Override
                    public void onClickConfirm(View v, TishiDialog dialog) {
                        saveData();
                    }

                    @Override
                    public void onDismiss(TishiDialog dialog) {

                    }
                });
                tishiDialog.setTextContent("是否确认提交当前内容");
                tishiDialog.show();

            }
        });

        rlGongjiangXingming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {

                    }

                    @Override
                    public void submit(String str) {
                        tvGongjiangXingming.setText(str);
                        gongJiangRuZhuBean.lianXiRenMingCheng = str;

                    }
                }, "请输入姓名");
                tongYongShuRuDIalog.show();
            }
        });

        llFuwugongzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseFuWuGongZhongActivity.actionStart(mContext);
                //gongJiangGongZhong();
            }
        });


        ivShenfenzhengZhengmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shangChuanTuPianLeiXing = "2";
                getPaiZhaoPhone();
            }
        });
        ivShenfengzhengFanmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shangChuanTuPianLeiXing = "3";
                getPaiZhaoPhone();
            }
        });

        rlTianxieJianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
//                    @Override
//                    public void cannel() {
//
//                    }
//
//                    @Override
//                    public void submit(String str) {
//                        tvGerenJianjie.setText(str);
//                    }
//                }, "请填写简介");
//                tongYongShuRuDIalog.show();

                TcInputActivity.actionStart(mContext, "jieshao", tvGerenJianjie.getText().toString());
            }
        });

        llYuedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xuanZe1.equals("0")) {
                    ivYuedu.setBackgroundResource(R.mipmap.yixuanze);
                    xuanZe1 = "1";
                } else if (xuanZe1.equals("1")) {
                    ivYuedu.setBackgroundResource(R.mipmap.weixuanze);
                    xuanZe1 = "0";
                }

            }
        });
        ivYuedu.setBackgroundResource(R.mipmap.weixuanze);

        BianMinFaBuBean.ProBean xiangQingTuBean = new BianMinFaBuBean.ProBean();
        xiangQingTuBean.type = "0";
        list.add(xiangQingTuBean);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        rlvTupian.setLayoutManager(gridLayoutManager);
        xiangQingTuAdapter = new XiangQingTuAdapter(R.layout.item_xiangqingtu, list);
        rlvTupian.setAdapter(xiangQingTuAdapter);

        xiangQingTuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position).type.equals("0")) {
                    shangChuanTuPianLeiXing = "1";
                    getPaiZhaoPhone();
                    //UIHelper.ToastMessage(mContext, "弹出相册和相机");
                } else {
                    //UIHelper.ToastMessage(mContext, "此时点击要无反应");
                }
            }
        });

        Glide.with(mContext).load(R.mipmap.shoppicture_icon_add).into(ivTouxiang);
        rlWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {

                    }

                    @Override
                    public void submit(String str) {
                        tvWeixinhao.setText(str);
                        gongJiangRuZhuBean.weiXinHao = str;
                    }
                }, "请输入微信号");

                tongYongShuRuDIalog.show();
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_FUWUGONGZHONG) {
                    fuWuGongZhongList = (List<String>) message.content;
                    tvFuwuGongzhong.setText(fuWuGongZhongList.get(0));
                    gongJiangRuZhuBean.fuWuGongZhong = fuWuGongZhongList.get(1);
                } else if (message.type == ConstanceValue.MSG_BIANMINFABU_HUICHUANDIZHI) {
                    //bianMinFaBuBean.diZhi = (String) message.content;
                    List<Object> list = (List<Object>) message.content;

                    gongJiangRuZhuBean.diZhi = (String) list.get(0);
                    LatLonPoint latLonPoint = (LatLonPoint) list.get(1);

                    gongJiangRuZhuBean.weiDu = String.valueOf(latLonPoint.getLatitude());
                    gongJiangRuZhuBean.jingDu = String.valueOf(latLonPoint.getLongitude());

                    tvDizhi.setText((CharSequence) list.get(0));
                } else if (message.type == ConstanceValue.MSG_TONGYONG_INPUT) {
                    String content = (String) message.content;
                    String input_type = message.input_type;
                    if (input_type.equals("jieshao")) {
                        tvGerenJianjie.setText(content);
                        gongJiangRuZhuBean.geRenJianJie = content;
                    }
                }
            }
        }));

        rlFuwuQuyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PoiKeywordSearchActivity.class);
                startActivity(intent);
            }
        });

        String dizhi = PreferenceHelper.getInstance(mContext).getString(AppConfig.ADDRESS, "");
        gongJiangRuZhuBean.weiDu = PreferenceHelper.getInstance(mContext).getString(WEIDU, "");
        gongJiangRuZhuBean.jingDu = PreferenceHelper.getInstance(mContext).getString(JINGDU, "");
        tvDizhi.setText(dizhi);
        gongJiangRuZhuBean.diZhi = dizhi;

        getData();
    }

    List<String> fuWuGongZhongList;
    String fuWuGongZhong;

    private TakePhoto takePhoto;

    public void getPaiZhaoPhone() {
        String[] items = {"拍照", "相册"};
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
                    case 0:
                        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                        break;
                    case 1:
                        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                        break;
                }
                dialog.dismiss();

            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_gongjiang_ruzhu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("工匠入驻");
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

    private String xuanZe1 = "0";//0为选择 1选择
    private String gongZhong = "";


    public static void actionStart(Context context, String irid) {
        Intent intent = new Intent(context, GongJiangRuZhuBianJiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("irid", irid);
        context.startActivity(intent);
    }

    private InvokeParam invokeParam;

    @Override
    public void takeSuccess(TResult result) {
        //此处使用原图路径，不压缩
        File file = new File(result.getImage().getOriginalPath());
        OkGo.<AppResponse<TcUpLoadModel.DataBean>>post(Urls.TONG_CHENG_UPLOAD)
                .tag(this)//
                .isMultipart(true)
                .params("key", Urls.key)
                .params("token", UserManager.getManager(mContext).getAppToken())
                .params("type", "1")
                .params("file", file)
                .execute(new JsonCallback<AppResponse<TcUpLoadModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcUpLoadModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
//                            if (type == 1) {
//                                Glide.with(mContext).load(file.getPath()).into(ivLogoAdd);
//                                TcUpLoadModel.DataBean dataBean = response.body().data.get(0);
//                                ir_inst_logo = dataBean.getImg_url();
//                                ir_inst_logo_id = dataBean.getImg_id();
//                            }

                            if (shangChuanTuPianLeiXing.equals("0")) {
                                Glide.with(mContext).load(response.body().data.get(0).getImg_url()).into(ivTouxiang);
                                gongJiangRuZhuBean.touXiang = response.body().data.get(0).getImg_url();
                                gongJiangRuZhuBean.touXiangId = response.body().data.get(0).getImg_id();
                            } else if (shangChuanTuPianLeiXing.equals("1")) {
                                BianMinFaBuBean.ProBean xiangQingTuBean = new BianMinFaBuBean.ProBean();
                                xiangQingTuBean.type = "1";
                                xiangQingTuBean.ir_img_url = response.body().data.get(0).getImg_url();
                                xiangQingTuBean.ir_img_id = response.body().data.get(0).getImg_id();
                                gongJiangRuZhuBean.proBeanList.add(xiangQingTuBean);

                                list.clear();
                                for (int i = 0; i < gongJiangRuZhuBean.proBeanList.size(); i++) {
                                    list.add(gongJiangRuZhuBean.proBeanList.get(i));
                                }
                                BianMinFaBuBean.ProBean xiangQingTuBean1 = new BianMinFaBuBean.ProBean();
                                xiangQingTuBean1.type = "0";
                                list.add(xiangQingTuBean1);
                                xiangQingTuAdapter.notifyDataSetChanged();
                            } else if (shangChuanTuPianLeiXing.equals("2")) {
                                Glide.with(mContext).load(response.body().data.get(0).getImg_url()).into(ivShenfenzhengZhengmian);
                                gongJiangRuZhuBean.shenFenZhengZhengMian = response.body().data.get(0).getImg_url();
                                gongJiangRuZhuBean.shenFenZhengZhengMianId = response.body().data.get(0).getImg_id();
                            } else if (shangChuanTuPianLeiXing.equals("3")) {
                                Glide.with(mContext).load(response.body().data.get(0).getImg_url()).into(ivShenfengzhengFanmian);
                                gongJiangRuZhuBean.shenFenZhengFanMian = response.body().data.get(0).getImg_url();
                                gongJiangRuZhuBean.shenFenZhengFanMianId = response.body().data.get(0).getImg_id();
                            }

                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<TcUpLoadModel.DataBean>> response) {
                        super.onError(response);
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });

    }

    @Override
    public void takeFail(TResult result, String msg) {

        UIHelper.ToastMessage(mContext, msg);
        //showToast(msg);
    }

    @Override
    public void takeCancel() {
        UIHelper.ToastMessage(mContext, "取消选择");
        //showToast("取消选择");
    }


    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    /**
     * 配置takerPhoto参数
     */
    public CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(800).setAspectY(800);
        return builder.create();
    }

    public void saveData() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17011");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("x", gongJiangRuZhuBean.weiDu);
        map.put("y", gongJiangRuZhuBean.jingDu);
        map.put("ir_type", "1");
        map.put("ir_id", irid);
//发布者头像
        map.put("ir_personnal_img_url", gongJiangRuZhuBean.touXiang);
        map.put("ir_personnal_img_id", gongJiangRuZhuBean.touXiangId);
        //发布者/联系人名称
        map.put("ir_personnal_name", gongJiangRuZhuBean.lianXiRenMingCheng);
        //服务工种
        map.put("ir_personnal_profession", gongJiangRuZhuBean.fuWuGongZhong);
        //pro
        map.put("pro", gongJiangRuZhuBean.proBeanList);
        //身份证正面
        map.put("ir_personnal_identity_up", gongJiangRuZhuBean.shenFenZhengZhengMian);
        //身份证正面id
        map.put("ir_personnal_identity_up_id", gongJiangRuZhuBean.shenFenZhengZhengMianId);
        //身份证反面
        map.put("ir_personnal_identity_down", gongJiangRuZhuBean.shenFenZhengFanMian);
        //身份证反面id
        map.put("ir_personnal_identity_down_id", gongJiangRuZhuBean.shenFenZhengFanMianId);
        //地址
        map.put("addr", gongJiangRuZhuBean.diZhi);
        //微信号
        map.put("ir_wx_number", gongJiangRuZhuBean.weiXinHao);
        // TODO: 2021/4/1 待调试
        map.put("ir_validity", gongJiangRuZhuBean.geRenJianJie);

        Gson gson = new Gson();
        OkGo.<AppResponse<Home.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Home.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Home.DataBean>> response) {
                        Logger.d(gson.toJson(response.body()));
                        tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
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
                        tishiDialog.setTextContent("提交成功");
                        tishiDialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<Home.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<Home.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private String irid;

    public void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17006");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("x", PreferenceHelper.getInstance(mContext).getString(WEIDU, ""));
        map.put("y", PreferenceHelper.getInstance(mContext).getString(JINGDU, ""));
        map.put("ir_id", irid);
        map.put("operate_type", "2");
        Gson gson = new Gson();
        OkGo.<AppResponse<GongJiangXinXiModel.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GongJiangXinXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GongJiangXinXiModel.DataBean>> response) {
                        Logger.d(gson.toJson(response.body()));
                        GongJiangXinXiModel.DataBean dataBean = new GongJiangXinXiModel.DataBean();
                        dataBean = response.body().data.get(0);
                        gongJiangRuZhuBean.geRenJianJie = dataBean.getIr_validity();
                        String fuWuGongZhong = "";
                        String fuWuGongZhongId = "";
                        for (int i = 0; i < dataBean.getType_array().size(); i++) {
                            if (dataBean.getType_array().get(i).getDefaultX().equals("1")) {

                                fuWuGongZhong = fuWuGongZhong + " " + dataBean.getType_array().get(i).getName();
                                fuWuGongZhongId = fuWuGongZhongId + dataBean.getType_array().get(i).getId() + ",";
                            }
                        }


                        fuWuGongZhongId = fuWuGongZhongId.substring(0, fuWuGongZhongId.length() - 1);


                        gongJiangRuZhuBean.fuWuGongZhong = fuWuGongZhongId;

                        for (int i = 0; i < dataBean.getImgList().size(); i++) {
                            BianMinFaBuBean.ProBean proBean = new BianMinFaBuBean.ProBean();
                            proBean.ir_img_id = dataBean.getImgList().get(i).getIr_img_id();
                            proBean.ir_img_url = dataBean.getImgList().get(i).getIr_img_url();
                            proBean.type = "1";
                            gongJiangRuZhuBean.proBeanList.add(proBean);
                        }

                        list.clear();
                        for (int i = 0; i < gongJiangRuZhuBean.proBeanList.size(); i++) {
                            list.add(gongJiangRuZhuBean.proBeanList.get(i));
                        }
                        BianMinFaBuBean.ProBean xiangQingTuBean1 = new BianMinFaBuBean.ProBean();
                        xiangQingTuBean1.type = "0";
                        list.add(xiangQingTuBean1);

                        xiangQingTuAdapter.notifyDataSetChanged();


                        /**
                         *         //身份证正面
                         *         map.put("ir_personnal_identity_up", gongJiangRuZhuBean.shenFenZhengZhengMian);
                         *         //身份证正面id
                         *         map.put("ir_personnal_identity_up_id", gongJiangRuZhuBean.shenFenZhengZhengMianId);
                         *         //身份证反面
                         *         map.put("ir_personnal_identity_down", gongJiangRuZhuBean.shenFenZhengFanMian);
                         *         //身份证反面id
                         *         map.put("ir_personnal_identity_down_id", gongJiangRuZhuBean.shenFenZhengFanMianId);
                         */
                        gongJiangRuZhuBean.shenFenZhengZhengMian = dataBean.getIr_personnal_identity_up();
                        gongJiangRuZhuBean.shenFenZhengZhengMianId = dataBean.getIr_personnal_identity_up_id();
                        gongJiangRuZhuBean.shenFenZhengFanMianId = dataBean.getIr_personnal_identity_down_id();
                        gongJiangRuZhuBean.shenFenZhengFanMian = dataBean.getIr_personnal_identity_down();
                        gongJiangRuZhuBean.diZhi = dataBean.getAddr();

                        gongJiangRuZhuBean.weiDu = dataBean.getX();
                        gongJiangRuZhuBean.jingDu = dataBean.getY();
                        gongJiangRuZhuBean.weiXinHao = dataBean.getIr_wx_number();
                        gongJiangRuZhuBean.touXiang = dataBean.getIr_personnal_img_url();
                        gongJiangRuZhuBean.touXiangId = dataBean.getIr_personnal_img_id();

                        gongJiangRuZhuBean.lianXiRenMingCheng = dataBean.getIr_personnal_name();
                        Glide.with(mContext).load(gongJiangRuZhuBean.touXiang).into(ivTouxiang);
                        tvGongjiangXingming.setText(gongJiangRuZhuBean.lianXiRenMingCheng);

                        tvFuwuGongzhong.setText(fuWuGongZhong);

                        Glide.with(mContext).load(gongJiangRuZhuBean.shenFenZhengZhengMian).into(ivShenfenzhengZhengmian);
                        Glide.with(mContext).load(gongJiangRuZhuBean.shenFenZhengFanMian).into(ivShenfengzhengFanmian);
                        tvGerenJianjie.setText(gongJiangRuZhuBean.geRenJianJie);
                        tvWeixinhao.setText(gongJiangRuZhuBean.weiXinHao);


                    }

                    @Override
                    public void onError(Response<AppResponse<GongJiangXinXiModel.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<GongJiangXinXiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}
