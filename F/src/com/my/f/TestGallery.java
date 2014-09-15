package com.my.f;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class TestGallery extends Activity implements OnItemSelectedListener,
		ViewFactory {

	private Integer[] imgIds = { R.drawable.a1, R.drawable.a2, R.drawable.a3,
			R.drawable.a4, R.drawable.a5, R.drawable.a6 };
	private ImageSwitcher imgSwitcher;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gallery);
		imgSwitcher = (ImageSwitcher) findViewById(R.id.switcher);

		imgSwitcher.setFactory(this);
		imgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		imgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this));
		g.setOnItemSelectedListener(this);

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		imgSwitcher.setImageResource(imgIds[position]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	public class ImageAdapter extends BaseAdapter {
		Context context;

		public ImageAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgIds.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imgview = new ImageView(context);
			imgview.setImageResource(imgIds[position]);
			// imgview.setAdjustViewBounds(true);

			imgview.setLayoutParams(new Gallery.LayoutParams(600, 300));
			return imgview;
		}

	}

	@Override
	public View makeView() {

		ImageView image = new ImageView(this);
		image.setScaleType(ImageView.ScaleType.FIT_CENTER);
		image.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return image;

	}

}
