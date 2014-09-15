package com.my.others;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.my.f.R;

public class AsyncTaskTest1 extends Activity {
	ProgressBar p;
	Button b;
	int downloadPercent;
	ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress);
		p = (ProgressBar) findViewById(R.id.pro);
		b = (Button) findViewById(R.id.bu);
		progressDialog = (ProgressDialog) onCreateDialog(0);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DownloadTask().execute();

			}
		});

	}

	class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				while (true) {
					int downloadPercent = doDownload();
					publishProgress(downloadPercent);
					if (downloadPercent >= 100) {
						break;
					}
				}
			} catch (Exception e) {
				return false;
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			progressDialog.setMessage("当前下载进度：" + values[0] + "%");
			p.setProgress(values[0]);
			Toast.makeText(getApplicationContext(), "下载进度" + values[0] + "%",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// progressDialog.dismiss();
			if (result) {
				Toast.makeText(getApplicationContext(), "下载成功",
						Toast.LENGTH_SHORT).show();
				progressDialog.dismiss();
			} else {
				Toast.makeText(getApplicationContext(), "下载失败",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private int doDownload() {
		try {
			Thread.sleep(500);
			downloadPercent = downloadPercent + 5;

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return downloadPercent;
	}

	public Dialog onCreateDialog(int id) {
		ProgressDialog p = new ProgressDialog(this);
		p.setTitle("title");
		p.setIndeterminate(true);
		p.setMessage("message");
		p.setCancelable(true);
		return p;
	}

}
