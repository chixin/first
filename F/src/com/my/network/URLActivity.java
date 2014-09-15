package com.my.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.my.f.R;

public class URLActivity extends Activity {
	ImageView image;
	String address;
	Bitmap bitmap;
	int imageWidth, imageHeight;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȡ��������
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ȫ��
		setContentView(R.layout.testurl);

		image = (ImageView) findViewById(R.id.urlpic);

		// ��create����ִ����ϲ��ܻ�ȡ�ؼ���͸�
		ViewTreeObserver vob = image.getViewTreeObserver();
		vob.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			boolean isFirst = true;

			@Override
			public void onGlobalLayout() {
				if (isFirst) {
					isFirst = false;
					imageWidth = image.getMeasuredWidth();
					imageHeight = image.getMeasuredHeight();

					new Thread(getpic).start();
				}
			}
		});

		Toast.makeText(getApplicationContext(), "������...", Toast.LENGTH_SHORT)
				.show();

	}

	Runnable uithread = new Runnable() {

		@Override
		public void run() {
			image.setClickable(true);
			Log.v("clickable", String.valueOf(image.isClickable()));
			image.setImageBitmap(bitmap);
			image.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					new Thread(getpic).start();
					Log.v("click", "click");
					Toast.makeText(getApplicationContext(), "������...",
							Toast.LENGTH_SHORT).show();

				}
			});

		}

	};
	// ��ȡһ��ͼƬ
	// Runnable getpic = new Runnable() {
	//
	// @Override
	// public void run() {
	// try {
	// address = "http://placeimg.com/" + imageWidth + "/"
	// + imageHeight + "/" + "any";
	// URL url = new URL(address);
	//
	// Log.v("urltest", address);
	// image.setClickable(false);
	// Log.v("clickable", String.valueOf(image.isClickable()));
	// InputStream input = url.openStream();
	// bitmap = BitmapFactory.decodeStream(input);
	// URLActivity.this.runOnUiThread(uithread);
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// };

	// ʹ��apachehttpclient
	Runnable getpic = new Runnable() {
		InputStream input;

		@Override
		public void run() {
			try {
				address = "http://placeimg.com/" + imageWidth + "/"
						+ imageHeight + "/" + "any";
				HttpGet request = new HttpGet(address);

				HttpResponse response = new DefaultHttpClient()
						.execute(request);
				Log.v("address", address);

				if (response.getStatusLine().getStatusCode() == 200) {

					input = response.getEntity().getContent();

				}
				image.setClickable(false);
				Log.v("clickable", String.valueOf(image.isClickable()));
				bitmap = BitmapFactory.decodeStream(input);
				URLActivity.this.runOnUiThread(uithread);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

}
