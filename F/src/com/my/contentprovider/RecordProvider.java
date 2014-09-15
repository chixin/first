package com.my.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class RecordProvider extends ContentProvider {
	SharedPreferences sp;
	UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);

	@Override
	public boolean onCreate() {
		sp = getContext().getSharedPreferences("records", Context.MODE_PRIVATE);
		um.addURI("lina", "all", 1);
		um.addURI("lina", "name", 2);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		String[] columns = { "_id", "name" };
		MatrixCursor c = new MatrixCursor(columns);
		if (um.match(uri) == 1) {
			c.addRow(new String[] { "1", "lin" });
			c.addRow(new String[] { "2", "wen" });
			c.addRow(new String[] { "3", "hui" });
			c.addRow(new String[] {
					"4",
					getContext().getContentResolver().getType(
							Uri.parse("content://lina/all")) });
		} else if (um.match(uri) == 2) {
			c.addRow(new String[] { "1", "a" });
			c.addRow(new String[] { "2", "b" });
			c.addRow(new String[] { "3", "c" });
		}
		return c;
	}

	@Override
	public String getType(Uri uri) {
		return "html/text";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
