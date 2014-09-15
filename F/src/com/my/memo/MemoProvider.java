package com.my.memo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class MemoProvider extends ContentProvider {

	private final String DATABASE_NAME = "task_list.db";
	private final String TABLE_NAME = "tasklists";

	private final int DATABASE_VERSION = 1;
	private DBHelper dbHelper;
	private final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);;
	private final int ALL = 1;
	private final int ID = 2;
	private final String AUTHORITY = "com.my.memo.tasklist";

	private Map<String, String> projectionMap = new HashMap<String, String>();;

	private class DBHelper extends SQLiteOpenHelper {
		private final String createSql = "create table "
				+ TABLE_NAME
				+ " (_id integer primary key,date TEXT,title TEXT,content TEXT,on_off integer,alarm integer)";

		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(createSql);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}

	@Override
	public boolean onCreate() {
		dbHelper = new DBHelper(getContext());
		uriMatcher.addURI(AUTHORITY, "ALL", 1);
		uriMatcher.addURI(AUTHORITY, "ID", 2);

		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		projectionMap.put("_id", "_id");
		projectionMap.put("date", "date");

		projectionMap.put("title", "title");
		projectionMap.put("content", "content");
		projectionMap.put("on_off", "on_off");
		projectionMap.put("alarm", "alarm");

		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (uriMatcher.match(uri)) {
		case ALL:
			qb.setTables(TABLE_NAME);
			qb.setProjectionMap(projectionMap);
			break;

		case ID:
			qb.setTables(TABLE_NAME);
			qb.setProjectionMap(projectionMap);
			qb.appendWhere("_id=" + uri.getPathSegments().get(1));
			break;

		default:
			Log.v("", "DB query error");
		}

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null,
				null, sortOrder);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss",
				Locale.getDefault());

		Calendar ca = Calendar.getInstance();

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		values.put("date", date.format(ca.getTime()));
		values.put("on_off", 0);
		values.put("alarm", 0);
		db.insert(TABLE_NAME, null, values);
		db.close();
		Log.v("db", "insert finish!");
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(TABLE_NAME, "_id", selectionArgs);
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.update(TABLE_NAME, values, selection, selectionArgs);
		return 0;
	}

}
