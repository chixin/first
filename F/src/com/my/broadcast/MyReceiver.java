package com.my.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	String Tag = "broadcast";

	@Override
	public void onReceive(Context context, Intent intent) {
		String msg = intent.getStringExtra("msg");
		Log.v(Tag, "timetick");
		Log.v(Tag, String.valueOf(System.currentTimeMillis()));
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
