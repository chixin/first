package com.my.others;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class GestureDetectorTest extends Activity {
	GestureDetector mGestureDetector;
	ScrollView mScrollView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mScrollView = new ScrollView(this);
		LinearLayout mLinearLayout = new LinearLayout(this);
		mLinearLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams mLayoutParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mScrollView.setLayoutParams(mLayoutParams);
		mLinearLayout.setLayoutParams(mLayoutParams);
		mScrollView.addView(mLinearLayout);
		int i = 0;
		while (i < 100) {
			TextView mView = new TextView(this);
			mView.setLayoutParams(mLayoutParams);
			mView.setText("a" + i);
			mLinearLayout.addView(mView);
			i++;
			Log.v("mView", i + "");
		}
		setContentView(mScrollView);
		// LayoutInflater.from(this).in
		mGestureDetector = new GestureDetector(this, new MyGestureListener());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mGestureDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	class MyGestureListener extends SimpleOnGestureListener {
		public boolean onSingleTapUp(MotionEvent e) {
			Log.v("GestureDetectorTest", "onSingleTapUp");
			return true;
		}

		public void onLongPress(MotionEvent e) {
			Log.v("GestureDetectorTest", "onLongPress");
		}

		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.v("GestureDetectorTest", "onScroll");
			return true;
		}

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float onFling) {

			Log.v("GestureDetectorTest", "onFling");
			return true;
		}

		public void onShowPress(MotionEvent e) {
			Log.v("GestureDetectorTest", "onShowPress");
		}

		public boolean onDown(MotionEvent e) {
			Log.v("GestureDetectorTest", "onDown");
			return true;
		}

		public boolean onDoubleTap(MotionEvent e) {
			Log.v("GestureDetectorTest", "onDoubleTap");
			return true;
		}

		public boolean onDoubleTapEvent(MotionEvent e) {
			e.getAction();
			Log.v("GestureDetectorTest", "onDoubleTapEvent");
			Log.v("onDoubleTapEvent", e.getAction() + "");
			return true;
		}

		public boolean onSingleTapConfirmed(MotionEvent e) {
			Log.v("GestureDetectorTest", "onSingleTapConfirmed");
			return true;
		}

	}

}
