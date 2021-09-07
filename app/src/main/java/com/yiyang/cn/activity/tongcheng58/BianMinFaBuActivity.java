package com.yiyang.cn.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.LatLonPoint;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
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
import com.yiyang.cn.activity.DefaultX5WebView_HaveNameActivity;
import com.yiyang.cn.activity.SettingActivity;
import com.yiyang.cn.activity.ShuRuInterView;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tongcheng58.model.TcUpLoadModel;
import com.yiyang.cn.adapter.XiangQingTuAdapter;
import com.yiyang.cn.app.App;
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
import com.yiyang.cn.dialog.TongYongShuRuEditTextDIalog;
import com.yiyang.cn.dialog.newdia.FaBuLanMuDialog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.BianMinFaBuBean;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.model.JingYingXiangBean;
import com.yiyang.cn.model.Upload;
import com.yiyang.cn.model.XiangQingTuBean;
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
import static com.yiyang.cn.app.App.getInstance;
import static com.yiyang.cn.get_net.Urls.TONGCHENG;

public class BianMinFaBuActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.tv_tian_xie_biao_ti)
    TextView tvTianXieBiaoTi;
    @BindView(R.id.rl_fabubiaoti)
    RelativeLayout rlFabubiaoti;
    @BindView(R.id.tv_qingxuanze)
    TextView tvQingxuanze;
    @BindView(R.id.rl_fabulanmu)
    RelativeLayout rlFabulanmu;
    @BindView(R.id.rl_miaoshu_neirong)
    RelativeLayout rlMiaoshuNeirong;
    @BindView(R.id.tv_shuru_lianxiren)
    TextView tvShuruLianxiren;
    @BindView(R.id.rl_xiangxidizhi)
    RelativeLayout rlXiangxidizhi;
    @BindView(R.id.tv_xuanze)
    TextView tvXuanze;
    @BindView(R.id.iv_none_sel)
    ImageView ivNoneSel;
    TongYongShuRuDIalog shuRuDIalog;
    @BindView(R.id.tv_shurugonggao)
    TextView tvShurugonggao;
    @BindView(R.id.tv_xiangqingtu)
    TextView tvXiangqingtu;
    @BindView(R.id.rl_xiangqingtu_huashu)
    RelativeLayout rlXiangqingtuHuashu;

    String biaoTi = "";//标题
    String faBuLanMu = "";//发布栏目
    String miaoShuNeiRong = "";//描述内容
    String lianXiRen = "";//联系人
    String xiangXiDiZhi = "";//详细地址
    @BindView(R.id.tv_xiangxidizhi)
    TextView tvXiangxidizhi;
    @BindView(R.id.rl_xuanze)
    RelativeLayout rlXuanze;
    @BindView(R.id.tv_tongyi_huashu)
    TextView tvTongyiHuashu;
    @BindView(R.id.tv_fabuxuzhi)
    TextView tvFabuxuzhi;
    @BindView(R.id.rl_baocunxinxi)
    RoundRelativeLayout rlBaocunxinxi;
    public BianMinFaBuBean bianMinFaBuBean = new BianMinFaBuBean();
    @BindView(R.id.rlv_tupian)
    RecyclerView rlvTupian;
    private OptionsPickerView pvOptions;
    private int index = 0;
    XiangQingTuAdapter xiangQingTuAdapter;
    List<BianMinFaBuBean.ProBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
        bianMinFaBuBean.proBeanList = new ArrayList<>();
        irId = getIntent().getStringExtra("irId");
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_BIANMINFABU_HUICHUANDIZHI) {
                    //bianMinFaBuBean.diZhi = (String) message.content;
                    List<Object> list = (List<Object>) message.content;

                    bianMinFaBuBean.diZhi = (String) list.get(0);
                    LatLonPoint latLonPoint = (LatLonPoint) list.get(1);

                    bianMinFaBuBean.weiDu = String.valueOf(latLonPoint.getLatitude());
                    bianMinFaBuBean.jingDu = String.valueOf(latLonPoint.getLongitude());

                    tvXiangxidizhi.setText((CharSequence) list.get(0));
                } else if (message.type == ConstanceValue.MSG_TONGYONG_INPUT) {
                    String content = (String) message.content;
                    String input_type = message.input_type;
                    if (input_type.equals("jieshao")) {
                        tvShurugonggao.setText(content);
                        bianMinFaBuBean.neiRongMiaoShu = content;
                    }
                }
            }
        }));


        rlFabubiaoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {
                        shuRuDIalog.dismiss();
                    }

                    @Override
                    public void submit(String str) {
                        tvTianXieBiaoTi.setText(str);
                        //   bianJiChangJingMingCheng(str);
                        biaoTi = str;
                        bianMinFaBuBean.biaoTi = biaoTi;
                    }
                }, "请输入标题");
                shuRuDIalog.show();
            }
        });

        rlFabulanmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FaBuLanMuDialog faBuLanMuDialog = new FaBuLanMuDialog(BianMinFaBuActivity.this);
