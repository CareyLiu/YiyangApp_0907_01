package com.yiyang.cn.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.sourceforge.pinyin4j.format.HanyuPinyinCaseType.UPPERCASE;
import static net.sourceforge.pinyin4j.format.HanyuPinyinToneType.WITHOUT_TONE;


public class Util {
	private static ProgressDialog mProgress = null;
	public static int result = 0;
	private static DisplayMetrics displayMetrics = new DisplayMetrics();
	private static long lastClickTime = 0;//上次点击的时间
	private static int spaceTime = 500;//时间间隔
	private static String timestamp;

	public static boolean isFastClick() {

		long currentTime = System.currentTimeMillis();//当前系统时间

		boolean isAllowClick;//是否允许点击
		if (currentTime - lastClickTime > spaceTime) {
			isAllowClick = false;
		} else {
			isAllowClick = true;
		}
		lastClickTime = currentTime;
		return isAllowClick;
	}


	/**
	 * 收起键盘
	 */
	public static void pickKey(Context context, View view) {
		InputMethodManager imm = (InputMethodManager)
				context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 *
	 * 方法描述：获取系统版本 创建人： yantong<br/>
	 *
	 * **/
	public static final int getSystemVersion() {

		return android.os.Build.VERSION.SDK_INT;
	}


	public static JSONObject stringToJSONObject(String str) throws Exception {
		try {
			return new JSONObject(str);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取手机IMEI号
	 *
	 * 需要动态权限: android.permission.READ_PHONE_STATE
	 */
	public static String getIMEI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return "";
		}
		String imei = telephonyManager.getDeviceId();

		return imei;
	}

	/**
	 * 方法描述：安装一个Apk
	 */
	public static boolean isAppInstall(Context context, String packname) {
		PackageManager manager = context.getPackageManager();
		@SuppressWarnings("rawtypes")
		List pkgList = manager.getInstalledPackages(0);
		for (int i = 0; i < pkgList.size(); i++) {
			PackageInfo pI = (PackageInfo) pkgList.get(i);
			if (pI.packageName.equalsIgnoreCase(packname)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * 方法描述：将资源目录下文件写入本地
	 */
	public static boolean retrieveApkFromAssets(Context context, String fileName, String path) {
		boolean bRet = false;
		try {
			InputStream is = context.getAssets().open(fileName);

			File file = new File(path);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);

			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}

			fos.close();
			is.close();

			bRet = true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bRet;
	}

	/**
	 *
	 * 判断sd卡是否存在
	 */
	public static boolean sdIsExist() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}


	/**
	 * 方法描述：获取电话号码
	 */
	public static String getPhoneNumber(Context context) throws Exception {
		String phoneNumber = "";
		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return "";
			}
			phoneNumber = tm.getLine1Number();
		} catch (Exception e) {
			throw new RuntimeException("android.permission.READ_PHONE_STATE should be add to AndroidManifest.xml!");
		}
		return phoneNumber == null ? "" : phoneNumber;
	}


