package com.yiyang.cn.activity.a_yiyang;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.LoginActivity;
import com.yiyang.cn.activity.NickActivity;
import com.yiyang.cn.activity.PhoneCheckActivity;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.EmptyModel;
import com.yiyang.cn.model.Upload;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

import static com.yiyang.cn.get_net.Urls.MESSAGE_URL;

public class YySetAactivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.rl_head)
    RelativeLayout rl_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rl_name)
    RelativeLayout rl_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.rl_sex)
    RelativeLayout rl_sex;
    @BindView(R.id.rl_pwd)
    RelativeLayout rl_pwd;
    @BindView(R.id.tv_renzheng)
    TextView tv_renzheng;
    @BindView(R.id.rl_renzheng)
    RelativeLayout rl_renzheng;
    @BindView(R.id.bt_login_out)
    TextView bt_login_out;


    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private OptionsPickerView pvOptions;

    private int index;
    private NormalDialog normalDialog;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_set;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, YySetAactivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设置");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        getTakePhoto().onCreate(savedInstanceState);
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


    @OnClick({R.id.rl_head, R.id.rl_name, R.id.rl_sex, R.id.rl_pwd, R.id.rl_renzheng, R.id.bt_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_head:
                clickHead();
                break;
            case R.id.rl_name:
                startActivity(new Intent(mContext, NickActivity.class));
                break;
            case R.id.rl_sex:
                clickSex();
                break;
            case R.id.rl_pwd:
                PhoneCheckActivity.actionStart(mContext, "0007");
                break;
            case R.id.rl_renzheng:
                break;
            case R.id.bt_login_out:
                loginOut();
                break;
        }
    }

    private BaseAnimatorSet mBasIn = new BounceBottomEnter();
    private BaseAnimatorSet mBasOut = new SlideBottomExit();

    private void loginOut() {
        normalDialog = new NormalDialog(this);
        normalDialog.content("确定要退出登录么?").showAnim(mBasIn).dismissAnim(mBasOut).show();
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
                        map.put("token", UserManager.getManager(mContext).getAppToken());
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
                                        AlertUtil.t(mContext, response.getException().getMessage());
                                    }
                                });

                        UserManager.getManager(mContext).removeUser();
                        PreferenceHelper.getInstance(mContext).removeKey(AppConfig.SERVERID);
                        PreferenceHelper.getInstance(mContext).removeKey(AppConfig.DEVICECCID);
                        normalDialog.dismiss();
                        //发送通知 -- 关闭所有订阅的mqtt
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_UNSUB_MQTT;
                        RxBus.getDefault().sendRx(n);
                        RongIM.getInstance().logout();
                        JPushInterface.deleteAlias(mContext, 0);
                        startActivity(new Intent(mContext, LoginActivity.class));
                    }
                });
    }

    private void clickSex() {
        final List<String> item = new ArrayList<>();
        pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tv_sex.setText(item.get(options1));
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
    }

    private void clickHead() {
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

    @Override
    public void takeSuccess(TResult result) {
        //此处使用原图路径，不压缩
        File file = new File(result.getImage().getOriginalPath());
        OkGo.<AppResponse<Upload.DataBean>>post(Urls.SERVER_URL + "msg/upload")
                .tag(this)//
                .isSpliceUrl(true)
                .params("key", Urls.key)
                .params("token", UserManager.getManager(mContext).getAppToken())
                .params("type", "1")
                .params("file", file)
                .execute(new JsonCallback<AppResponse<Upload.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<Upload.DataBean>> response) {
                        Glide.with(mContext).load(response.body().data.get(0).getFile_all_url()).into(iv_head);
                    }

                    @Override
                    public void onError(Response<AppResponse<Upload.DataBean>> response) {
                        AlertUtil.t(mContext, response.getException().getMessage());
                    }
                });

    }

    @Override
    public void takeFail(TResult result, String msg) {
        Y.t(msg);
    }

    @Override
    public void takeCancel() {
        Y.t("取消选择");
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
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
                        AlertUtil.t(mContext, response.getException().getMessage());
                    }
                });
    }
}
