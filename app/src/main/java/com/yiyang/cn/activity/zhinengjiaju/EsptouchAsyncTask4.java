package com.yiyang.cn.activity.zhinengjiaju;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.espressif.iot.esptouch.EsptouchTask;
import com.espressif.iot.esptouch.IEsptouchResult;
import com.espressif.iot.esptouch.IEsptouchTask;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.zhinengjiaju.peinet.ZhiNengJiaJuPeiWangActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.yiyang.cn.app.ConstanceValue.MSG_PEIWNAG_ESPTOUCH;

public class EsptouchAsyncTask4 extends AsyncTask<byte[], IEsptouchResult, List<IEsptouchResult>> {
    private WeakReference<Activity> mActivity;

    private final Object mLock = new Object();
    private ProgressDialog mProgressDialog;
    private AlertDialog mResultDialog;
    private IEsptouchTask mEsptouchTask;

    public IListener iListener;

    public interface IListener {
        void successZhuJi();
    }


    public EsptouchAsyncTask4(Activity activity, IListener iListener) {
        mActivity = new WeakReference<>(activity);
        this.iListener = iListener;
    }

    public void cancelEsptouch() {
        cancel(true);
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mResultDialog != null) {
            mResultDialog.dismiss();
        }
        if (mEsptouchTask != null) {
            mEsptouchTask.interrupt();
        }
    }

    @Override
    protected void onPreExecute() {
        //连接中
        /**
         * @param str 0连接失败 1开始连接页面 2连接中
         */
//        Notice notice = new Notice();
//        notice.type = ConstanceValue.MSG_PEIWNAG_ESPTOUCH;
//        notice.content = 2;
//        RxBus.getDefault().sendRx(notice);

        Activity activity = mActivity.get();
        mProgressDialog = new ProgressDialog(activity);
    }

    @Override
    protected void onProgressUpdate(IEsptouchResult... values) {
        Log.i("ZhiNengJiaJuPeiWang", "onProgressUpdate" + values[0].getBssid() + values[0].getInetAddress());
        Activity context = mActivity.get();
//        if (context != null) {
//           Toast.makeText(context, "配网成功", Toast.LENGTH_SHORT).show();
//            Notice notice = new Notice();
//            notice.type = MSG_PEIWNAG_ESPTOUCH;
//            notice.type = 3;
//            RxBus.getDefault().sendRx(notice);
////            context.finish();
//        }

        iListener.successZhuJi();
//        Notice notice = new Notice();
//        notice.type = MSG_PEIWNAG_ESPTOUCH;
//        RxBus.getDefault().sendRx(notice);


    }

    @Override
    protected List<IEsptouchResult> doInBackground(byte[]... params) {
        Activity activity = mActivity.get();
        int taskResultCount;
        synchronized (mLock) {
            byte[] apSsid = params[0];
            byte[] apBssid = params[1];
            byte[] apPassword = params[2];
            byte[] deviceCountData = params[3];
            byte[] broadcastData = params[4];
            taskResultCount = deviceCountData.length == 0 ? -1 : Integer.parseInt(new String(deviceCountData));
            Context context = activity.getApplicationContext();
            mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword, context);
            mEsptouchTask.setPackageBroadcast(broadcastData[0] == 1);
            mEsptouchTask.setEsptouchListener(this::publishProgress);
        }
        return mEsptouchTask.executeForResults(taskResultCount);
    }

    @Override
    protected void onPostExecute(List<IEsptouchResult> result) {
        Activity activity = mActivity.get();
        Log.i("ZhiNengJiaJuPeiWang", "onProgressUpdate" + result.get(0).getBssid() + result.get(0).getInetAddress());

        mProgressDialog.dismiss();
        if (result == null) {
            mResultDialog = new AlertDialog.Builder(activity)
                    .setMessage(R.string.esptouch1_configure_result_failed_port)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
            mResultDialog.setCanceledOnTouchOutside(false);
            return;
        }

        // check whether the task is cancelled and no results received
        IEsptouchResult firstResult = result.get(0);
        if (firstResult.isCancelled()) {
            return;
        }
        // the task received some results including cancelled while
        // executing before receiving enough results
        //连接失败
        /**
         * @param str 0连接失败 1开始连接页面 2连接中
         */
        if (!firstResult.isSuc()) {

//            Notice notice = new Notice();
//            notice.type = MSG_PEIWNAG_ESPTOUCH;
//            notice.content = 0;
//            RxBus.getDefault().sendRx(notice);

            return;
        }

        ArrayList<CharSequence> resultMsgList = new ArrayList<>(result.size());
        for (IEsptouchResult touchResult : result) {
            String message = activity.getString(R.string.esptouch1_configure_result_success_item,
                    touchResult.getBssid(), touchResult.getInetAddress().getHostAddress());
            resultMsgList.add(message);
        }
        CharSequence[] items = new CharSequence[resultMsgList.size()];

        /**
         * @param str 0连接失败 1开始连接页面 2连接中3连接成功
         */

//        activity.finish();

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}