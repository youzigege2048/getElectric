package com.getelectric_pro.database;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import android.os.Build;

class subInfo {
	// POST提取电费
	public static String postInfo() throws IOException {
		URL postUrl = new URL(Basedata.subUrl);
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");

		// 取消缓存
		connection.setUseCaches(false);

		// 自动处理重定向
		connection.setInstanceFollowRedirects(true);

		connection.setRequestProperty("user-agent", "Mozilla/5.0");
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());

		String data = "MODEL=" + Build.MODEL + "&" + "PRODUCT" + Build.PRODUCT;

		out.writeBytes(data);

		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String line;
		reader.close();
		connection.disconnect();
		return "";
	}

}
