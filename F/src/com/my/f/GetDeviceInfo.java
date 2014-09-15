package com.my.f;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class GetDeviceInfo extends Activity {
	static byte[] Mac;
	TextView plantform;
	TextView MACAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 取消标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏
		setContentView(R.layout.querydeviceinfo);

		plantform = (TextView) findViewById(R.id.plantform);
		MACAddress = (TextView) findViewById(R.id.MAC);
		plantform.setText(getPhonteInfo());
		MACAddress.setText(getMacAddress());
	}

	private String getMacAddress() {
		StringBuffer address = new StringBuffer();
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifi.getConnectionInfo();

		address.append(wifiInfo.getMacAddress() + "\n");
		address.append(wifiInfo.getSSID() + "\n");
		address.append(wifiInfo.getIpAddress() + "\n");

		return address.toString();
	}

	private String getPhonteInfo() {
		String phoneInfo = "Product: " + android.os.Build.PRODUCT;
		phoneInfo += "\nMODEL: " + android.os.Build.MODEL;
		phoneInfo += "\nVERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
		phoneInfo += "\nDEVICE: " + android.os.Build.DEVICE;
		phoneInfo += "\nBRAND: " + android.os.Build.BRAND;
		phoneInfo += "\nBOARD: " + android.os.Build.BOARD;
		Log.v("", phoneInfo);
		return phoneInfo;
	}
}
