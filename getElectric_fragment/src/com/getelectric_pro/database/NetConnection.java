package com.getelectric_pro.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import android.R.integer;
import android.os.AsyncTask;

public class NetConnection {

	public NetConnection(final String url,
			final SuccessCallback successCallback,
			final FailCallback failCallback, final String... kvs) {

		new AsyncTask<Void, Void, String>() {

			protected String doInBackground(Void... arg0) {

				Http_getResult getinfo = new Http_getResult();
				subInfo subInfo = new subInfo();
				System.out.println(kvs[0] + " -  " + kvs[1] + " -  " + kvs[2]
						+ " -  " + kvs[3]);
				try {
					String result[] = getinfo.getElecInfo(kvs[0], kvs[1],
							kvs[2], Integer.parseInt(kvs[3]));
					subInfo.postInfo();
					return result[0] + "," + result[1];
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void onPostExecute(String result) {

				if (result != null) {
					if (successCallback != null) {
						successCallback.onSuccess(result);
					}
				} else {
					if (failCallback != null) {
						failCallback.onFail();
					}
				}

				// super.onPostExecute(result);
			}
		}.execute();

	}

	public static interface SuccessCallback {
		void onSuccess(String result);
	}

	public static interface FailCallback {
		void onFail();
	}
}
