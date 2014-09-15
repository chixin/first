package com.yearwifi.yearwifitool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.yearwifi.util.Util;
import com.yearwifi.yeahwifitool.R;

public class PushService extends Service {
	int ThreadNum = 0;
	Info result;
	Gson gson = new Gson();
	RequestQueue rq;
	boolean hasShowed = false;
	boolean serviceDestoryed = false;
	int sleepTime = 10000;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		rq = Volley.newRequestQueue(getApplicationContext());
		if (Util.isNetworkConnected(this)) {
			new Thread(getMessage).start();
			ThreadNum++;
			Log.v("ThreadNum++", ThreadNum + "");
		} else {
			Toast.makeText(this, "网络不可用", Toast.LENGTH_LONG).show();
		}

		return super.onStartCommand(intent, flags, startId);
	}

	Runnable getMessage = new Runnable() {

		@Override
		public void run() {

			while (true) {
				if (ThreadNum > 1 || serviceDestoryed) {
					ThreadNum--;
					Log.v("thread return", Thread.currentThread().getId() + "");
					return;
				}
				Log.v("sendRequest", Thread.currentThread().getId() + "");
				sendRequest();

				try {
					Log.v("thread sleep", Thread.currentThread().getId() + "");
					Thread.sleep(sleepTime);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	};

	private void sendRequest() {
		String url;
		String mac = Util.getWIFIMac(this);
		String appName;

		try {
			appName = URLEncoder.encode(PushService.this.getResources()
					.getString(R.string.app_name), "UTF-8");
			url = "http://112.124.33.125/appInClient/GetUserInfo?sclientmac="
					+ mac + "&sAppName=" + appName;
			Log.v("url", url);
			StringRequest jr = new StringRequest(url,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							Log.v("response", Util.UnicodeToString(response));

							try {
								result = gson.fromJson(
										Util.UnicodeToString(response),
										Info.class);
							} catch (JsonSyntaxException e) {
								result = new Info();
								result.status = "0";
								e.printStackTrace();
							}
							Handler handler = new Handler();

							handler.post(notify);
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Toast.makeText(PushService.this, "接口调用错误",
									Toast.LENGTH_SHORT).show();
							Log.d("onErrorResponse", "接口调用错误");
							Log.d("onErrorResponse", "", error);
						}
					});

			rq.add(jr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Runnable notify = new Runnable() {

		@SuppressLint("NewApi")
		@Override
		public void run() {

			if (!result.status.equals("1")) {
				Log.v("接口返回状态", "status=" + result.status + "接口返回错误或用户不在范围内");
				hasShowed = false;
				return;
			}

			// Log.v("hasShowed", hasShowed + "");
			// if (hasShowed)
			// return;

			Util.sendNotification(PushService.this);
			// hasShowed = true;
		}

	};

	class Info {
		@SerializedName("app")
		String appName;
		String partner;
		String postion;
		String status;
		String time;
	}

	@Override
	public void onDestroy() {
		Log.v("onDestroy", "onDestroy");
		serviceDestoryed = true;
		super.onDestroy();
	}

}
