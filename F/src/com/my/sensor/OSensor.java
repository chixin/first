package com.my.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.my.f.R;

public class OSensor extends Activity {
	SensorManager sm;
	TextView tx1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mytext);

		// ׼����ʾ��Ϣ��UI�齨
		// tx1 = (TextView) findViewById(R.id.TextView01);
		//
		// // ��ϵͳ�����л�ô�����������
		// sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		//
		// float[] event = sm.getOrientation(null, null);
		// tx1.setText(String.valueOf(event[0]) + "\n" +
		// String.valueOf(event[1])
		// + "\n" + String.valueOf(event[2]));

	}
}