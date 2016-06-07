package com.chadian.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dataDb extends SQLiteOpenHelper {

    public dataDb(Context context) {
        super(context, "db", null, 1);
    }

    public String drxiaoqu = "8";
    public String drlou = "芙蓉1";
    public String txtroomid = "0401";
    public String moneyinfo = "12";
    public String eleinfo = "12";

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
