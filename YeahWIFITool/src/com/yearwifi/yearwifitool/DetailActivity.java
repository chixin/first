package com.yearwifi.yearwifitool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.yearwifi.yeahwifitool.R;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemlist);
		ImageView imageView = (ImageView) findViewById(R.id.itemlist);
		imageView.setBackgroundResource(R.drawable.detail);
		Log.v("detailList activity id", this.toString());
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

	}
}
