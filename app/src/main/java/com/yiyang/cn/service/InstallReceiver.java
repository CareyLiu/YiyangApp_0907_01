package com.yiyang.cn.service;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.readystatesoftware.chuck.internal.ui.MainActivity;

import java.io.File;

public class InstallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        String data = intent.getStringExtra(DownloadService.EXTENDED_DATA_STATUS);
//        Log.i("test", data);
//
//        long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//        intent = new Intent(Intent.ACTION_VIEW);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/myApp.apk")),
//                "application/vnd.android.package-archive");
//        startActivity(intent);

    }
}
