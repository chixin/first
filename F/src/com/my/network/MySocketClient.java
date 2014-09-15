package com.my.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.my.f.R;

public class MySocketClient extends Activity {

	TextView text;
	String msg;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testnetwork);

		text = (TextView) findViewById(R.id.networkText);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Socket client = new Socket("192.168.1.106", 8080);

					BufferedReader reader = new BufferedReader(
							new InputStreamReader(client.getInputStream()));

					String buff = "";
					StringBuffer sb = new StringBuffer(100);
					while ((buff = reader.readLine()) != null) {
						sb.append(buff);
					}
					reader.close();
					msg = sb.toString();
					// h.
					MySocketClient.this.runOnUiThread(uithread);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}).start();

	}

	// 使用handler的sendmessage传递消息
	// Handler h = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	//
	// if (msg != null) {
	// text.setText("handle message" + " "
	// + msg.getData().getString("data"));
	// Log.v("handle message", msg.getData().getString("data"));
	// } else {
	// text.setText("数据错误！");
	// }
	//
	// }

	// };

	// 使用handler post方法切换线程
	// Handler h = new Handler();
	//
	// Runnable runnable = new Runnable() {
	//
	// @Override
	// public void run() {
	// if (msg != null) {
	// text.setText("handle post" + " " + msg);
	// Log.v("handle post", msg);
	// } else {
	// text.setText("数据错误！");
	// }
	//
	// }
	//
	// };

	// ui thread
	Runnable uithread = new Runnable() {

		@Override
		public void run() {
			if (msg != null) {
				text.setText("runOnUiThread" + " " + msg);
				Log.v("handle post", msg);
			} else {
				text.setText("数据错误！");
			}

		}

	};

}
