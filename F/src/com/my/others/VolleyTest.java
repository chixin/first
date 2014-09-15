package com.my.others;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.my.f.R;

public class VolleyTest extends Activity {

	ImageView image;
	String address;
	Bitmap bitmap;
	int imageWidth, imageHeight;
	MyImageCache imageCache;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 取消标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏
		setContentView(R.layout.testurl);
		image = (ImageView) findViewById(R.id.urlpic);

		// 在create函数执行完毕才能获取控件宽和高
		ViewTreeObserver vob = image.getViewTreeObserver();
		imageCache = new MyImageCache();
		vob.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			boolean isFirst = true;

			@Override
			public void onGlobalLayout() {
				if (isFirst) {
					isFirst = false;
					imageWidth = image.getMeasuredWidth();
					imageHeight = image.getMeasuredHeight();
					address = "http://placeimg.com/" + imageWidth + "/"
							+ imageHeight + "/" + "any";
					// getpic(address);

					getpicWithImageLoader(address);

				}
			}
		});
		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getpicWithImageLoader(address);
				Toast.makeText(getApplicationContext(), "加载中...",
						Toast.LENGTH_SHORT).show();

			}
		});
		Toast.makeText(getApplicationContext(), "加载中...", Toast.LENGTH_SHORT)
				.show();

	}

	public void getpic(String url) {

		RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
		ImageRequest re = new ImageRequest(url,
				new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap arg0) {
						image.setImageBitmap(arg0);

					}

				}, imageHeight, imageHeight, Config.RGB_565,
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						image.setImageDrawable(getResources().getDrawable(
								R.drawable.yuexiuya));

					}
				});
		queue.add(re);
	}

	public void getpicWithImageLoader(String url) {
		Log.v("address", url);
		RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
		ImageLoader re = new ImageLoader(queue, imageCache);
		ImageListener l = re.getImageListener(image, R.drawable.yuexiuya,
				R.drawable.a1);
		re.get(url, l);

	}

	public class MyImageCache implements ImageCache {
		private LruCache<String, Bitmap> mCache;

		public MyImageCache() {
			int maxSize = 10 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}

		@Override
		public Bitmap getBitmap(String url) {
			return mCache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			mCache.put(url, bitmap);
		}
	}

}
