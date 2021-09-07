package com.yiyang.cn.get_net;

import android.util.Log;

import com.google.gson.Gson;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MFangService {
    public static MFangAPI createMFangService() {
        OkHttpClient client = null;
        try {
            client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request newReq = request.newBuilder()
                            .build();
                    Log.e("MFangService", new Gson().toJson(newReq));
                    return chain.proceed(newReq);
                }
            }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        builder.client(client);
        return builder.build().create(MFangAPI.class);
    }
}
