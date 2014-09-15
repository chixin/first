package com.my.e;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my.f.R;

public class TestIntent extends Activity {
	EditText edit;
	TextView text;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testintent1);
		text = (TextView) findViewById(R.id.name3);
		Button b = (Button) findViewById(R.id.button1);

		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				edit = (EditText) findViewById(R.id.name1);
				Bundle bundle = new Bundle();
				bundle.putString("name", edit.getText().toString());
				Intent intent = new Intent(TestIntent.this, TestIntent2.class);
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle b = data.getExtras();
		String name = b.getString("name");
		text.setText(name);

	}
}
