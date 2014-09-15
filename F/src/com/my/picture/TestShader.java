package com.my.picture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.my.f.R;

public class TestShader extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView myView = new MyView(this);
		setContentView(myView);
	};

	public class MyView extends View {
		private Bitmap bitmap;
		private Shader bitmapShader;
		private Shader linearGradient;
		private Shader radialGradient;
		private Shader sweepGradient;
		private Shader composeShader;
		private Paint paint;
		private int[] colors;

		public MyView(Context context) {
			super(context);
			paint = new Paint();
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.yuexiuya);
			colors = new int[] { Color.RED, Color.GREEN, Color.BLUE };

			bitmapShader = new BitmapShader(bitmap, TileMode.REPEAT,
					TileMode.MIRROR);
			linearGradient = new LinearGradient(0, 0, 10, 10, colors, null,
					TileMode.REPEAT);
			radialGradient = new RadialGradient(100, 1000, 80, colors, null,
					TileMode.REPEAT);
			sweepGradient = new SweepGradient(400, 400, colors, null);

			composeShader = new ComposeShader(linearGradient, sweepGradient,
					PorterDuff.Mode.DARKEN);

		}

		@Override
		public void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			paint.setShader(composeShader);
			canvas.drawRect(0, 0, getRight(), getBottom(), paint);
		}

	}

}
