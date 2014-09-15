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

		b1.setText("���ٶ�");
		b2.setText("������");
		b3.setText("����");
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

		// ׼����ʾ��Ϣ��UI�齨
		final TextView tx1 = (TextView) findViewById(R.id.TextView01);

		// ��ϵͳ�����л�ô�����������
		SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		// �Ӵ������������л��ȫ���Ĵ������б�
		// List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);
		List<Sensor> allSensors = new ArrayList<Sensor>();
		allSensors.add(sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
		allSensors.add(sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
		allSensors.add(sm.getDefaultSensor(Sensor.TYPE_ORIENTATION));
		// allSensors.add(SensorManager.getOrientation(null, null));
		//
		// // ��ʾ�ж��ٸ�������
		// tx1.setText("�������ֻ���" + allSensors.size() + "�������������Ƿֱ��ǣ�\n");
		//
		// // ��ʾÿ���������ľ�����Ϣ

		for (Sensor s : allSensors) {
			//
			String tempString = "\n" + "  �豸���ƣ�" + s.getName() + "\n"
					+ "  �豸�汾��" + s.getVersion() + "\n" + "  ��Ӧ�̣�"
					+ s.getVendor() + "\n";
			//
			switch (s.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				tx1.setText(tx1.getText().toString() + s.getType()
						+ " ���ٶȴ�����accelerometer" + tempString);
				break;
			case Sensor.TYPE_GYROSCOPE:
				tx1.setText(tx1.getText().toString() + s.getType()
						+ " �����Ǵ�����gyroscope" + tempString);
				break;
			case Sensor.TYPE_ORIENTATION:
				tx1.setText(tx1.getText().toString() + s.getType()
						+ " ���򴫸���orientation" + tempString);
				break;
			// case Sensor.TYPE_LIGHT:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " �������ߴ�����light" + tempString);
			// break;
			// case Sensor.TYPE_MAGNETIC_FIELD:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " ��ų�������magnetic field" + tempString);
			// break;

			// case Sensor.TYPE_PRESSURE:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " ѹ��������pressure" + tempString);
			// break;
			// case Sensor.TYPE_PROXIMITY:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " ���봫����proximity" + tempString);
			// break;
			// case Sensor.TYPE_TEMPERATURE:
			// tx1.setText(tx1.getText().toString() + s.getType()
			// + " �¶ȴ�����temperature" + tempString);
			// break;
			// case Sensor.TYPE_ROTATION_VECTOR:
			// tx1.setText(tx1.getText().toString() + s.getType() + " ��ת����������"
			// + tempString);
			// break;
			//
			// case Sensor.TYPE_LINEAR_ACCELERATION:
			// tx1.setText(tx1.getText().toString() + s.getType() + " ���Լ��ټ�"
			// + tempString);
			// break;
			// case Sensor.TYPE_GRAVITY:
			// tx1.setText(tx1.getText().toString() + s.getType() + " ������Ӧ��"
			// + tempString);
			// break;
			default:
				tx1.setText(tx1.getText().toString() + s.getType() + " δ֪������"
						+ tempString);
				break;
			}
		}

	}
}