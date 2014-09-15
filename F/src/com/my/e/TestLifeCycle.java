package com.my.e;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.my.f.R;

public class TestLifeCycle extends Activity {
	private String TAG = "lifecycle";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lifecycle);
		Log.v(TAG, "onCreate----------------------------");
		Button b = (Button) findViewById(R.id.lifecycle);

		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "click");
				Button b = (Button) findViewById(R.id.lifecycle);
				b.setText("a");
				finish();
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.v(TAG, "onStart----------------------------");

	}

	@Override
	public void onRestart() {
		super.onRestart();
		Log.v(TAG, "onRestart----------------------------");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.v(TAG, "onResume----------------------------");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.v(TAG, "onPause----------------------------");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.v(TAG, "onStop----------------------------");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy----------------------------");
	}
}
