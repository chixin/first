package com.my.picture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.f.R;

public class TestMatrix extends Activity {

	DrawView mypic;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		LinearLayout llayout = new LinearLayout(this);
		llayout.setOrientation(LinearLayout.VERTICAL);
		mypic = new DrawView(this);

		Button b1 = new Button(this);
		Button b2 = new Button(this);
		Button b3 = new Button(this);
		Button b4 = new Button(this);
		b1.setText("左");
		b2.setText("右");

		b3.setText("上");
		b4.setText("下");
		llayout.addView(b1);
		llayout.addView(b2);
		llayout.addView(b3);
		llayout.addView(b4);
		llayout.addView(mypic);
		setContentView(llayout);

		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mypic.click(1);
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mypic.click(2);

			}
		});
		b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mypic.click(3);
			}
		});
		b4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mypic.click(4);

			}
		});
	}

	public class DrawView extends View {
		private Bitmap bm;
		private Matrix matrix = new Matrix();
		private int w, h;
		private float angle = 0.0f;
		private float scale = 1.0f;
		private boolean isScale = false;

		public DrawView(Context context) {
			super(context);

			bm = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.yuexiuya);
			w = bm.getWidth();
			h = bm.getHeight();

			this.setFocusable(true);
		}

		@Override
		public void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			matrix.reset();
			if (!isScale) {
				matrix.setRotate(angle);
			} else {
				matrix.setScale(scale, scale);

			}
			Bitmap bm2 = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, true);
			canvas.drawBitmap(bm2, matrix, null);
		}

		public boolean click(int event) {
			if (event == 1) {
				isScale = false;
				angle++;
				postInvalidate();
			}
			if (event == 2) {
				isScale = false;
				angle--;
				postInvalidate();
			}
			if (event == 3) {
				isScale = true;

				scale += 0.1;
				Log.v("", String.valueOf(scale));
				postInvalidate();
			}
			if (event == 4) {
				isScale = true;

				scale -= 0.1;
				Log.v("", String.valueOf(scale));
				if (scale <= 0) {

					Toast.makeText(getApplicationContext(), "不能再小了",
							Toast.LENGTH_SHORT).show();

					return true;
				}
				postInvalidate();
			}
			return true;
		}

	}

}
