package com.my.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.my.f.R;
import com.my.service.MyLocalService.LocalBinder;

public class TestServices extends Activity {
	TextView name;
	TextView age;
	Button button, button1;
	String Tag = "testService";
	private IPerson iPerson;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testservice);
		Log.v(Tag, "onCreate...");
		name = (TextView) findViewById(R.id.service_name);
		age = (TextView) findViewById(R.id.service_age);
		button = (Button) findViewById(R.id.service_get);
		button1 = (Button) findViewById(R.id.service_getagain);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.v(Tag, "onClick...");
				Log.v(Tag, "activity pid" + android.os.Process.myPid());
				Log.v(Tag, "activity tid" + Thread.currentThread().getId());

				Intent intent = new Intent();
				intent.setAction("com.my.service.MyLocalService");
				// startService(intent);
				startService(intent);
				Log.v(Tag, String.valueOf(localBinder == null));

				// unbindService(conn);
				// try {
				// Thread.sleep(3000);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// unbindService(conn);

			}
		});

	}

	LocalBinder localBinder;
	ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v(Tag, "onServiceDisconnected...");
			Toast.makeText(getApplicationContext(), "断开连接", Toast.LENGTH_LONG)
					.show();
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v(Tag, "onServiceConnected...");
			Log.v(Tag, "ServiceConnection pid" + android.os.Process.myPid());
			Log.v(Tag, "ServiceConnection tid" + Thread.currentThread().getId());
			Toast.makeText(getApplicationContext(), "连接成功", Toast.LENGTH_LONG)
					.show();

			// 本地服务
			localBinder = (LocalBinder) service;
			localBinder.setName("lin");
			localBinder.setAge("2");
			TestServices.this.name.setText(localBinder.display());
			button1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					TestServices.this.age.setText(localBinder.display());

				}
			});

			// 远程服务代码
			// iPerson = Stub.asInterface(service);
			// if (iPerson != null) {
			// try {
			// iPerson.setName("lin");
			// iPerson.setAge("11");
			// String msg = iPerson.display();
			// Log.v(Tag, msg);
			// } catch (RemoteException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// }

		}
	};

}
