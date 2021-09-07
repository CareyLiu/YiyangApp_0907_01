package com.yiyang.cn.activity.fenxiang_tuisong;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.util.BitMapUrls;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import io.rong.message.utils.BitmapUtil;


import static com.yiyang.cn.config.Wetch_S.APP_ID;

public class FenXiangTuiSongActivity extends BaseActivity {

    @BindView(R.id.btn_weixin_share)
    Button btnWeixinShare;
    public static final int IMAGE_SIZE = 32768;//微信分享图片大小限制
    String title;
    String image;
    String url;
    String content;
    @BindView(R.id.btn_weixin_pengyouquan)
    Button btnWeixinPengyouquan;

    private static final int THUMB_SIZE = 150; //设置分享到朋友圈的缩略图宽高大小

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);//创建一个实例
        api.registerApp(APP_ID);//注册实例
        btnWeixinShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "微信分享");
                new Thread(new Runnable() {//创建一个子线程
                    @Override
                    public void run() {
                        try {
                            //远程获取的缩略图的图片地址
                            String url = "http://weixin.qq.com/zh_CN/htmledition/images/weixin/weixin_logo20f761.png";
                            URL imageUrl = new URL(url);
                            InputStream is = imageUrl.openStream();


                            WXWebpageObject webpage = new WXWebpageObject();
                            webpage.webpageUrl = "http://www.baidu.com";//分享出去的网页地址
                            WXMediaMessage msg = new WXMediaMessage(webpage);
                            msg.title = "标题";//分享的标题
                            msg.description = "描述";//分享的描述信息


                            msg.thumbData = getThumbData(url);
                            SendMessageToWX.Req req = new SendMessageToWX.Req();
                            req.transaction = String.valueOf(System.currentTimeMillis());
                            req.message = msg;

                            req.scene = SendMessageToWX.Req.WXSceneSession;//分享到微信好友
                            //   req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到微信朋友圈
                            api.sendReq(req);
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });

        btnWeixinPengyouquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {//创建一个子线程
                    @Override
                    public void run() {
//初始化一个WXWebpageObject，填写url
                        WXWebpageObject webpage = new WXWebpageObject();
                        webpage.webpageUrl = "http://weixin.qq.com/zh_CN/htmledition/images/weixin/weixin_logo20f761.png";

//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
                        WXMediaMessage msg = new WXMediaMessage(webpage);
                        msg.title = "网页标题----- ";
                        msg.description = "网页描述----";
                        //   Bitmap thumbBmp = BitmapFactory.decodeResource(getResources(), R.drawable.send_music_thumb);

                        ImageView iv = new ImageView(mContext);

                        Glide.with(mContext).asBitmap().load(url).into(new BitmapImageViewTarget(iv) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource,
                                                        @Nullable Transition<? super Bitmap> transition) {

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                resource.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                byte[] data = baos.toByteArray();
                                msg.thumbData = data;

                            }
                        });


//构造一个Req
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = msg;
                        // req.scene = SendMessageToWX.Req.WXSceneSession;//分享到微信好友
                        req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到微信朋友圈


//调用api接口，发送数据到微信
                        api.sendReq(req);

                    }
                }).start();


            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, FenXiangTuiSongActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_fen_xiang_tui_song;
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


    public static byte[] getBytesFromInputStream(InputStream is) throws IOException {

        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // 用数据装
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            outstream.write(buffer, 0, len);
        }
        outstream.close();   // 关闭流一定要记得。

        return outstream.toByteArray();
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


    /**
     * 获取分享封面byte数组 我们这边取的是软件启动icon
     *
     * @return
     */
    private byte[] getThumbData(String url) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        List<String> urlStringList = new ArrayList<>();
        urlStringList.add(url);
        Bitmap bitmap = getUrlBitMap(url);


        //  Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
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


    private Bitmap getUrlBitMap(String url) {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(url).submit(360, 480).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;

        return bitmap;
    }


}
