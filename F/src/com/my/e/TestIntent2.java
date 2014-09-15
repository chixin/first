package com.my.e;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.my.f.R;

public class TestIntent2 extends Activity {
	TextView text;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testintent2);

		Button b = (Button) findViewById(R.id.button2);
		text = (TextView) findViewById(R.id.name2);
		Intent i = getIntent();
		Bundle bundle = i.getExtras();

		String name = bundle.getString("name");
		text.setText(name);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = getIntent();
				Bundle bundle = i.getExtras();
				bundle.putString("name", "lin");
				i.putExtras(bundle);
				TestIntent2.this.setResult(1, i);
				finish();

			}
		});

	}
}
