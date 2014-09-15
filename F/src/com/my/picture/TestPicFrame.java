package com.my.picture;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
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

public class TestPicFrame extends Activity {
	ImageView image;
	Button b1, b2;
	AnimationDrawable a;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testpicture);
		image = (ImageView) findViewById(R.id.mypic);
		image.setBackgroundResource(R.drawable.frameanimation);
		b1 = (Button) findViewById(R.id.picid1);
		b2 = (Button) findViewById(R.id.picid2);
		a = (AnimationDrawable) image.getBackground();
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				a.start();
			}
		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				a.stop();

			}
		});

	}
}
