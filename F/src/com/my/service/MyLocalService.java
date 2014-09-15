package com.my.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyLocalService extends Service {
	String Tag = "testService";

	public class LocalBinder extends Binder {
		private String name, age;

		public void setAge(String age) {

			this.age = age;

		}

		public void setName(String name) {
			this.name = name;

		}

		int count = 1;

		public String display() {
			while (true) {
				count++;
				return count + "name" + name + "  " + "age" + age;
			}

		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(Tag, "onBind");
		Log.v(Tag, "service pid" + android.os.Process.myPid());
		Log.v(Tag, "service tid" + Thread.currentThread().getId());
		LocalBinder person = new LocalBinder();
		return person;
	}

	@Override
	public void onCreate() {
		Log.v(Tag, "onCreate");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(Tag, "onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(Tag, "onStartCommand");
		boolean mStartCompatibility = false;
		onStart(intent, startId);
		return mStartCompatibility ? START_STICKY_COMPATIBILITY : START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.v(Tag, "onDestroy");
	}
}
