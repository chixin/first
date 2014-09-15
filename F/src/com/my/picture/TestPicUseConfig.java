package com.my.picture;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.my.f.R;

public class TestPicUseConfig extends Activity {
	ImageView image;
	Button b1, b2, b3, b4;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testpicture);
		image = (ImageView) findViewById(R.id.mypic);
		b1 = (Button) findViewById(R.id.picid1);
		b2 = (Button) findViewById(R.id.picid2);
		b3 = (Button) findViewById(R.id.picid3);
		b4 = (Button) findViewById(R.id.picid4);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation scaleAnimation = AnimationUtils.loadAnimation(
						TestPicUseConfig.this, R.anim.scale);
				image.startAnimation(scaleAnimation);

			}
		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation translateAnimation = AnimationUtils.loadAnimation(
						TestPicUseConfig.this, R.anim.translate);
				image.startAnimation(translateAnimation);

			}
		});
		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation alphaAnimation = AnimationUtils.loadAnimation(
						TestPicUseConfig.this, R.anim.alpha);
				image.startAnimation(alphaAnimation);

			}
		});
		b4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation rotateAnimation = AnimationUtils.loadAnimation(
						TestPicUseConfig.this, R.anim.rotate);
				image.startAnimation(rotateAnimation);
			}
		});

	}
}
