package com.yiyang.cn.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

/**
 * Created by Administrator on 17-11-12.
 */
public class BitMapUrls {

    // 饿汉式
    private static BitMapUrls instance = new BitMapUrls();

    private BitMapUrls(){}

    public static BitMapUrls getInstance(){
        return instance;
    }

    /*
     *    get image from network
     *    @param [String]imageURL
     *    @return [BitMap]image
     */
    public Bitmap returnBitMap(String url){
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
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


    /**
     * 获取网络图片
     *
     * @param imageurl 图片网络地址
     * @return Bitmap 返回位图
     */
    public Vector<Bitmap> GetImageInputStream(List<String> imageurl) {
        Vector<Bitmap> bitmaps = new Vector<>();

                URL url = null;
                HttpURLConnection connection = null;
                Bitmap bitmap = null;
                for (int i = 0; i < imageurl.size(); i++) {
                    try {
                        url = new URL(imageurl.get(i));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setConnectTimeout(6000); //超时设置
                        connection.setDoInput(true);
                        connection.setUseCaches(false); //设置不使用缓存
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();
                        if (inputStream==null){
                            throw  new RuntimeException("stream is null");
                        }else {
                            try{
                                byte[] data=readStream(inputStream);
                                if (data!=null){
                                    bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            inputStream.close();
                            bitmaps.add(bitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        System.out.println("----------------------------"+bitmaps.size());
        return bitmaps;
    }

    /*
     * 得到图片字节流 数组大小
     * */
    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }


}