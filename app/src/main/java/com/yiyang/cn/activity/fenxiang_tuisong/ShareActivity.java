package com.yiyang.cn.activity.fenxiang_tuisong;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.model.Home;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

import static com.yiyang.cn.activity.fenxiang_tuisong.FenXiangTuiSongActivity.IMAGE_SIZE;
import static com.yiyang.cn.config.Wetch_S.APP_ID;


public class ShareActivity extends Activity {


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


    private IWXAPI api;
    Home.DataBean.activity activity;
    private String share_title;
    private String share_detail;
    private String share_url;
    private String share_img;

    /**
     * 用于其他activity跳转到该activity
     *
     * @param context
     */
    public static void actionStart(Context context, String share_title, String share_detail, String share_url, String share_img) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("share_title", share_title);
        intent.putExtra("share_detail", share_detail);
        intent.putExtra("share_url", share_url);
        intent.putExtra("share_img", share_img);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
        setContentView(R.layout.activity_shouye_fenxiang);
        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);//创建一个实例
        api.registerApp(APP_ID);//注册实例
        AppManager.getAppManager().addActivity(this);

        share_title = getIntent().getStringExtra("share_title");
        share_detail = getIntent().getStringExtra("share_detail");
        share_url = getIntent().getStringExtra("share_url");
        share_img = getIntent().getStringExtra("share_img");

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
                ClipData mClipData = ClipData.newPlainText("Label", share_url);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                UIHelper.ToastMessage(ShareActivity.this, "复制成功");

            }
        });
        rlQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = url;
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = title;
                msg.description = content;
                byte[] thumbData = getThumbData(imageUrl);
                msg.thumbData = getThumbData(imageUrl);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;

                if (leixing.equals("0")) {
                    req.scene = SendMessageToWX.Req.WXSceneSession;//分享到微信好友
                } else if (leixing.equals("1")) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到微信朋友圈
                }

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
    private byte[] getThumbData(String share_url) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = returnBitMap(share_url);
        bitmap = createBitmapThumbnail(bitmap);
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

    public static Bitmap returnBitMap(final String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }

    }

    //压缩图片
    public Bitmap createBitmapThumbnail(Bitmap bitMap) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = 99;
        int newHeight = 99;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
        return newBitMap;
    }
}

