package com.my.f;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class GetDisplay extends Activity {
	static byte[] Mac;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 取消标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏
		setContentView(R.layout.querydeviceinfo);
		TextView text = (TextView) findViewById(R.id.resultid);
		Display display = getWindowManager().getDefaultDisplay();

		text.setText(display.getWidth() + "    " + display.getHeight());

		Toast.makeText(this, display.getWidth() + "" + display.getHeight(),
				Toast.LENGTH_LONG).show();

	}
}