//                faBuLanMuDialog.show();
                // faBuLanMu();


                pvOptions.show();

            }
        });


        ShuRuInterView shuRuInterView = new ShuRuInterView() {
            @Override
            public void cannel() {

            }

            @Override
            public void submit(String str) {
                tvShurugonggao.setText(str);
                miaoShuNeiRong = str;
                bianMinFaBuBean.neiRongMiaoShu = miaoShuNeiRong;
            }
        };
        rlMiaoshuNeirong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TongYongShuRuEditTextDIalog tongYongShuRuEditTextDIalog = new TongYongShuRuEditTextDIalog(mContext, shuRuInterView, "请输入您要发布的信息");
//                tongYongShuRuEditTextDIalog.show();

                TcInputActivity.actionStart(mContext, "jieshao", tvShurugonggao.getText().toString());
            }
        });


        tvShuruLianxiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {

                    }

                    @Override
                    public void submit(String str) {
                        tvShuruLianxiren.setText(str);
                        bianMinFaBuBean.lianXiRenXingMing = str;
                    }
                }, "请输入联系人");
                tongYongShuRuDIalog.show();
            }
        });

        rlXiangxidizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, PoiKeywordSearchActivity.class);
                startActivity(intent);

//                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
//                    @Override
//                    public void cannel() {
//
//                    }
//
//                    @Override
//                    public void submit(String str) {
//                        tvXiangxidizhi.setText(str);
//                        xiangXiDiZhi = str;
//
//                    }
//                }, "详细地址");

            }
        });
        ivNoneSel.setBackgroundResource(R.mipmap.weixuanze);

        rlXuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ivNoneSel.setBackgroundResource(R.mipmap.yixuanze);
                if (xuanZe.equals("0")) {
                    xuanZe = "1";
                    ivNoneSel.setBackgroundResource(R.mipmap.yixuanze);
                } else if (xuanZe.equals("1")) {
                    ivNoneSel.setBackgroundResource(R.mipmap.weixuanze);
                    xuanZe = "0";
                }
            }
        });
        rlBaocunxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xuanZe.equals("0")) {
                    UIHelper.ToastMessage(mContext, "请您先选择阅读再进行保存信息操作");
                } else if (xuanZe.equals("1")) {
                    //UIHelper.ToastMessage(mContext, "");
                    if (StringUtils.isEmpty(bianMinFaBuBean.biaoTi)) {
                        UIHelper.ToastMessage(mContext, "您发布的标题不能为空");
                        return;
                    }
                    if (StringUtils.isEmpty(bianMinFaBuBean.lanMuLeiBieMingCheng)) {
                        UIHelper.ToastMessage(mContext, "请选择发布栏目");
                        return;
                    }
                    if (StringUtils.isEmpty(bianMinFaBuBean.neiRongMiaoShu)) {
                        UIHelper.ToastMessage(mContext, "描述内容不能为空");
                        return;
                    }


                    if (bianMinFaBuBean.proBeanList.size() == 0) {
                        UIHelper.ToastMessage(mContext, "请您上传详情图");
                        return;
                    }
                    if (StringUtils.isEmpty(bianMinFaBuBean.lianXiRenXingMing)) {
                        UIHelper.ToastMessage(mContext, "联系人不能为空");
                        return;
                    }

                    if (StringUtils.isEmpty(bianMinFaBuBean.diZhi)) {
                        UIHelper.ToastMessage(mContext, "请选择您的地址");
                        return;
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
                    tishiDialog.setTextContent("是否确定提交当前信息");

                    tishiDialog.show();

                }
            }
        });

        getJingYingXiang();

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

                    getPaiZhaoPhone();
                    //UIHelper.ToastMessage(mContext, "弹出相册和相机");
                } else {
                    //UIHelper.ToastMessage(mContext, "此时点击要无反应");
                }
            }
        });
        String dizhi = PreferenceHelper.getInstance(mContext).getString(AppConfig.ADDRESS, "");
        bianMinFaBuBean.weiDu = PreferenceHelper.getInstance(mContext).getString(WEIDU, "");
        bianMinFaBuBean.jingDu = PreferenceHelper.getInstance(mContext).getString(JINGDU, "");


        tvXiangxidizhi.setText(dizhi);
        bianMinFaBuBean.diZhi = dizhi;

        tvFabuxuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultX5WebView_HaveNameActivity.actionStart(mContext, Urls.BIANMINXUZHI, "便民发布须知");
            }
        });

    }

    private TakePhoto takePhoto;

    public void getPaiZhaoPhone() {
        String[] items = {"拍照", "相册"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
        dialog.isTitleShow(false).

                show();
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

                            BianMinFaBuBean.ProBean xiangQingTuBean = new BianMinFaBuBean.ProBean();
                            xiangQingTuBean.type = "1";
                            xiangQingTuBean.ir_img_url = response.body().data.get(0).getImg_url();
                            xiangQingTuBean.ir_img_id = response.body().data.get(0).getImg_id();
                            bianMinFaBuBean.proBeanList.add(xiangQingTuBean);

                            list.clear();
                            for (int i = 0; i < bianMinFaBuBean.proBeanList.size(); i++) {
                                list.add(bianMinFaBuBean.proBeanList.get(i));
                            }
                            BianMinFaBuBean.ProBean xiangQingTuBean1 = new BianMinFaBuBean.ProBean();
                            xiangQingTuBean1.type = "0";
                            list.add(xiangQingTuBean1);

                            xiangQingTuAdapter.notifyDataSetChanged();
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

    private TishiDialog tishiDialog;

    public void saveData() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        if (!StringUtils.isEmpty(irId)) {
            map.put("", "");
        }
        map.put("x", bianMinFaBuBean.weiDu);
        map.put("y", bianMinFaBuBean.jingDu);
        map.put("ir_type", "3");

        //便民发布标题
        map.put("ir_title", bianMinFaBuBean.biaoTi);

        //便民发布栏目类型
        map.put("ir_column_type", bianMinFaBuBean.lanMuLeiXing);
        //便民发布栏目类型名称
        map.put("ir_column_type_name", bianMinFaBuBean.lanMuLeiXingMingCheng);
        //便民发布栏目类别
        map.put("ir_column_category", bianMinFaBuBean.lanMuLeiBie);
        //便民发布栏目类别名称
        map.put("ir_column_category_name", bianMinFaBuBean.lanMuLeiBieMingCheng);
        //pro
        map.put("pro", bianMinFaBuBean.proBeanList);
        //信息简介/内容描述
        map.put("ir_validity", bianMinFaBuBean.neiRongMiaoShu);
        //地址
        map.put("addr", bianMinFaBuBean.diZhi);
        //联系人姓名
        map.put("ir_contact_name", bianMinFaBuBean.lianXiRenXingMing);
        //微信号
        map.put("ir_wx_number", bianMinFaBuBean.weiXinHao);

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
                        tishiDialog.setTextContent("已提交成功");
                        tishiDialog.setTextCancel("");
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

    private List<Object> dataBeanList = new ArrayList<>();
    private List<List<Object>> dataBeanList1 = new ArrayList<>();

    public void getJingYingXiang() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17003");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("itemtype_id", "4");
        Gson gson = new Gson();
        OkGo.<AppResponse<JingYingXiangBean.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JingYingXiangBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<JingYingXiangBean.DataBean>> response) {


                        pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                //返回的分别是三个级别的选中位置
//                                UIHelper.ToastMessage(mContext, dataBeanList.get(options1) + "");
//                                UIHelper.ToastMessage(mContext, dataBeanList1.get(options2) + "");

                                Log.i("BianMinFaBuActivity", "dataBeanList的值" + dataBeanList.get(options1) + "");
                                Log.i("BianMinFaBuActivity", "dataBeanList1的值" + dataBeanList1.get(options2) + "");

                                for (int i = 0; i < dataBeanList.size(); i++) {
                                    if (response.body().data.get(i).getItem_name().equals(dataBeanList.get(options1))) {
                                        bianMinFaBuBean.lanMuLeiBie = response.body().data.get(i).getItem_id();
                                        bianMinFaBuBean.lanMuLeiBieMingCheng = response.body().data.get(i).getItem_name();

//                                        for (int j = 0; j < response.body().data.get(i).getItem().size(); j++) {
//
//                                            bianMinFaBuBean.lanMuLeiXing = response.body().data.get(i).getItem().get(j).getItem_id();
//                                            bianMinFaBuBean.lanMuLeiXingMingCheng = response.body().data.get(i).getItem().get(j).getItem_name();
//                                        }

                                     bianMinFaBuBean.lanMuLeiXingMingCheng = (String) dataBeanList1.get(i).get(options2);
                                       // bianMinFaBuBean.lanMuLeiXingMingCheng = (String) dataBeanList.get(i);

                                        for (int j = 0; j < response.body().data.get(i).getItem().size(); j++) {
                                            if ( bianMinFaBuBean.lanMuLeiXingMingCheng.equals(response.body().data.get(i).getItem().get(j).getItem_name())) {

                                                bianMinFaBuBean.lanMuLeiXing = response.body().data.get(i).getItem().get(j).getItem_id();
                                                bianMinFaBuBean.lanMuLeiXingMingCheng=response.body().data.get(i).getItem().get(j).getItem_name();
                                            }
                                        }

                                    }
                                }

                                Log.i("BianMinFaBuActivity", "栏目类别:" + bianMinFaBuBean.lanMuLeiBie + "栏目类别名称:" + bianMinFaBuBean.lanMuLeiBieMingCheng);
                                Log.i("BianMinFaBuActivity", "栏目类型:" + bianMinFaBuBean.lanMuLeiXing + "栏目类型名称:" + bianMinFaBuBean.lanMuLeiXingMingCheng);


                                String str = bianMinFaBuBean.lanMuLeiBieMingCheng + "-" + bianMinFaBuBean.lanMuLeiXingMingCheng;
                                tvQingxuanze.setText(str);
                            }
                        }).build();

                        pvOptions.setTitleText("请选择栏目内容");

                        for (int i = 0; i < response.body().data.size(); i++) {
//                            JingYingXiangBean.DataBean jingYingXiangBean = new JingYingXiangBean.DataBean();
//                            jingYingXiangBean.setItem_name(response.body().data.get(i).getItem_name());
//                            jingYingXiangBean.setItem_id(response.body().data.get(i).getItem_id());
                            List<Object> list = new ArrayList<>();
                            for (int j = 0; j < response.body().data.get(i).getItem().size(); j++) {

                                list.add(response.body().data.get(i).getItem().get(j).getItem_name());


                            }
                            dataBeanList1.add(list);
                            dataBeanList.add(response.body().data.get(i).getItem_name());
                        }


                        pvOptions.setPicker(dataBeanList, dataBeanList1);
                        pvOptions.setSelectOptions(index);
                    }

                    @Override
                    public void onError(Response<AppResponse<JingYingXiangBean.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<JingYingXiangBean.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });


    }

    private String xuanZe = "0";//0为选择 1选择

    private void faBuLanMu() {
        FaBuLanMuDialog faBuDialog = new FaBuLanMuDialog(mContext, new FaBuLanMuDialog.FaBuDialogListener() {
            @Override
            public void shangJiaFaBu() {
                UIHelper.ToastMessage(mContext, "shangjiafabu");
            }

            @Override
            public void gongJiangFaBu() {

                UIHelper.ToastMessage(mContext, "gongjiang");
            }

            @Override
            public void bianMinFabu() {
                //UIHelper.ToastMessage(mContext, "便民");
                BianMinFaBuActivity.actionStart(mContext);
            }
        });
        faBuDialog.show();

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = faBuDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        faBuDialog.getWindow().setAttributes(lp);
    }


    @Override
    public int getContentViewResId() {
        return R.layout.layout_bianmin_fabu;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("便民发布");
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


    String irId = "";

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String irId) {
        Intent intent = new Intent(context, BianMinFaBuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("irid", irId);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BianMinFaBuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
