package com.my.data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.my.f.R;

public class TestSqlite extends Activity {
	EditText name, url, decription;
	Button saveb, showhis;
	DBHelper dbh;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.testsqlite);
		name = (EditText) findViewById(R.id.nameedit);
		url = (EditText) findViewById(R.id.urledit);
		decription = (EditText) findViewById(R.id.decriptionedit);
		saveb = (Button) findViewById(R.id.saveb);
		showhis = (Button) findViewById(R.id.showhis);
		dbh = new DBHelper(this);
		saveb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if ((name.getText().toString() == null || name.getText()
						.toString().trim().length() <= 0)
						|| (url.getText().toString() == null || url.getText()
								.toString().trim().length() <= 0)) {
					Toast.makeText(getApplicationContext(), "名称或url不能为空",
							Toast.LENGTH_SHORT).show();
					return;

				}
				ContentValues values = new ContentValues();
				values.put("name", name.getText().toString());
				values.put("url", url.getText().toString());
				values.put("desc", decription.getText().toString());
				dbh.insert(values);

				Toast.makeText(getApplicationContext(), "收藏成功",
						Toast.LENGTH_SHORT).show();
				// Intent intent = new Intent("com.my.data.Result");
				// startActivity(intent);
			}
		});
		showhis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.my.data.Result");
				startActivity(intent);
			}
		});

	}
}
