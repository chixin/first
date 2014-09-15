package com.my.f;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	// public Dialog onCreateDialog(int id) {
	// ProgressDialog p = new ProgressDialog(this);
	// p.setTitle("title");
	// p.setIndeterminate(true);
	// p.setMessage("message");
	// p.setCancelable(true);
	// return p;
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.timepicker);
		// Button b = (Button) findViewById(R.id.bu);
		// bar = (ProgressBar) findViewById(R.id.pro);
		// b.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// bar.incrementProgressBy(1);
		// }
		// });

		// TabHost th = getTabHost();
		// LayoutInflater.from(this).inflate(R.layout.tab,
		// th.getTabContentView(),
		// true);
		// th.addTab(th.newTabSpec("ALL").setIndicator("全部").setContent(this));
		// th.addTab(th.newTabSpec("OK").setIndicator("已接").setContent(this));
		// th.addTab(th.newTabSpec("CANCEL").setIndicator("未接").setContent(this));

		// Log.v("lin", "my log");
		// ImageView imageView = (ImageView) findViewById(R.id.pic1);
		// imageView.setImageDrawable(getResources().getDrawable(
		// R.drawable.yuexiuya));
		// ImageView image = new ImageView(this);
		// image.setImageDrawable(getResources().getDrawable(R.drawable.yuexiuya));
		// mi = new MenuInflater(this);
		//
		// Button button = (Button) findViewById(R.id.click);
		// button.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// int color = v.getDrawingCacheBackgroundColor();
		// Button b = (Button) v;
		// if (b.getText().equals("确定")) {
		// b.setText("click");
		// } else {
		// b.setText("确定");
		// }
		// Log.v("lin", String.valueOf(color));
		//
		// }
		// });

	}
	// @Override
	// public View createTabContent(String tag) {
	// ListView lv = new ListView(this);
	// List<String> list = new ArrayList<String>();
	// if (tag == "ALL") {
	// list.add("a");
	// list.add("b");
	// } else if (tag == "OK") {
	// list.add("d");
	// list.add("e");
	// } else {
	// list.add("f");
	// list.add("g");
	// }
	//
	// ArrayAdapter ad = new ArrayAdapter(this,
	// android.R.layout.simple_list_item_checked, list);
	// lv.setAdapter(ad);
	//
	// return lv;
	// }
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // TODO Auto-generated method stub
	// // menu.add(0, 1, 1, "苹果");
	// // menu.add(0, 2, 2, "香蕉");
	// mi.inflate(R.menu.main, menu);
	// return true;
	// }

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // TODO Auto-generated method stub
	// if (item.getItemId() == R.id.item1) {
	// Toast t = Toast.makeText(this, "你选的是苹果", Toast.LENGTH_SHORT);
	// t.show();
	// } else if (item.getItemId() == R.id.item2) {
	// Toast t = Toast.makeText(this, "你选的是香蕉", Toast.LENGTH_SHORT);
	// t.show();
	// }
	// return true;
	// }

}
