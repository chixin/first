package com.my.picture;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.my.f.R;

public class TestPic extends Activity {
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
				Animation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				scaleAnimation.setDuration(2000);
				image.startAnimation(scaleAnimation);

			}
		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation translateAnimation = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 1f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 1f);
				translateAnimation.setDuration(3000);
				image.startAnimation(translateAnimation);

			}
		});
		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation alphaAnimation = new AlphaAnimation(0, 1f);
				alphaAnimation.setDuration(3000);
				image.startAnimation(alphaAnimation);

			}
		});
		b4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation rotateAnimation = new RotateAnimation(0f, 360f,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				rotateAnimation.setDuration(3000);
				image.startAnimation(rotateAnimation);
			}
		});

	}
}
