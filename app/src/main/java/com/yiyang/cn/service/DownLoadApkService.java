package com.yiyang.cn.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;

public class DownLoadApkService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Log.i("downLoadApkService", "服务已经开启");
                DownloadManager dManager = (DownloadManager) getApplication().getSystemService(Context.DOWNLOAD_SERVICE);
                String requestId = intent.getStringExtra("id");
                String downloadPath = intent.getStringExtra("path");
                //查询下载信息
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(Long.parseLong(requestId));
                try {
                    boolean isGoging = true;
                    while (isGoging) {
                        Cursor cursor = dManager.query(query);
                        if (cursor != null && cursor.moveToFirst()) {
                            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                            switch (status) {
                                //如果下载状态为成功
                                case DownloadManager.STATUS_SUCCESSFUL:
                                    isGoging = false;
                                    //调用LocalBroadcastManager.sendBroadcast将intent传递回去
//                            mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
//                            mLocalBroadcastManager.sendBroadcast(localIntent);
                                    Log.i("downLoadApkService", "下载完成");


                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_APK_DOWNLOADSUCCESS;
                                    notice.content = downloadPath;
                                    RxBus.getDefault().sendRx(notice);
                                    break;
                            }
                        }

                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Looper.loop();
            }
        }).start();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "服务已经停止", Toast.LENGTH_LONG).show();
    }
}
