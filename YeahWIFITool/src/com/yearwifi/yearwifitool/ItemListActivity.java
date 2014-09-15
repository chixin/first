package com.yearwifi.yearwifitool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.yearwifi.yeahwifitool.R;

public class ItemListActivity extends Activity {

	int category;
	ImageView itemlist;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemlist);
		itemlist = (ImageView) findViewById(R.id.itemlist);
		category = getIntent().getIntExtra("category", 0);
		Log.v("itemList activity id", this.toString());
		switch (category) {
		case R.id.item_cg_food:
			itemlist.setImageResource(R.drawable.foodpage);
			break;
		case R.id.item_cg_movie:
			itemlist.setImageResource(R.drawable.moviepage);
			break;
		case R.id.item_cg_entertainment:
			itemlist.setImageResource(R.drawable.entertainmentpage);
			break;
		case R.id.item_cg_life_service:
			itemlist.setImageResource(R.drawable.servicepage);
			break;
		case R.id.item_cg_hotel:
			itemlist.setImageResource(R.drawable.hotelpage);
			break;
		case R.id.item_cg_travel:
			itemlist.setImageResource(R.drawable.travelpage);
			break;
		case R.id.item_cg_commodity:
			itemlist.setImageResource(R.drawable.commoditypage);
			break;
		case R.id.item_cg_all:
			itemlist.setImageResource(R.drawable.allpage);
			break;
		default:
			break;

		}
	}


}
