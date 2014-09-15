package com.my.f;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class ListTestActivity extends Activity {
	Cursor c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list1);
		c = getContentResolver().query(People.CONTENT_URI, null, null, null,
				null);
		startManagingCursor(c);
		GridView grid = (GridView) findViewById(R.id.grid);
		grid.setNumColumns(4);
		grid.setAdapter(new MyAdapter(this));

		// ListAdapter ad = new SimpleCursorAdapter(this,
		// android.R.layout.simple_list_item_1, c,
		// new String[] { People.NAME }, new int[] { android.R.id.text1 });
		// ListAdapter ad=new MyAdapter(){

	}

	class MyAdapter extends BaseAdapter {
		Context context;
		String[] str = { "lin", "w", "d", "c", "a", "t", "b", "e", "n", "g" };

		public MyAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			return str.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = new TextView(getApplicationContext());
			view.setText(str[position]);
			Log.v("getview", "" + position);
			return view;

		}
	}
}
