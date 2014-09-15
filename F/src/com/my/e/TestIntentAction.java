package com.my.e;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.my.f.R;

public class TestIntentAction extends Activity {
	TextView text;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testintent2);

		Button b = (Button) findViewById(R.id.button2);
		text = (TextView) findViewById(R.id.name2);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("geo:33.23,50.23"));
				// intent.setType("vnd.android.cursor.item/phone");
				startActivity(intent);

			}
		});

	}
}
