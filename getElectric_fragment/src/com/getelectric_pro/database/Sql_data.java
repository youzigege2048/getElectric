package com.getelectric_pro.database;

import java.net.ContentHandler;

import com.getelectric_pro.main.DataMain;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

public class Sql_data {
	public String drxiaoqu = "8";
	public String drlou = "Ü½ÈØ1";
	public String txtroomid = "0101";
	public String moneyinfo = "222Ôª";
	public String eleinfo = "666¶È";
	public String code = "26010101";
	public String time = "null";

	public void getsql() {

	}

	public Sql_data() {

	}

	public Sql_data(Context context) {
		dataDb db = new dataDb(context);
		if (!isDb(context)) {
			SQLiteDatabase dbDatabaseset = db.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("id", "1");
			values.put("drxiaoqu", drxiaoqu);
			values.put("drlou", drlou);
			values.put("txtroomid", txtroomid);
			values.put("moneyinfo", moneyinfo);
			values.put("eleinfo", eleinfo);
			values.put("code", code);
			values.put("time", Basedata.getTime());
			dbDatabaseset.insert("info", null, values);
		} else {
			Cursor cursor = getInfo(context);
			while (cursor.moveToNext()) {
				this.drxiaoqu = cursor.getString(cursor
						.getColumnIndex("drxiaoqu"));
				this.drlou = cursor.getString(cursor.getColumnIndex("drlou"));
				this.txtroomid = cursor.getString(cursor
						.getColumnIndex("txtroomid"));
				this.moneyinfo = cursor.getString(cursor
						.getColumnIndex("moneyinfo"));
				this.eleinfo = cursor.getString(cursor
						.getColumnIndex("eleinfo"));
				this.code = cursor.getString(cursor.getColumnIndex("code"));
				this.time = cursor.getString(cursor.getColumnIndex("time"));
			}
		}
	}

	public boolean isDb(Context context) {
		dataDb db = new dataDb(context);
		SQLiteDatabase dbDatabase = db.getReadableDatabase();
		Cursor cursor = dbDatabase
				.query("info", null, "id=1", null, "", "", "");
		// System.out.println(cursor.getCount());
		// while (cursor.moveToNext()) {
		// System.out.println(cursor.getString(cursor.getColumnIndex("id")));
		// System.out.println(cursor.getString(cursor
		// .getColumnIndex("drxiaoqu")));
		// System.out
		// .println(cursor.getString(cursor.getColumnIndex("drlou")));
		// System.out.println(cursor.getString(cursor
		// .getColumnIndex("txtroomid")));
		// System.out.println(cursor.getString(cursor
		// .getColumnIndex("moneyinfo")));
		// System.out.println(cursor.getString(cursor
		// .getColumnIndex("eleinfo")));
		// }
		if (cursor.getCount() == 0)
			return false;
		return true;
	}

	public Cursor getInfo(Context context) {
		dataDb db = new dataDb(context);
		SQLiteDatabase dbDatabase = db.getReadableDatabase();
		Cursor cursor = dbDatabase
				.query("info", null, "id=1", null, "", "", "");
		return cursor;
	}

	public void bindinfo(Context context, String drxiaoqu, String drlou,
			String txtroomid, String code) {
		dataDb db = new dataDb(context);
		SQLiteDatabase dbDatabaseset;
		if (!isDb(context)) {
			dbDatabaseset = db.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("id", "1");
			values.put("drxiaoqu", drxiaoqu);
			values.put("drlou", drlou);
			values.put("txtroomid", txtroomid);
			values.put("moneyinfo", moneyinfo);
			values.put("eleinfo", eleinfo);
			values.put("code", code);
			// values.put("time", Basedata.getTime());
			dbDatabaseset.insert("info", null, values);
		} else {
			dbDatabaseset = db.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("drxiaoqu", drxiaoqu);
			values.put("drlou", drlou);
			values.put("txtroomid", txtroomid);
			values.put("code", code);
			// values.put("time", Basedata.getTime());
			dbDatabaseset.update("info", values, "id=?", new String[] { "1" });
		}
	}

	public void uptime(Context context) {
		dataDb db = new dataDb(context);
		SQLiteDatabase dbDatabaseset = db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("time", Basedata.getTime());
		System.out.println(values.get("time") + "  ---");
		dbDatabaseset.update("info", values, "id=?", new String[] { "1" });
	}

	public String getDrxiaoqu() {
		return drxiaoqu;
	}

	public String getDrlou() {
		return drlou;
	}

	public String getTxtroomid() {
		return txtroomid;
	}

	public String getMoneyinfo() {
		return moneyinfo;
	}

	public String getEleinfo() {
		return eleinfo;
	}
}
