package com.my.picture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class TestDraw extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new drawView(this));
	}

	public class drawView extends View {

		public drawView(Context context) {
			super(context);
		}

		@Override
		public void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawColor(Color.WHITE);
			Paint p = new Paint();
			p.setAntiAlias(true);
			p.setColor(Color.RED);
			p.setStyle(Paint.Style.STROKE);
			p.setStrokeWidth(0);
			canvas.drawCircle(getRight() / 2, getBottom() / 2, 300, p);
			RectF re = new RectF(10, 500, 1000, 1000);
			canvas.drawRect(re, p);
			p.setStrokeWidth(3);
			canvas.drawOval(re, p);
			Path path = new Path();
			path.moveTo(90, 90);
			path.lineTo(900, 90);
			path.lineTo(700, 350);
			path.lineTo(130, 350);
			path.close();
			p.setStyle(Paint.Style.FILL);
			canvas.drawPath(path, p);

		}

	}

}
