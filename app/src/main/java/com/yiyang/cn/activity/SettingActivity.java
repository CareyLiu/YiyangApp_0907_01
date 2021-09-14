package com.yiyang.cn.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.zhinengjiaju.peinet.v1.EspTouchActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppEvent;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuo_Base;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.EmptyModel;
import com.yiyang.cn.model.Upload;
import com.yiyang.cn.model.UserInfo;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.CleanMessageUtil;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;
import org.jaaksi.pickerview.picker.TimePicker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

import static com.yiyang.cn.get_net.Urls.MESSAGE_URL;

public class SettingActivity extends BaseActivity implements Observer, TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.layout_header)
    LinearLayout layoutHeader;
    @BindView(R.id.layout_nickname)
    LinearLayout layoutNickname;
    @BindView(R.id.layout_gender)
    LinearLayout layoutGender;
    @BindView(R.id.layout_birthday)
    LinearLayout layoutBirthday;
    @BindView(R.id.layout_address)
    LinearLayout layoutAddress;
    @BindView(R.id.layout_cash_account)
    LinearLayout layoutCashAccount;
    @BindView(R.id.layout_login_password)
    LinearLayout layoutLoginPassword;
    @BindView(R.id.layout_pay_password)
    LinearLayout layoutPayPassword;
    @BindView(R.id.layout_clear_cache)
    LinearLayout layoutClearCache;
    @BindView(R.id.shebei_peiwang)
    LinearLayout shebeiPeiwang;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.ll_shezhi_weixin)
    LinearLayout llShezhiWeixin;
    @BindView(R.id.ll_fort_set)
    LinearLayout ll_fort_set;


    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.tv_zhifubao_ming)
    TextView tvZhifubaoMing;
    @BindView(R.id.tv_weixin_ming)
    TextView tvWeixinMing;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_fort)
    TextView tv_fort;
    @BindView(R.id.tv13)
    TextView tv13;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv12)
    TextView tv12;

    private BaseAnimatorSet mBasIn = new BounceBottomEnter();
    private BaseAnimatorSet mBasOut = new SlideBottomExit();
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private NormalDialog normalDialog;
    private OptionsPickerView pvOptions;
    private TimePicker timePicker;
    private int index = 0;
    private String yongHuMing = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);
        getTakePhoto().onCreate(savedInstanceState);
        requestData();
        AppEvent.getClassEvent().addObserver(this);
        try {
            tvCache.setText(CleanMessageUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        shebeiPeiwang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EspTouchActivity.actionStart(SettingActivity.this);
            }
        });


        String font_settings = PreferenceHelper.getInstance(this).getString(AppConfig.FONT_SETTINGS, "");
        if (TextUtils.isEmpty(font_settings)) {
            tv_fort.setText("标准");
            setFortXiao();
        } else {
            if (font_settings.equals(AppConfig.FONT_DA)) {
                tv_fort.setText("大");
                setFortDa();
            } else {
                tv_fort.setText("标准");
                setFortXiao();
            }
        }
    }

    private void setFortDa() {
        tvGender.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tvBirthday.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tvExit.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tvCache.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tvZhifubaoMing.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tvWeixinMing.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tvNickname.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv_fort.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);

        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv5.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv6.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv7.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv8.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv9.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv10.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv11.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv12.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tv13.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
    }

    private void setFortXiao() {
        tvGender.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tvBirthday.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tvExit.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tvCache.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tvZhifubaoMing.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tvWeixinMing.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tvNickname.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv_fort.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv5.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv6.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv7.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv8.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv9.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv10.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv11.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv12.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv13.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
    }


    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04201");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<UserInfo.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<UserInfo.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<UserInfo.DataBean>> response) {
                        Glide.with(MyApplication.getAppContext()).load(response.body().data.get(0).getUser_img_url()).into(ivHeader);
                        tvNickname.setText(response.body().data.get(0).getUser_name());
                        tvBirthday.setText(response.body().data.get(0).getUser_birthday());

                        if (response.body().data.get(0).getUser_sex().equals("1")) {
                            tvGender.setText("男");
                        } else {
                            tvGender.setText("女");
                        }
                        tvZhifubaoMing.setText(response.body().data.get(0).getAlipay_uname());
                        tvWeixinMing.setText(response.body().data.get(0).getWx_user_name());
                        yongHuMing = response.body().data.get(0).getUser_name();
                    }

                    @Override
                    public void onError(Response<AppResponse<UserInfo.DataBean>> response) {
                        AlertUtil.t(SettingActivity.this, response.getException().getMessage());
                    }
                });
    }


    public void updateInfo(String updateType) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00701");
        map.put("key", Urls.key);
        map.put("of_user_id", UserManager.getManager(this).getUserId());
        map.put("update_type", updateType);
        switch (updateType) {
            case "2":
                map.put("user_sex", index + 1 + "");
                break;
            case "3":
                map.put("user_birthday", tvBirthday.getText().toString());
                break;
        }
        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(final Response<AppResponse> response) {

                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        AlertUtil.t(SettingActivity.this, response.getException().getMessage());
                    }
                });
    }


    @OnClick({R.id.rl_back, R.id.ll_fort_set, R.id.layout_header, R.id.layout_nickname, R.id.layout_gender, R.id.layout_birthday,
            R.id.layout_address, R.id.layout_cash_account, R.id.layout_login_password, R.id.layout_pay_password, R.id.layout_clear_cache,
            R.id.tv_exit, R.id.ll_shezhi_weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_fort_set:
                showFortDialog();
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.layout_header:
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

                break;
            case R.id.layout_nickname:
                startActivity(new Intent(SettingActivity.this, NickActivity.class));
                break;
            case R.id.layout_gender:
                final List<String> item = new ArrayList<>();
                pvOptions = new OptionsPickerBuilder(SettingActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        tvGender.setText(item.get(options1));
                        index = options1;
                        updateInfo("2");
                    }
                }).build();
                item.clear();
                item.add("男");
                item.add("女");
                pvOptions.setTitleText("请选择性别");
                pvOptions.setPicker(item);
                pvOptions.setSelectOptions(index);
                pvOptions.show();
                break;
            case R.id.layout_birthday:
                timePicker = new TimePicker.Builder(this, TimePicker.TYPE_DATE, new TimePicker.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(TimePicker picker, Date date) {
                        tvBirthday.setText(getTime(date));
                        updateInfo("3");
                    }
                }).setSelectedDate(System.currentTimeMillis()).create();
                timePicker.show();
                break;
            case R.id.layout_address:
                startActivity(new Intent(SettingActivity.this, AddressActivity.class));
                break;
            case R.id.layout_cash_account:
                PhoneCheckActivity.actionStart(SettingActivity.this, "0008", "1");
                break;
            case R.id.layout_login_password:
                PhoneCheckActivity.actionStart(SettingActivity.this, "0007");

                break;
            case R.id.layout_pay_password:
                PhoneCheckActivity.actionStart(SettingActivity.this, "0006");
                break;
            case R.id.ll_shezhi_weixin:

                String str = PreferenceHelper.getInstance(SettingActivity.this).getString(App.CUNCHUBIND_WEIXINPAY, "");

                String huashu1 = "";
                String huashu2 = "";
                //1 已经设置 2 未设置
                if (str.equals("1")) {
                    huashu1 = "您已绑定" + tvWeixinMing.getText().toString().trim() + "微信" + "需要解绑吗？";

                } else if (str.equals("2")) {
                    huashu1 = "您还未绑定微信，去绑定吗？";
                }

                MyCarCaoZuoDialog_CaoZuo_Base base = new MyCarCaoZuoDialog_CaoZuo_Base(this, "操作", huashu1, new MyCarCaoZuoDialog_CaoZuo_Base.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {
                        // base.dismiss();

                    }

                    @Override
                    public void clickRight() {
                        if (str.equals("1")) {
                            PhoneCheckActivity.actionStart_WeiBind(SettingActivity.this, "0320", false);
                        } else if (str.equals("2")) {
                            PhoneCheckActivity.actionStart_WeiBind(SettingActivity.this, "0320", true);
                        }
                    }
                });


                if (str.equals("1")) {
                    base.show();
                } else if (str.equals("2")) {
                    base.show();
                }

                break;
            case R.id.layout_clear_cache:
                normalDialog = new NormalDialog(this);
                normalDialog.content("确定清除当前应用的缓存数据吗?").showAnim(mBasIn).dismissAnim(mBasOut).show();
                normalDialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                normalDialog.dismiss();
                            }
                        },
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                CleanMessageUtil.clearAllCache(SettingActivity.this);
                                normalDialog.dismiss();
                                AlertUtil.t(SettingActivity.this, "清除完毕！");
                                try {
                                    tvCache.setText(CleanMessageUtil.getTotalCacheSize(SettingActivity.this));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                break;
            case R.id.tv_exit:
                normalDialog = new NormalDialog(this);
                normalDialog.content("确定要注销当前账户吗?").showAnim(mBasIn).dismissAnim(mBasOut).show();
                normalDialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {

                                normalDialog.dismiss();
                            }
                        },
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                Map<String, String> map = new HashMap<>();
                                map.put("code", "03013");
                                map.put("key", Urls.key);
                                map.put("token", UserManager.getManager(SettingActivity.this).getAppToken());
                                Gson gson = new Gson();

                                OkGo.<AppResponse<EmptyModel>>post(MESSAGE_URL)
                                        .tag(this)//
                                        .upJson(gson.toJson(map))
                                        .execute(new JsonCallback<AppResponse<EmptyModel>>() {
                                            @Override
                                            public void onSuccess(final Response<AppResponse<EmptyModel>> response) {

                                            }

                                            @Override
                                            public void onError(Response<AppResponse<EmptyModel>> response) {
                                                AlertUtil.t(SettingActivity.this, response.getException().getMessage());
                                            }
                                        });

                                UserManager.getManager(SettingActivity.this).removeUser();
                                PreferenceHelper.getInstance(SettingActivity.this).removeKey(AppConfig.SERVERID);
                                PreferenceHelper.getInstance(SettingActivity.this).removeKey(AppConfig.DEVICECCID);
                                normalDialog.dismiss();
                                //发送通知 -- 关闭所有订阅的mqtt
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_UNSUB_MQTT;
                                RxBus.getDefault().sendRx(n);
                                RongIM.getInstance().logout();
                                JPushInterface.deleteAlias(SettingActivity.this, 0);
                                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                            }
                        });
                break;
        }
    }

    private void showFortDialog() {
        String items[] = {"标准", "大"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        tv_fort.setText("标准");
                        PreferenceHelper.getInstance(SettingActivity.this).putString(AppConfig.FONT_SETTINGS, AppConfig.FONT_XIAO);
                        setFortXiao();
                        break;
                    case 1:
                        tv_fort.setText("大");
                        PreferenceHelper.getInstance(SettingActivity.this).putString(AppConfig.FONT_SETTINGS, AppConfig.FONT_DA);
                        setFortDa();
                        break;
                }
                dialog.dismiss();

            }
        });
    }

    @Override
    public void takeSuccess(TResult result) {
        //此处使用原图路径，不压缩
        File file = new File(result.getImage().getOriginalPath());
        OkGo.<AppResponse<Upload.DataBean>>post(Urls.SERVER_URL + "msg/upload")
                .tag(this)//
                .isSpliceUrl(true)
                .params("key", Urls.key)
                .params("token", UserManager.getManager(SettingActivity.this).getAppToken())
                .params("type", "1")
                .params("file", file)
                .execute(new JsonCallback<AppResponse<Upload.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<Upload.DataBean>> response) {
                        Glide.with(SettingActivity.this).load(response.body().data.get(0).getFile_all_url()).into(ivHeader);
                    }

                    @Override
                    public void onError(Response<AppResponse<Upload.DataBean>> response) {
                        AlertUtil.t(SettingActivity.this, response.getException().getMessage());
                    }
                });

    }

    @Override
    public void takeFail(TResult result, String msg) {
        showToast(msg);
    }

    @Override
    public void takeCancel() {
        showToast("取消选择");
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

    @SuppressLint("SimpleDateFormat")
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format;
        format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(date);
        return strDate;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("update")) {
            requestData();
        }
    }
}
