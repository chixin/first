package com.my.data;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.my.f.R;

public class QueryResult extends ListActivity {
	private TextView id, name, url, desc;
	DBHelper dbh;
	Cursor c;
	ListView listView;
	String Tag = "show data";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.queryresult);

		// id = (TextView) findViewById(R.id.resultid);
		// name = (TextView) findViewById(R.id.resultname);
		// url = (TextView) findViewById(R.id.resulturl);
		// desc = (TextView) findViewById(R.id.resultdesc);

		dbh = new DBHelper(this);
		showResult();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				dbh.del((int) id);

				Toast.makeText(getApplicationContext(), "É¾³ý³É¹¦:" + id,
						Toast.LENGTH_SHORT).show();
				showResult();
			}
		});

		dbh.close();
	}

	public void showResult() {

		c = dbh.query();
		Log.v(Tag, "execute showResult...");
		String[] from = { "_id", "name", "url", "desc" };
		int[] to = { R.id.resultid, R.id.resultname, R.id.resulturl,
				R.id.resultdesc };
		Log.v(Tag, "21");
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.querydeviceinfo, c, from, to);

		listView = getListView();

		listView.setAdapter(adapter);

		c.moveToFirst();
		while (!c.isAfterLast()) {

			Log.v(Tag,
					c.getString(0) + ":" + c.getString(1) + ":"
							+ c.getString(2) + ":" + c.getString(3) + "size:"
							+ listView.getChildCount() + "c.count:"
							+ c.getCount() + "c.columncount:"
							+ c.getColumnCount() + "");
			c.moveToNext();
		}
		Log.v(Tag, "execute showResult finish.");
	}
}
