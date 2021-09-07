package com.yiyang.cn.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.yiyang.cn.R;
import com.yiyang.cn.util.zxing.BarcodeFormat;
import com.yiyang.cn.util.zxing.EncodeHintType;
import com.yiyang.cn.util.zxing.common.BitMatrix;
import com.yiyang.cn.util.zxing.qrcode.QRCodeWriter;
import com.yiyang.cn.util.zxing.qrcode.decoder.ErrorCorrectionLevel;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class Tools {

    /**
     * 生成二维码Bitmap
     *
     * @param context 上下文
     * @param data    文本内容
     * @param logoBm  二维码中心的Logo图标（可以为null）
     * @return 合成后的bitmap
     */
    public static Bitmap createQRImage(Context context, String data, Bitmap logoBm) {

        try {

            if (data == null || "".equals(data)) {
                return null;
            }

            int widthPix = ((Activity) context).getWindowManager().getDefaultDisplay()
                    .getWidth();
            widthPix = widthPix / 5 * 4;
            int heightPix = widthPix;

            //配置参数
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
            hints.put(EncodeHintType.MARGIN, 0); //default is 4

            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
            int[] pixels = new int[widthPix * heightPix];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }

            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);

            if (logoBm != null) {
                bitmap = addLogo(bitmap, logoBm);
            }

            return bitmap;
            //必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，内存消耗巨大！
            //return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }

    /**
     * @param urlpath
     * @return Bitmap
     * 根据图片url获取图片对象
     */
    public static Bitmap getBitMBitmap(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    public static Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }


    public static void fitPopupWindowOverStatusBar(PopupWindow mPopupWindow, boolean needFullScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field mLayoutInScreen = PopupWindow.class.getDeclaredField("mLayoutInScreen");
                mLayoutInScreen.setAccessible(needFullScreen);
                mLayoutInScreen.set(mPopupWindow, needFullScreen);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param cx       上下文
     * @param pcLayout 布局 控件
     * @param tvPrice  钱 控件
     * @param tvJia    加号 控件
     * @param tvCredit 积分 控件
     * @param price    钱 值
     * @param credit   积分 值
     */
    public static void pcLayout(Context cx, LinearLayout pcLayout, TextView tvPrice, TextView tvJia, TextView tvCredit, String price, String credit) {
        ForegroundColorSpan fcs1 = new ForegroundColorSpan(cx.getResources().getColor(R.color.color_cd013d));
        ForegroundColorSpan fcs2 = new ForegroundColorSpan(cx.getResources().getColor(R.color.black_000000));
        AbsoluteSizeSpan ass1 = new AbsoluteSizeSpan(DensityUtils.sp2px(cx, 17));
        AbsoluteSizeSpan ass2 = new AbsoluteSizeSpan(DensityUtils.sp2px(cx, 16));
        AbsoluteSizeSpan ass3 = new AbsoluteSizeSpan(DensityUtils.sp2px(cx, 8));

        if (TextUtils.isEmpty(price) && TextUtils.isEmpty(credit)) {
            // 钱或积分，都为空 不展示布局
            pcLayout.setVisibility(View.GONE);
        } else {
            SpannableStringBuilder ssbPrice;
            SpannableStringBuilder ssbCredit = new SpannableStringBuilder(credit + "积分");
            BigDecimal bdPrice = new BigDecimal(price);
            if (bdPrice.compareTo(BigDecimal.ZERO) == 0) {
                ssbPrice = new SpannableStringBuilder(BigDecimal.ZERO + "元");
            } else {
                ssbPrice = new SpannableStringBuilder(price + "元");
            }
            if (!TextUtils.isEmpty(price) && !TextUtils.isEmpty(credit)) {
                //钱，积分都不为空
                //钱字段展示
                ssbPrice.setSpan(fcs1, 0, ssbPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbPrice.setSpan(ass1, 0, ssbPrice.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbPrice.setSpan(ass2, ssbPrice.length() - 1, ssbPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //积分字段展示
                ssbCredit.setSpan(fcs1, 0, ssbCredit.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbCredit.setSpan(ass1, 0, ssbCredit.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbCredit.setSpan(fcs2, ssbCredit.length() - 2, ssbCredit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbCredit.setSpan(ass3, ssbCredit.length() - 2, ssbCredit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                if ("0".equals(credit)) {
                    tvPrice.setText(ssbPrice);
                    tvJia.setVisibility(View.GONE);
                    tvCredit.setVisibility(View.GONE);
                } else {
                    tvPrice.setText(ssbPrice);
                    tvJia.setVisibility(View.VISIBLE);
                    tvCredit.setText(ssbCredit);
                }
            } else {
                if (!TextUtils.isEmpty(price)) {
                    //钱 不为空
                    ssbPrice.setSpan(fcs1, 0, ssbPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbPrice.setSpan(ass1, 0, ssbPrice.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbPrice.setSpan(ass2, ssbPrice.length() - 1, ssbPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //隐藏 加号
                    tvJia.setVisibility(View.GONE);
                    tvPrice.setText(ssbPrice);
                } else if (!TextUtils.isEmpty(credit)) {
                    //积分 不为空
                    ssbCredit.setSpan(fcs1, 0, ssbCredit.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbCredit.setSpan(ass1, 0, ssbCredit.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbCredit.setSpan(fcs2, ssbCredit.length() - 2, ssbCredit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbCredit.setSpan(ass3, ssbCredit.length() - 2, ssbCredit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvJia.setVisibility(View.GONE);
                    tvCredit.setText(ssbCredit);
                }
            }
        }
    }

    public static void pcLayoutWhite(Context cx, LinearLayout pcLayout, TextView tvPrice, TextView tvJia, TextView tvCredit, String price, String credit) {
        ForegroundColorSpan fcs1 = new ForegroundColorSpan(cx.getResources().getColor(R.color.white));
        ForegroundColorSpan fcs2 = new ForegroundColorSpan(cx.getResources().getColor(R.color.white));
        AbsoluteSizeSpan ass1 = new AbsoluteSizeSpan(DensityUtils.sp2px(cx, 12));
        AbsoluteSizeSpan ass2 = new AbsoluteSizeSpan(DensityUtils.sp2px(cx, 12));
        AbsoluteSizeSpan ass3 = new AbsoluteSizeSpan(DensityUtils.sp2px(cx, 12));

        if (TextUtils.isEmpty(price) || TextUtils.isEmpty(credit)) {
            // 钱或积分，都为空 不展示布局
            pcLayout.setVisibility(View.GONE);
        } else {
            SpannableStringBuilder ssbPrice;
            SpannableStringBuilder ssbCredit = new SpannableStringBuilder(credit + "积分");
            BigDecimal bdPrice = new BigDecimal(price);
            if (bdPrice.compareTo(BigDecimal.ZERO) == 0) {
                ssbPrice = new SpannableStringBuilder(BigDecimal.ZERO + "元");
            } else {
                ssbPrice = new SpannableStringBuilder(price + "元");
            }
            if (!TextUtils.isEmpty(price) && !TextUtils.isEmpty(credit)) {
                //钱，积分都不为空
                //钱字段展示
                ssbPrice.setSpan(fcs1, 0, ssbPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbPrice.setSpan(ass1, 0, ssbPrice.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbPrice.setSpan(ass2, ssbPrice.length() - 1, ssbPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //积分字段展示
                ssbCredit.setSpan(fcs1, 0, ssbCredit.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbCredit.setSpan(ass1, 0, ssbCredit.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbCredit.setSpan(fcs2, ssbCredit.length() - 2, ssbCredit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssbCredit.setSpan(ass3, ssbCredit.length() - 2, ssbCredit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                if ("0".equals(credit)) {
                    tvPrice.setText(ssbPrice);
                    tvJia.setVisibility(View.GONE);
                    tvCredit.setVisibility(View.GONE);
                } else {
                    tvPrice.setText(ssbPrice);
                    tvJia.setVisibility(View.VISIBLE);
                    tvCredit.setText(ssbCredit);
                }
            } else {
                if (!TextUtils.isEmpty(price)) {
                    //钱 不为空
                    ssbPrice.setSpan(fcs1, 0, ssbPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbPrice.setSpan(ass1, 0, ssbPrice.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbPrice.setSpan(ass2, ssbPrice.length() - 1, ssbPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //隐藏 加号
                    tvJia.setVisibility(View.GONE);
                    tvPrice.setText(ssbPrice);
                } else if (!TextUtils.isEmpty(credit)) {
                    //积分 不为空
                    ssbCredit.setSpan(fcs1, 0, ssbCredit.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbCredit.setSpan(ass1, 0, ssbCredit.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbCredit.setSpan(fcs2, ssbCredit.length() - 2, ssbCredit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbCredit.setSpan(ass3, ssbCredit.length() - 2, ssbCredit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvJia.setVisibility(View.GONE);
                    tvCredit.setText(ssbCredit);
                }
            }
        }
    }

    /*
     * 图片转化成String
     */
    public static String converIconToString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] b = stream.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    /*
     * String 转化成图片
     */

    public static Bitmap converStringToIcon(String s) {
        try {
            Bitmap bitmap = null;
            byte[] b = Base64.decode(s, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 判断Activity是否Destroy
     * @param activity
     * @return
     */
    public static boolean isDestroy(Activity mActivity) {
        if (mActivity== null || mActivity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mActivity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }



}
