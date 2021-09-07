package com.yiyang.cn.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tongcheng58.adapter.ShangpinBannerAdapter;
import com.yiyang.cn.activity.tongcheng58.model.TcUpLoadModel;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.util.phoneview.sample.ImageShowActivity;

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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShangpinBannerActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {


    @BindView(R.id.iv_main)
    ImageView iv_main;
    @BindView(R.id.iv_delete)
    ImageView iv_delete;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.ll_no_picture)
    RelativeLayout ll_no_picture;

    private List<TcUpLoadModel.DataBean> imgText_list = new ArrayList<>();
    private ShangpinBannerAdapter adapter;

    private int position;
    private TakePhoto takePhoto;


    @Override
    public int getContentViewResId() {
        return R.layout.tongcheng_act_banner;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("轮播图");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onActivityFinish();
            }
        });
    }


    public static void actionStart(Context context, List<TcUpLoadModel.DataBean> imgText_list) {
        Intent intent = new Intent(context, ShangpinBannerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("imgText_list", (Serializable) imgText_list);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        getTakePhoto().onCreate(savedInstanceState);
        init();
    }

    private void init() {
        imgText_list = (List<TcUpLoadModel.DataBean>) getIntent().getSerializableExtra("imgText_list");
        if (imgText_list.size() > 1) {
            iv_main.setVisibility(View.VISIBLE);
            iv_delete.setVisibility(View.VISIBLE);
            ll_no_picture.setVisibility(View.GONE);
            Glide.with(mContext).load(imgText_list.get(0).getImg_url()).into(iv_main);
            position = 0;
        } else {
            ll_no_picture.setVisibility(View.VISIBLE);
            iv_delete.setVisibility(View.GONE);
            iv_main.setVisibility(View.GONE);
        }

        initAdapter();
    }

    private void initAdapter() {
        adapter = new ShangpinBannerAdapter(R.layout.tongcheng_item_shangpin_addimg, imgText_list);
        rv_content.setAdapter(adapter);
        rv_content.setLayoutManager(new GridLayoutManager(mContext, 4));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (imgText_list != null && imgText_list.size() > position) {
                    switch (view.getId()) {
                        case R.id.iv_main:
                            showPicMain(position);
                            break;
                    }
                }
            }
        });
    }


    private void showPicMain(int position) {
        if (position == imgText_list.size() - 1) {
            getPicture();
        } else {
            this.position = position;
            iv_main.setVisibility(View.VISIBLE);
            iv_delete.setVisibility(View.VISIBLE);
            ll_no_picture.setVisibility(View.GONE);
            String img_url = imgText_list.get(position).getImg_url();
            Glide.with(mContext).load(img_url).into(iv_main);
        }
    }


    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(ShangpinBannerActivity.this, this));
        }
        return takePhoto;
    }

    private void getPicture() {
        String[] items = {"拍照", "相册"};
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, items, null);
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
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        return type;
    }

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
                            ll_no_picture.setVisibility(View.GONE);
                            iv_main.setVisibility(View.VISIBLE);
                            iv_delete.setVisibility(View.VISIBLE);
                            Glide.with(mContext).load(file.getPath()).into(iv_main);
                            TcUpLoadModel.DataBean dataBean = response.body().data.get(0);
                            position = imgText_list.size() - 1;
                            imgText_list.add(position, dataBean);
                            adapter.notifyDataSetChanged();
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

                    @Override
                    public void onStart(Request<AppResponse<TcUpLoadModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }
                });
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
    public void onBackPressedSupport() {
        onActivityFinish();
    }


    @OnClick({R.id.iv_main, R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main:
                showPic();
                break;
            case R.id.iv_delete:
                showDeleteDialog(position);
                break;
        }
    }

    private void showPic() {
        if (imgText_list.size() > 1) {
            ArrayList<String> imgs = new ArrayList<>();
            for (int i = 0; i < imgText_list.size() - 1; i++) {
                imgs.add(imgText_list.get(i).getImg_url());
            }
            ImageShowActivity.actionStart(mContext, imgs, position);
        }
    }


    private void showDeleteDialog(int pos) {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_DELETE, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                deletePicture(pos);
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTitle("提示");
        dialog.setTextContent("你确定要删除当前图片么");
        dialog.show();
    }

    private void deletePicture(int pos) {
        position = 0;
        imgText_list.remove(pos);
        if (imgText_list.size() <= 1) {
            iv_main.setVisibility(View.GONE);
            iv_delete.setVisibility(View.GONE);
            ll_no_picture.setVisibility(View.VISIBLE);
        } else {
            iv_main.setVisibility(View.VISIBLE);
            iv_delete.setVisibility(View.VISIBLE);
            ll_no_picture.setVisibility(View.GONE);
            Glide.with(mContext).load(imgText_list.get(0).getImg_url()).into(iv_main);
        }
        adapter.notifyDataSetChanged();
    }

    private void onActivityFinish() {
        Notice n = new Notice();
        n.type = ConstanceValue.MSG_ADD_BANNER;
        n.content = imgText_list;
        RxBus.getDefault().sendRx(n);
        finish();
    }
}
