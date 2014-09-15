package com.my.contentprovider;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.my.f.R;

public class TestProvider extends ListActivity {
	TextView name;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Cursor c = getContentResolver().query(Uri.parse("content://lina/all"),
				null, null, null, null);
		String info = "";
		if (c.getCount() == 0) {
			info = "ÎÞÊý¾Ý";
		} else {
			Log.v("LIN", "line number:" + c.getCount());
			Log.v("LIN",
					"Content Type:"
							+ getContentResolver().getType(
									Uri.parse("content://lina")));

			c.moveToFirst();
		}

		ListAdapter ad = new SimpleCursorAdapter(getApplicationContext(),
				R.layout.testprovider, c, new String[] { "_id", "name" },
				new int[] { R.id.providerid, R.id.providername });

		getListView().setAdapter(ad);

	}
}
