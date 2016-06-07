package com.chadian;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chadian.database.Basedata;
import com.chadian.database.NetConnection;
import com.chadian.database.Sql_data;
import com.chadian.database.dataDb;

public class DataMain extends Fragment {
    Button bind, refresh;
    TextView drxiaoqu, drlou, txtroomid, moneyinfo, elecinfo, code, timeinfo;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_data, container,
                false);

        bind = (Button) rootView.findViewById(R.id.xml_btn_bind);
        bind.setText("绑定");
        refresh = (Button) rootView.findViewById(R.id.xml_btn_refresh);
        refresh.setText("刷新");
        drxiaoqu = (TextView) rootView.findViewById(R.id.xml_tab_drxiaoqu);
        drlou = (TextView) rootView.findViewById(R.id.xml_tab_drlou);
        txtroomid = (TextView) rootView.findViewById(R.id.xml_tab_txtroomid);
        moneyinfo = (TextView) rootView.findViewById(R.id.xml_tab_moneyinfo);
        elecinfo = (TextView) rootView.findViewById(R.id.xml_tab_elecinfo);
        timeinfo = (TextView) rootView.findViewById(R.id.xml_tab_timeinfo);
        code = (TextView) rootView.findViewById(R.id.xml_code);

        refreshInfo(rootView);
        refresh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {

                        Toast.makeText(rootView.getContext(), "正在查询，请稍等",
                                Toast.LENGTH_SHORT).show();
                        RefreshSql(rootView);
                    }
                });

        bind.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        startActivityForResult(new Intent(
                                rootView.getContext(), bind_info.class), 111);
                    }
                });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 20) {
            Toast.makeText(this.getActivity(), "绑定成功，请刷新", Toast.LENGTH_SHORT)
                    .show();
            refreshInfo(this.getView());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setInfo(View rootView) {
        Bundle dataBundle = getArguments();
        drxiaoqu.setText(dataBundle.getString("drxiaoqu"));
        drlou.setText(dataBundle.getString("drlou"));
        txtroomid.setText(dataBundle.getString("txtroomid"));
        moneyinfo.setText(dataBundle.getString("moneyinfo"));
        elecinfo.setText(dataBundle.getString("elecinfo"));
    }

    public void refreshInfo(View rootView) {
        Sql_data sql_data = new Sql_data(rootView.getContext());
        // sql_data.RefreshSql(rootView.getContext());
        if (sql_data.isDb(rootView.getContext())) {
            Cursor cursor = sql_data.getInfo(rootView.getContext());

            while (cursor.moveToNext()) {
                code.setText(cursor.getString(cursor.getColumnIndex("code")));
                drxiaoqu.setText(Basedata.name_drxiaoqu[Integer.parseInt(cursor
                        .getString(cursor.getColumnIndex("drxiaoqu")))]);
                drlou.setText(cursor.getString(cursor
                        .getColumnIndex("drlou")));
                txtroomid.setText(cursor.getString(cursor
                        .getColumnIndex("txtroomid")));
                moneyinfo.setText(cursor.getString(cursor
                        .getColumnIndex("moneyinfo")));
                elecinfo.setText(cursor.getString(cursor
                        .getColumnIndex("eleinfo")));
                timeinfo.setText(cursor.getString(cursor.getColumnIndex("time")));
            }
        }
    }

    public void RefreshSql(final View rootView) {
        Sql_data sql_data = new Sql_data(rootView.getContext());
        Cursor cursor = sql_data.getInfo(rootView.getContext());
        cursor.moveToNext();
        new NetConnection("", new NetConnection.SuccessCallback() {

            @Override
            public void onSuccess(String result) {
                Toast.makeText(rootView.getContext(), "联网更新成功",
                        Toast.LENGTH_SHORT).show();
                new Sql_data().uptime(rootView.getContext());
                String getinfo[] = result.split(",");
                dataDb db = new dataDb(rootView.getContext());
                SQLiteDatabase dbUpdata = db.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("moneyinfo", getinfo[0]);
                values.put("eleinfo", getinfo[1]);
                moneyinfo.setText(getinfo[0]);
                elecinfo.setText(getinfo[1]);
                timeinfo.setText(Basedata
                        .getTime());
                dbUpdata.update("info", values, "id=?", new String[]{"1"});
            }
        }, new NetConnection.FailCallback() {

            @Override
            public void onFail() {
                Toast.makeText(rootView.getContext(), "查询失败，请检查网络设置",
                        Toast.LENGTH_SHORT).show();

            }
        }, Basedata.val_drxiaoqu[Integer.parseInt(cursor.getString(cursor
                .getColumnIndex("drxiaoqu")))], cursor.getString(cursor
                .getColumnIndex("txtroomid")), cursor.getString(cursor
                .getColumnIndex("drlou")), cursor.getString(cursor
                .getColumnIndex("drxiaoqu")));
    }

}