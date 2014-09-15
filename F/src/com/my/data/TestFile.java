package com.my.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.my.f.R;

public class TestFile extends Activity {
	EditText sms, sms2;
	Button sendb, readb;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.testdataprocess);
		sms = (EditText) findViewById(R.id.sms);
		sendb = (Button) findViewById(R.id.send_sms);
		sms2 = (EditText) findViewById(R.id.sms2);
		readb = (Button) findViewById(R.id.read);

		sendb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				write(sms.getText().toString());
			}

		});

		readb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sms2.setText(read());

			}

		});

	}

	private String read() {
		FileInputStream fi;
		try {
			fi = openFileInput("myfile");
			byte[] buff = new byte[fi.available()];
			fi.read(buff);
			fi.close();
			return new String(buff);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private void write(String content) {
		FileOutputStream fo;
		try {
			fo = openFileOutput("myfile", MODE_WORLD_WRITEABLE);
			fo.write(content.getBytes());
			fo.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
