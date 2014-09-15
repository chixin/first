package com.my.data;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final String Tag = "DBHelper";
	private static final String DBName = "coll.db";
	private static final String TabName = "collTab";
	private SQLiteDatabase db;
	private SQLiteQueryBuilder qb;
	private static final String createTab = "create table "
			+ TabName
			+ "(id integer primary key autoincrement,name text,url text,desc text)";

	public DBHelper(Context context) {
		super(context, DBName, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		db.execSQL(createTab);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void insert(ContentValues values) {

		db = getWritableDatabase();
		db.insert(TabName, null, values);
		db.close();

	}

	public void del(int id) {
		db = getWritableDatabase();
		db.delete(TabName, "id=?", new String[] { String.valueOf(id) });

	}

	public Cursor query() {
		Log.v(Tag, "execute query...");
		db = getWritableDatabase();

		Map<String, String> map = new HashMap<String, String>();
		qb = new SQLiteQueryBuilder();
		qb.setTables(TabName);
		map.put("_id", "id");
		map.put("name", "name");
		map.put("url", "url");
		map.put("desc", "desc");

		qb.setProjectionMap(map);

		// Cursor c = db.query(TabName, new String[] { "id as _id", "name",
		// "url",
		// "desc" }, null, null, null, null, null);
		Cursor c = qb.query(db, new String[] { "id as _id", "name", "url",
				"desc" }, null, null, null, null, null);
		return c;

	}

	public void close() {
		if (db != null) {
			db.close();
		}
	}
}