	public static ProgressDialog showProgress(Context context, CharSequence title, CharSequence message, boolean indeterminate, boolean cancelable) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setIndeterminate(indeterminate);
		dialog.setCancelable(false);
		dialog.setOnCancelListener(new OnCancelListener((Activity) context));
		dialog.show();
		mProgress = dialog;
		return dialog;
	}

	public static void closeProgress() {
		try {
			if (mProgress != null) {
				mProgress.dismiss();
				mProgress = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showDialog(Activity context, String strTitle, String strText, int icon, String text) {
		Builder tDialog = new Builder(context);
		tDialog.setIcon(icon);
		tDialog.setTitle(strTitle);
		tDialog.setMessage(strText);
		tDialog.setPositiveButton(text, null);
		tDialog.show();
	}

	public static void chmod(String permission, String path) {
		try {
			String command = "chmod " + permission + " " + path;
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 方法描述：将一个流转换成字符串
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null)
				sb.append(line);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 方法描述：给文件加时间戳
	 */
	public static void updateFileTime(String dir, String fileName) {
		File file = new File(dir, fileName);
		long newModifiedTime = System.currentTimeMillis();
		file.setLastModified(newModifiedTime);
	}

	/**
	 * 方法描述：获取文件时间戳
	 */
	public static long getFileTime(String dir, String fileName) {
		File file = new File(dir, fileName);
		return file.lastModified();
	}

	static class OnCancelListener implements DialogInterface.OnCancelListener {
		Activity mcontext;

		OnCancelListener(Activity context) {
			this.mcontext = context;
		}

		public void onCancel(DialogInterface dialog) {
			this.mcontext.onKeyDown(4, null);
		}
	}

	/**
	 * 方法描述：检查SD卡是否存在
	 */
	public static boolean checkSDCard() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

	}

	/**
	 * 返回屏幕宽(px)
	 */
	public static int getScreenWidth(Activity activity) {
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.widthPixels;
	}

	/**
	 * 返回屏幕高(px)
	 */
	public static int getScreenHeight(Activity activity) {
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}


	/**
	 * 方法描述：获取手机型号 创建人：wuyanhua<br/>
	 * 创建时间：2013-12-27 下午01:55:45 修改人： 修改时间：2013-3-26 下午01:55:45 修改备注：
	 * 
	 */
	public static String getPhoneModle(Context context) {
		return android.os.Build.MODEL;
	}

	/**
	 * 方法描述：验证手机格式 创建人： yantong<br/>
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186
		 * 电信：133、153、180、189、178（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3/5/7/8，其他位置的可以为0-9
		 */
		String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}
 /**
 * 方法描述：获取当前系统时间
* */
 public static String getTime(Date date,String format) {//可根据需要自行截取数据显示
	 Log.d("getTime()", "choice date millis: " + date.getTime());
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
	 return simpleDateFormat.format(date);
 }

	public static String md5(String string) {
		if (TextUtils.isEmpty(string)) {
			return "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(string.getBytes());
			String result = "";
			for (byte b : bytes) {
				String temp = Integer.toHexString(b & 0xff);
				if (temp.length() == 1) {
					temp = "0" + temp;
				}
				result += temp;
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * 禁止EditText输入特殊字符
	 * @param editText
	 */
	public static void setEditTextInhibitInputSpeChat(EditText editText){

		InputFilter filter=new InputFilter() {
			@Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
				String speChat="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
				Pattern pattern = Pattern.compile(speChat);
				Matcher matcher = pattern.matcher(source.toString());
				if(matcher.find())
					return "";
				else
					return null;
			}
		};
		editText.setLongClickable(false);//同时禁止editText复制粘贴
		editText.setFilters(new InputFilter[]{filter});
	}



	//日期选择Dialog
	public static void showDatePickerDialog(Context context, final TextView textView, String timestamp){
        Calendar ca =  Calendar.getInstance();
		int mYear = ca.get(Calendar.YEAR);
		int mMonth = ca.get(Calendar.MONTH);
		int mDay = ca.get(Calendar.DAY_OF_MONTH);
		/**
		 * 日期选择器对话框监听
		 */
		DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				String days;
				if (monthOfYear + 1 < 10) {
					if (dayOfMonth < 10) {
						days = new StringBuffer().append(year).append("-").append("0").
								append(monthOfYear + 1).append("-").append("0").append(dayOfMonth).toString();
					} else {
						days = new StringBuffer().append(year).append("-").append("0").
								append(monthOfYear + 1).append("-").append(dayOfMonth).toString();
					}

				} else {
					if (dayOfMonth < 10) {
						days = new StringBuffer().append(year).append("-").
								append(monthOfYear + 1).append("-").append("0").append(dayOfMonth).toString();
					} else {
						days = new StringBuffer().append(year).append("-").
								append(monthOfYear + 1).append("-").append(dayOfMonth).toString();
					}

				}
				textView.setText(days);
			}
		};
		DatePickerDialog dpd = new DatePickerDialog(context, onDateSetListener, mYear, mMonth, mDay);
		DatePicker dp = dpd.getDatePicker();
		if (timestamp!=null)
			dp.setMinDate(Long.parseLong(timestamp) * 1000);
		else
			dp.setMinDate(System.currentTimeMillis()-1000);
        dpd.show();
//	    String formats = "yyyy-MM-dd HH:mm:ss";
//		Long timestamp1 = Long.parseLong(getTimeStamp(context)) * 1000;
//		String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp1));
//		Log.d("yant",date);

	}

	//隐藏弹出键盘
	public static void hintKb(Activity context) {
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm.isActive()&&context.getCurrentFocus()!=null){
			if (context.getCurrentFocus().getWindowToken()!=null) {
				imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}
	//截屏
	public static void screen(Activity context){
		View dView = context.getWindow().getDecorView();
		dView.setDrawingCacheEnabled(true);
		dView.buildDrawingCache();
		Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
		if (bitmap != null) {
			try {
				// 获取内置SD卡路径
				String sdCardPath = Environment.getExternalStorageDirectory().getPath();
				// 图片文件路径
				String filePath = sdCardPath + File.separator + "screenshot.png";
				File file = new File(filePath);
				FileOutputStream os = new FileOutputStream(file);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
				os.flush();
				os.close();
				AppToast.makeShortToast(context,"存储完成");
			} catch (Exception e) {

			}
		}
	}


	public static String getLocalVersion(Context ctx) {
		int localVersion = 0;
		String localName = "";
		try {
			PackageInfo packageInfo = ctx.getApplicationContext()
					.getPackageManager()
					.getPackageInfo(ctx.getPackageName(), 0);
			localVersion = packageInfo.versionCode;
			localName = packageInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return localName;
	}

	//NumberFormat
	public static String big(double d) {
		NumberFormat nf = NumberFormat.getInstance();
		// 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
		nf.setGroupingUsed(false);
		// 结果未做任何处理
		return nf.format(d);
	}

	//BigDecimal
	public static String big2(double d) {
		BigDecimal d1 = new BigDecimal(Double.toString(d));
		BigDecimal d2 = new BigDecimal(Integer.toString(1));
		// 四舍五入,保留2位小数
		return d1.divide(d2,2, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 如果字符串的首字符为汉字，则返回该汉字的拼音大写首字母
	 * 如果字符串的首字符为字母，也转化为大写字母返回
	 * 其他情况均返回'#'
	 *
	 * @param str 字符串
	 * @return 首字母
	 */
	public static char getHeadChar(String str) {
		if (str != null && str.trim().length() != 0) {
			char[] strChar = str.toCharArray();
			char headChar = strChar[0];
			//如果是大写字母则直接返回
			if (Character.isUpperCase(headChar)) {
				return headChar;
			} else if (Character.isLowerCase(headChar)) {
				return Character.toUpperCase(headChar);
			}
			// 汉语拼音格式输出类
			HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
			hanYuPinOutputFormat.setCaseType(UPPERCASE);
			hanYuPinOutputFormat.setToneType(WITHOUT_TONE);
			if (String.valueOf(headChar).matches("[\\u4E00-\\u9FA5]+")) {
				try {
					String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(headChar, hanYuPinOutputFormat);
					if (stringArray != null && stringArray[0] != null) {
						return stringArray[0].charAt(0);
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					return '#';
				}
			}
		}
		return '#';
	}



}
