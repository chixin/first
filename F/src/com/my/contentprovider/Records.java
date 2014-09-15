package com.my.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Records {
	public static final String AUTHORITY = "com.my.contentprovider.Records";

	public static final class Record implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://lina");
		public static final String NAME = "name";
	}
}
