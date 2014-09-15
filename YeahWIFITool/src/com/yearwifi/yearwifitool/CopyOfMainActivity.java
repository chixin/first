package com.yearwifi.yearwifitool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
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

public class CopyOfMainActivity extends Activity {

	RelativeLayout food;
	RelativeLayout movie;
	RelativeLayout entertainment;
	RelativeLayout service;
	RelativeLayout hotel;
	RelativeLayout travel;
	RelativeLayout commodity;
	RelativeLayout all;

	ItemClickListener itemClickListener;

	boolean hasShowed = false;
	Info result;
	RequestQueue rq;

	// NotificationManager nm;
	// String pushTitle = "�Ź��Ż���Ϣ";
	// String pushContent =
	// "[8��ͨ��] �Ϻ���������Ӱ�ǽ���39.9Ԫ����ֵ120Ԫ2D/3D��ӰƱһ�ţ�����ԤԼ��1.2��(����)���¶�ͯ����ѹۿ���";

	class Info {
		@SerializedName("app")
		String appName;
		String partner;
		String postion;
		String status;
	}

	Gson gson = new Gson();
	int ThreadNum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		food = (RelativeLayout) findViewById(R.id.item_cg_food);
		movie = (RelativeLayout) findViewById(R.id.item_cg_movie);
		entertainment = (RelativeLayout) findViewById(R.id.item_cg_entertainment);
		service = (RelativeLayout) findViewById(R.id.item_cg_life_service);
		hotel = (RelativeLayout) findViewById(R.id.item_cg_hotel);
		travel = (RelativeLayout) findViewById(R.id.item_cg_travel);
		commodity = (RelativeLayout) findViewById(R.id.item_cg_commodity);
		all = (RelativeLayout) findViewById(R.id.item_cg_all);

		itemClickListener = new ItemClickListener();

		food.setOnClickListener(itemClickListener);
		movie.setOnClickListener(itemClickListener);
		entertainment.setOnClickListener(itemClickListener);
		service.setOnClickListener(itemClickListener);
		hotel.setOnClickListener(itemClickListener);
		travel.setOnClickListener(itemClickListener);
		commodity.setOnClickListener(itemClickListener);
		all.setOnClickListener(itemClickListener);

		rq = Volley.newRequestQueue(getApplicationContext());
		// nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// View scrollView = (View) findViewById(R.id.scrolllist);
		// scrollView.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// // new Thread(report).start();
		// Log.v("onclick", "onclick");
		//
		// }
		// });
	}

	@Override
	public void onResume() {
		super.onResume();

		if (Util.isNetworkConnected(this)) {
			new Thread(getMessage).start();
			ThreadNum++;
		} else {
			Toast.makeText(this, "���粻����", Toast.LENGTH_LONG).show();
		}

	}

	// private void getInfo() {
	//
	// }

	Runnable getMessage = new Runnable() {

		@Override
		public void run() {

			while (true) {
				if (ThreadNum > 1) {
					ThreadNum--;
					return;
				}
				sendRequest();

				try {
					Thread.sleep(30000);
					Log.v("sleep", ThreadNum + "");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	};

	private void sendRequest() {
		Log.v("ThreadNum", ThreadNum + "");
		String url;
		try {
			url = "http://112.124.33.125/appInClient/GetUserInfo?sclientmac="
					+ Util.getWIFIMac(this);
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
							CopyOfMainActivity.this.runOnUiThread(notify);
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.d("onErrorResponse", "", error);
						}
					});

			rq.add(jr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Runnable notify = new Runnable() {

		@SuppressWarnings("deprecation")
		@SuppressLint("NewApi")
		@Override
		public void run() {

			if (!result.status.equals("1")) {
				Log.v("�ӿڷ���״̬", "status=" + result.status + "�ӿڷ��ش�����û����ڷ�Χ��");
				hasShowed = false;
				return;
			}

			// Log.v("hasShowed", hasShowed + "");
			// if (hasShowed)
			// return;

			Util.sendNotification(CopyOfMainActivity.this);
			// hasShowed = true;
		}

	};

	class ItemClickListener implements OnClickListener {
		Intent intent = new Intent();

		@Override
		public void onClick(View v) {
			intent.setClass(CopyOfMainActivity.this, ItemListActivity.class);
			switch (v.getId()) {
			case R.id.item_cg_food:
				intent.putExtra("category", v.getId());
				break;
			case R.id.item_cg_movie:
				intent.putExtra("category", v.getId());
				break;
			case R.id.item_cg_entertainment:
				intent.putExtra("category", v.getId());
				break;
			case R.id.item_cg_life_service:
				intent.putExtra("category", v.getId());
				break;
			case R.id.item_cg_hotel:
				intent.putExtra("category", v.getId());
				break;
			case R.id.item_cg_travel:
				intent.putExtra("category", v.getId());
				break;
			case R.id.item_cg_commodity:
				intent.putExtra("category", v.getId());
				break;
			case R.id.item_cg_all:
				intent.putExtra("category", v.getId());
				break;
			default:
				break;
			}
			startActivity(intent);
		}
	}
}
