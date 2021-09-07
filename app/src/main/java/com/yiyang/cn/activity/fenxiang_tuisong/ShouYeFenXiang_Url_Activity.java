package com.yiyang.cn.activity.fenxiang_tuisong;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.model.ShareModel;
import com.yiyang.cn.util.AlertUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

import static com.yiyang.cn.activity.fenxiang_tuisong.FenXiangTuiSongActivity.IMAGE_SIZE;
import static com.yiyang.cn.config.Wetch_S.APP_ID;


public class ShouYeFenXiang_Url_Activity extends Activity {


    @BindView(R.id.cl_1)
    ConstraintLayout cl1;
    @BindView(R.id.iv_pengyouquan)
    ImageView ivPengyouquan;
    @BindView(R.id.cl_pengyouquan)
    ConstraintLayout clPengyouquan;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.cl_weixin)
    ConstraintLayout clWeixin;
    @BindView(R.id.iv_fuzhi)
    ImageView ivFuzhi;
    @BindView(R.id.cl_fuzhi)
    ConstraintLayout clFuzhi;
    @BindView(R.id.cl_zhognjian)
    ConstraintLayout clZhognjian;
    @BindView(R.id.view_x)
    View viewX;
    @BindView(R.id.rl_quxiao)
    RelativeLayout rlQuxiao;
    @BindView(R.id.rl_layout)
    RelativeLayout rlLayout;
    private ImmersionBar mImmersionBar;
    private IWXAPI api;


    String share_title, share_detail, share_url, share_img;

    /**
     * 用于其他activity跳转到该activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShouYeFenXiang_Url_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);//创建一个实例
        api.registerApp(APP_ID);//注册实例
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shouye_fenxiang);
        ButterKnife.bind(this);
        //       mImmersionBar = ImmersionBar.with(this);
        AppManager.getAppManager().addActivity(this);


        Intent i = getIntent();
        if (i == null) {
            return;
        }

        String content = "内容";

        rlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        clWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWeatchShare(share_title, share_detail, share_url, share_img, "0");
            }
        });
        clPengyouquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWeatchShare(share_title, share_detail, share_url, share_img, "1");
            }
        });
        clFuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", content);
// 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);

                UIHelper.ToastMessage(ShouYeFenXiang_Url_Activity.this, "复制成功");

            }
        });
        rlQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getShareNet();
    }

    private void initImmersionBar() {
        mImmersionBar.transparentBar()
                .statusBarDarkFont(true)
                .init();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        Log.d("c", "onActivityResult");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        if (mImmersionBar != null) {
//            mImmersionBar.destroy();
//        }
    }


    /**
     * 注册事件通知
     */
    public Observable<Notice> toObservable() {
        return RxBus.getDefault().toObservable(Notice.class);
    }

    /**
     * 发送消息
     */
    public void sendRx(Notice msg) {
        RxBus.getDefault().sendRx(msg);
    }

    /**
     * @param title
     * @param content
     * @param url
     * @param imageUrl
     * @param leixing  0 好友 1朋友圈
     */
    private void setWeatchShare(String title, String content, String url, String imageUrl, String leixing) {
        new Thread(new Runnable() {//创建一个子线程
            @Override
            public void run() {
//初始化一个WXWebpageObject，填写url
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = url;

//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = title;
                msg.description = content;
                //   Bitmap thumbBmp = BitmapFactory.decodeResource(getResources(), R.drawable.send_music_thumb);

                ImageView iv = new ImageView(ShouYeFenXiang_Url_Activity.this);

                Glide.with(ShouYeFenXiang_Url_Activity.this).asBitmap().load(imageUrl).into(new BitmapImageViewTarget(iv) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable Transition<? super Bitmap> transition) {

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();


                    }
                });


                msg.thumbData = getThumbData();
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;

                if (leixing.equals("0")) {
                    req.scene = SendMessageToWX.Req.WXSceneSession;//分享到微信好友
                } else if (leixing.equals("1")) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到微信朋友圈
                }
                // req.scene = SendMessageToWX.Req.WXSceneSession;//分享到微信好友
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到微信朋友圈


//调用api接口，发送数据到微信
                api.sendReq(req);

            }
        }).start();
    }

    /**
     * 获取分享封面byte数组 我们这边取的是软件启动icon
     *
     * @return
     */
    private byte[] getThumbData() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        int quality = 100;
        while (output.toByteArray().length > IMAGE_SIZE && quality != 10) {
            output.reset(); // 清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, output);// 这里压缩options%，把压缩后的数据存放到baos中
            quality -= 10;
        }
        bitmap.recycle();
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private void getShareNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04261");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(ShouYeFenXiang_Url_Activity.this).getAppToken());

        Gson gson = new Gson();
        OkGo.<AppResponse<ShareModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShareModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ShareModel.DataBean>> response) {
                        //finish();
                        share_title = response.body().data.get(0).getShare_title();
                        share_detail = response.body().data.get(0).getShare_detail();
                        share_url = response.body().data.get(0).getShare_url();
                        share_img = response.body().data.get(0).getShare_img();
                    }

                    @Override
                    public void onError(Response<AppResponse<ShareModel.DataBean>> response) {
                        AlertUtil.t(ShouYeFenXiang_Url_Activity.this, response.getException().getMessage());
                    }
                });
    }
}

