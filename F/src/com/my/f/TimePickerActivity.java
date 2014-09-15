package com.my.f;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerActivity extends Activity {

	public Dialog onCreateDialog(int id) {

		if (id == 0) {

			return new DatePickerDialog(this, 1, l1, year, month, day);

		} else {
			return new TimePickerDialog(this, 1, l2, hour, minute, true);
		}

	}

	private TextView dateview;
	private TextView timeview;
	private int year, month, day, hour, minute;
	private Button dateb, timeb;
	private Calendar ca = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);//
		setContentView(R.layout.timepicker);
		dateview = (TextView) findViewById(R.id.dateview);
		timeview = (TextView) findViewById(R.id.timeview);
		dateb = (Button) findViewById(R.id.dateb);
		timeb = (Button) findViewById(R.id.timeb);
		year = ca.get(Calendar.YEAR);
		month = ca.get(Calendar.MONTH);
		day = ca.get(Calendar.DAY_OF_MONTH);
		hour = ca.get(Calendar.HOUR);
		minute = ca.get(Calendar.MINUTE);
		dateview.setText(year + ":" + (month + 1) + ":" + day);
		timeview.setText(hour + ":" + minute);

		dateb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(0);

			}
		});
		timeb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(1);

			}
		});

	}

	OnDateSetListener l1 = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateview.setText(year + ":" + (monthOfYear + 1) + ":" + dayOfMonth);
		}
	};
	OnTimeSetListener l2 = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			timeview.setText(hourOfDay + ":" + minute);
		}
	};
}
