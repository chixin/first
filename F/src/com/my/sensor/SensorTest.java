package com.my.sensor;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.f.R;

public class SensorTest extends Activity {

	/** Called when the activity is first created. */
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mytext);
		LinearLayout layout = (LinearLayout) findViewById(R.id.mytest);
		Button b1 = new Button(this);
		Button b2 = new Button(this);
		Button b3 = new Button(this);

		// addContentView(b1, new LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));

		b1.setText("加速度");
		b2.setText("陀螺仪");
		b3.setText("方向");
		layout.addView(b1);
		layout.addView(b2);
		layout.addView(b3);

		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SensorTest.this, GSensor.class));

			}
		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SensorTest.this, GyroSensor.class));

			}
		});

		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SensorTest.this, OSensor.class));

			}
		});

		// 准备显示信息的UI组建
		final TextView tx1 = (TextView) findViewById(R.id.TextView01);

		// 从系统服务中获得传感器管理器
		SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		// 从传感器管理器中获得全部的传感器列表
		// List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);
		List<Sensor> allSensors = new ArrayList<Sensor>();
		allSensors.add(sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
		allSensors.add(sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
		allSensors.add(sm.getDefaultSensor(Sensor.TYPE_ORIENTATION));
		// allSensors.add(SensorManager.getOrientation(null, null));
		//
		// // 显示有多少个传感器
		// tx1.setText("经检测该手机有" + allSensors.size() + "个传感器，他们分别是：\n");
		//
		// // 显示每个传感器的具体信息

		for (Sensor s : allSensors) {
			//
			String tempString = "\n" + "  设备名称：" + s.getName() + "\n"
					+ "  设备版本：" + s.getVersion() + "\n" + "  供应商："
					+ s.getVendor() + "\n";
			//
			switch (s.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				tx1.setText(tx1.getText().toString() + s.getType()
						+ " 加速度传感器accelerometer" + tempString);
				break;
			case Sensor.TYPE_GYROSCOPE:
				tx1.setText(tx1.getText().toString() + s.getType()
						+ " 陀螺仪传感器gyroscope" + tempString);
				break;
			case Sensor.TYPE_ORIENTATION:
				tx1.setText(tx1.getText().toString() + s.getType()
						+ " 方向传感器orientation" + tempString);
				break;
			// case Sensor.TYPE_LIGHT:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " 环境光线传感器light" + tempString);
			// break;
			// case Sensor.TYPE_MAGNETIC_FIELD:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " 电磁场传感器magnetic field" + tempString);
			// break;

			// case Sensor.TYPE_PRESSURE:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " 压力传感器pressure" + tempString);
			// break;
			// case Sensor.TYPE_PROXIMITY:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " 距离传感器proximity" + tempString);
			// break;
			// case Sensor.TYPE_TEMPERATURE:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " 温度传感器temperature" + tempString);
			// break;
			// case Sensor.TYPE_ROTATION_VECTOR:
			// tx1.setText(tx1.getText().toString() + s.getType() + " 旋转向量传感器"
			// + tempString);
			// break;
			//
			// case Sensor.TYPE_LINEAR_ACCELERATION:
			// tx1.setText(tx1.getText().toString() + s.getType() + " 线性加速计"
			// + tempString);
			// break;
			// case Sensor.TYPE_GRAVITY:
			// tx1.setText(tx1.getText().toString() + s.getType() + " 重力感应器"
			// + tempString);
			// break;
			default:
				tx1.setText(tx1.getText().toString() + s.getType() + " 未知传感器"
						+ tempString);
				break;
			}
		}

	}
}