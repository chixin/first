package com.my.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.my.service.IPerson.Stub;

public class MyRemoteService extends Service {
	String Tag = "testService";

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(Tag, "onBind");
		Log.v(Tag, "service pid" + android.os.Process.myPid());
		Stub person = new IPersonImpl();
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
	public void onDestroy() {
		Log.v(Tag, "onDestroy");
	}
}
