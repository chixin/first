package com.my.memo;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.my.f.R;

public class MemoAdd extends Activity {
	private EditText title;
	private EditText content;
	private Button add;

	private String titlevalue;
	private String contentvalue;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memoadd);
		title = (EditText) findViewById(R.id.memotitle);
		content = (EditText) findViewById(R.id.memocontent);
		add = (Button) findViewById(R.id.memoadd);
		add.setClickable(false);
		content.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

				titlevalue = title.getText().toString();
				contentvalue = content.getText().toString();
				Log.v("", contentvalue);
				if (contentvalue != null && contentvalue.trim().length() != 0) {
					add.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							ContentValues contentValue = new ContentValues();
							contentValue.put("title", titlevalue);
							contentValue.put("content", contentvalue);
							getContentResolver()
									.insert(Uri
											.parse("content://com.my.memo.tasklist"),
											contentValue);
							Toast.makeText(getApplicationContext(), "添加备忘成功!",
									Toast.LENGTH_SHORT).show();
						}
					});
				} else {
					add.setClickable(false);
					Log.v("", "内容为空");
				}

			}
		});
	}
}
