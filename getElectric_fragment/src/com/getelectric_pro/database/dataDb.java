package com.getelectric_pro.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class dataDb extends SQLiteOpenHelper {

	public dataDb(Context context) {
		super(context, "db", null, 1);
	}

	public String drxiaoqu = "8";
	public String drlou = "Ü½ÈØ1";
	public String txtroomid = "0401";
	public String moneyinfo = "120Ôª";
	public String eleinfo = "666¶È";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE info( " + "id INT(4)," + "drxiaoqu TEXT(20),"
				+ "drlou TEXT(20)," + "txtroomid TEXT(20),"
				+ "moneyinfo TEXT(20)," + "code TEXT(20)," + "time TEXT(20),"
				+ "eleinfo TEXT(20)" + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
