package com.yiyang.cn.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.yiyang.cn.R;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.RxUtils;

import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.util.DialogManager;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;


public abstract class BaseActivity extends AppCompatActivity {

	/** Application基类对象 **/
	protected MyApplication ac;
	protected Toast toast;
	protected CompositeSubscription _subscriptions = new CompositeSubscription();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		ac = (MyApplication) getApplication();
		StatusBarUtil.setTransparent(this);
		_subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
		getdata();
		getView();
	}

	protected void getView() {

	}

	protected  void getdata(){

	}
	public void showDialog(String msg){
		DialogManager.getManager(this).showMessage(msg);
	}
	public void dismissDialog(){
		DialogManager.getManager(this).dismiss();
	}

	protected void showToast(String msg) {
//		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		if (toast != null)
	    {
	        toast.setText(msg);
	        toast.setDuration(Toast.LENGTH_SHORT);
	        toast.show();
	    } else
	    {
	        toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
	        toast.show();
	    }
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}



	/** 带有右进右出动画的退出 **/
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	@Override
	protected void onDestroy() {
		AppManager.getAppManager().finishActivity(this);
		View currentFocus = getCurrentFocus();
		if (currentFocus != null) {
			currentFocus.clearFocus();
		}
		if(!_subscriptions.isUnsubscribed()) {
			_subscriptions.unsubscribe();
		}
		super.onDestroy();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	public void onConfigurationChanged(Configuration newConfig) {
		try {
			super.onConfigurationChanged(newConfig);
			if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				Log.v("yant", "onConfigurationChanged_ORIENTATION_LANDSCAPE");
			} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
				Log.v("yant", "onConfigurationChanged_ORIENTATION_PORTRAIT");
			}
		} catch (Exception ex) {
			Log.d("exception",ex.getMessage());
		}
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

	
}
