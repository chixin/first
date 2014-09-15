package com.yearwifi.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;

import com.yearwifi.yeahwifitool.R;

public class Util {
	static SharedPreferences pre;
	static WifiManager wifiManager;
	static WifiInfo wifiInfo;
	static String phoneInfo = "";
	static ConnectivityManager mConnectivityManager;
	static int notifyTimes;

	public static String UnicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	public static String getPhonteInfo(Context context) {
		phoneInfo = "Product: " + android.os.Build.PRODUCT;
		phoneInfo += "\nMODEL: " + android.os.Build.MODEL;
		phoneInfo += "\nAndroid sdk Version: "
				+ android.os.Build.VERSION.RELEASE;
		phoneInfo += "\nAndroid sdk Int: " + android.os.Build.VERSION.SDK_INT;
		phoneInfo += "\nDEVICE: " + android.os.Build.DEVICE;
		phoneInfo += "\nBRAND: " + android.os.Build.BRAND;
		phoneInfo += "\nBOARD: " + android.os.Build.BOARD;
		phoneInfo += "\n应用名称: "
				+ context.getResources().getString(R.string.app_name);

		Log.v("getPhonteInfo", phoneInfo);
		return phoneInfo;
	}

	public static String getWIFIInfo(Context context) {

		wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		wifiInfo = wifiManager.getConnectionInfo();
		StringBuffer address = new StringBuffer();
		address.append("本机MAC：" + wifiInfo.getMacAddress() + "\n");
		address.append("本机IP：" + intToIp(wifiInfo.getIpAddress()) + "\n");
		address.append("WIFI ssid：" + wifiInfo.getSSID() + "\n");
		address.append("路由器MAC：" + wifiInfo.getBSSID() + "\n");
		Log.v("wifi info", address.toString());
		return address.toString();
	}

	public static String getWIFIMac(Context context) {

		wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		wifiInfo = wifiManager.getConnectionInfo();
		return wifiInfo.getMacAddress();
	}

	public static boolean isNetworkConnected(Context context) {
		mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
		return networkInfo.isAvailable();

	}

	public static String intToIp(int i) {
		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + ((i >> 24) & 0xFF);
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void sendNotification(Context context) {
		notifyTimes++;
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		String pushTitle = "团购优惠信息" + getFormatedTime(context);
		String pushContent = "[8店通用] 上海星美国际影城仅售39.9元，价值120元2D/3D电影票一张，无需预约，1.2米(不含)以下儿童可免费观看。";
		Notification n = new Notification();
		Intent intent = new Intent();
		intent.setAction("com.yearwifi.yearwifitool.detail");
		PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

		if (android.os.Build.VERSION.SDK_INT >= 16) {
			Notification.Builder builder = new Notification.Builder(context);
			builder.setContentTitle(pushTitle).setContentText(pushContent)
					.setContentIntent(pi).setWhen(System.currentTimeMillis())
					.setTicker(pushTitle).setSmallIcon(R.drawable.push)
					.setLights(0xff00ff00, 300, 1000).setAutoCancel(true);
			n = builder.build();
			n.defaults |= Notification.DEFAULT_SOUND;
			n.flags |= Notification.FLAG_SHOW_LIGHTS;
		} else {
			n.flags |= Notification.FLAG_AUTO_CANCEL;
			n.icon = R.drawable.push;
			n.tickerText = pushTitle;
			n.when = System.currentTimeMillis();
			n.setLatestEventInfo(context, pushTitle, pushContent, pi);
			n.defaults = Notification.DEFAULT_SOUND;

		}
		nm.notify(1, n);
		wakeLock(context);
	}

	public static void wakeLock(Context context) {
		final PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		final PowerManager.WakeLock wakeLock = pm.newWakeLock(
				PowerManager.SCREEN_DIM_WAKE_LOCK
						| PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");

		Handler mTimeHandler = new Handler();
		wakeLock.acquire();

		mTimeHandler.postDelayed(new Runnable() {
			public void run() {
				wakeLock.release();
			}
		}, 5 * 1000);
	}

	public static String getCache(Context context, String key) {
		pre = context.getSharedPreferences("temp_sms", Context.MODE_PRIVATE);
		String content = pre.getString(key, "");
		return content;
	}

	public static void putCache() {
	}

	public static String getFormatedTime(Context context) {

		java.text.DateFormat formatter = DateFormat.getTimeInstance();
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		return str;
	}
}
