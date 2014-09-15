package com.my.broadcast;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.my.f.R;

public class BroadCastSender extends Activity {
	Button b1, b2;
	String Tag = "broadcast";
	Notification n;
	NotificationManager nm;
	AlarmManager am;
	PendingIntent pi;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcastsender);
		b1 = (Button) findViewById(R.id.broadcastsend);
		b2 = (Button) findViewById(R.id.broadcastcancel);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		am = (AlarmManager) getSystemService(ALARM_SERVICE);
		n = new Notification();
		n.icon = R.drawable.yuexiuya;
		n.tickerText = "我的通知展示";
		n.when = System.currentTimeMillis();

		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.v(Tag, "onclick");
				Intent intent = new Intent(BroadCastSender.this,
						com.my.e.TestIntent.class);

				// intent.putExtra("msg", "闹钟响了");
				pi = PendingIntent.getActivity(BroadCastSender.this, 0, intent,
						0);
				n.setLatestEventInfo(BroadCastSender.this, "my title",
						"my content", pi);
				// am.setRepeating(AlarmManager.RTC_WAKEUP,
				// System.currentTimeMillis(), 1000, pi);
				nm.notify(1, n);

				// intent.putExtra("msg", "我是广播");
				// sendBroadcast(intent);

				// registerReceiver(new MyReceiver(), new IntentFilter(
				// Intent.ACTION_TIME_TICK));

			}
		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nm.cancel(1);
				// am.cancel(pi);
			}
		});
	}
}
