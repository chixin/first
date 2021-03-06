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

public class GyroSensor extends Activity {
	SensorManager sm;
	Sensor gyroscopeSensor;
	TextView tx1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mytext);

		// 准备显示信息的UI组建
		tx1 = (TextView) findViewById(R.id.TextView01);

		// 从系统服务中获得传感器管理器
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		gyroscopeSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

	}

	@Override
	public void onResume() {
		super.onResume();

		sm.registerListener(sensorEventListener, gyroscopeSensor,
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	@Override
	public void onPause() {
		super.onPause();
		sm.unregisterListener(sensorEventListener);
	}

	SensorEventListener sensorEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			tx1.setText(String.valueOf(event.values[0]) + "\n"
					+ String.valueOf(event.values[1]) + "\n"
					+ String.valueOf(event.values[2]));
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
}