package com.yearwifi.yearwifitool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.yearwifi.yeahwifitool.R;

public class MainActivity extends Activity {

	RelativeLayout food;
	RelativeLayout movie;
	RelativeLayout entertainment;
	RelativeLayout service;
	RelativeLayout hotel;
	RelativeLayout travel;
	RelativeLayout commodity;
	RelativeLayout all;

	ItemClickListener itemClickListener;
	Intent pushIntent;

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
		pushIntent = new Intent("com.yearwifi.yearwifitool.push");
		startService(pushIntent);
		Log.v("startService", "startService");
		Log.v("main activity id", this.toString());

	}

	class ItemClickListener implements OnClickListener {
		Intent intent = new Intent();

		@Override
		public void onClick(View v) {
			intent.setClass(MainActivity.this, ItemListActivity.class);
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
				startService(pushIntent);
				break;
			case R.id.item_cg_all:
				intent.putExtra("category", v.getId());
				stopService(pushIntent);
				break;
			default:
				break;
			}
			startActivity(intent);
		}
	}
}
