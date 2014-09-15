package com.my.f;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class CopyOfMainActivity extends Activity {
	private ImageView pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.back);
		Log.v("lin", "my log");
		pic = (ImageView) findViewById(R.id.image);
		registerForContextMenu(pic);
		Button b = (Button) findViewById(R.id.b);

		final String[] items = { "qq", "yue" };

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				builder.setTitle("«Î—°‘Ò").setItems(items,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (items[which] == "qq") {
									pic.setImageResource(R.drawable.qqshow);
								} else if (items[which] == "yue") {
									pic.setImageResource(R.drawable.yuexiuya);
								}

							}
						});
				builder.create().show();
			}

		});

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add(0, 1, 1, "yue");
		menu.add(0, 2, 2, "show");
		SubMenu subMenu = menu.addSubMenu("—°‘Ò");
		subMenu.add(2, 4, 1, "yue");
		subMenu.add(2, 5, 2, "show");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case 1:
			pic.setImageResource(R.drawable.yuexiuya);
			break;
		case 2:
			pic.setImageResource(R.drawable.qqshow);
			break;
		case 4:
			pic.setImageResource(R.drawable.yuexiuya);
			break;
		case 5:
			pic.setImageResource(R.drawable.qqshow);
			break;
		}

		return true;
	}
}
