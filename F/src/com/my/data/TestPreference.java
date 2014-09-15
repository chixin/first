package com.my.data;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.my.f.R;

public class TestPreference extends Activity {
	EditText sms;
	Button send;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.testdataprocess);
		sms = (EditText) findViewById(R.id.sms);
		send = (Button) findViewById(R.id.send_sms);
		SharedPreferences pre = getSharedPreferences("temp_sms", MODE_PRIVATE);
		String content = pre.getString("sms_content", "");
		sms.setText(content);

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.v("", "onStop...");
				finish();

			}
		});

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		SharedPreferences.Editor preEdit = getSharedPreferences("temp_sms",
				MODE_PRIVATE).edit();

		preEdit.putString("sms_content", sms.getText().toString());
		preEdit.commit();
	}

}
