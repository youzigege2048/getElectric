package com.getelectric_pro.main;

import com.example.getelectric_fragment.R;
import com.getelectric_pro.database.Basedata;
import com.getelectric_pro.database.NetConnection;
import com.getelectric_pro.database.Sql_data;
import com.getelectric_pro.database.bind_info;
import com.getelectric_pro.database.dataDb;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class DataMain extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.tab_data, container,
				false);
		// setInfo(rootView);

		refreshInfo(rootView);
		rootView.findViewById(R.id.xml_btn_spinner).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {

						Toast.makeText(rootView.getContext(), "正在查询，请稍等",
								Toast.LENGTH_SHORT).show();
						// refreshInfo(rootView);
						RefreshSql(rootView);
					}
				});

		rootView.findViewById(R.id.xml_btn_bind).setOnClickListener(
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
		((TextView) rootView.findViewById(R.id.xml_tab_drxiaoqu))
				.setText(dataBundle.getString("drxiaoqu"));
		((TextView) rootView.findViewById(R.id.xml_tab_drlou))
				.setText(dataBundle.getString("drlou"));
		((TextView) rootView.findViewById(R.id.xml_tab_txtroomid))
				.setText(dataBundle.getString("txtroomid"));
		((TextView) rootView.findViewById(R.id.xml_tab_moneyinfo))
				.setText(dataBundle.getString("moneyinfo"));
		((TextView) rootView.findViewById(R.id.xml_tab_elecinfo))
				.setText(dataBundle.getString("elecinfo"));
	}

	public void refreshInfo(View rootView) {
		Sql_data sql_data = new Sql_data(rootView.getContext());
		// sql_data.RefreshSql(rootView.getContext());
		if (sql_data.isDb(rootView.getContext())) {
			Cursor cursor = sql_data.getInfo(rootView.getContext());

			while (cursor.moveToNext()) {
				((TextView) rootView.findViewById(R.id.xml_code))
						.setText(cursor.getString(cursor.getColumnIndex("code")));
				((TextView) rootView.findViewById(R.id.xml_tab_drxiaoqu))
						.setText(Basedata.name_drxiaoqu[Integer.parseInt(cursor
								.getString(cursor.getColumnIndex("drxiaoqu")))]);
				((TextView) rootView.findViewById(R.id.xml_tab_drlou))
						.setText(cursor.getString(cursor
								.getColumnIndex("drlou")));
				((TextView) rootView.findViewById(R.id.xml_tab_txtroomid))
						.setText(cursor.getString(cursor
								.getColumnIndex("txtroomid")));
				((TextView) rootView.findViewById(R.id.xml_tab_moneyinfo))
						.setText(cursor.getString(cursor
								.getColumnIndex("moneyinfo")));
				((TextView) rootView.findViewById(R.id.xml_tab_elecinfo))
						.setText(cursor.getString(cursor
								.getColumnIndex("eleinfo")));
				((TextView) rootView.findViewById(R.id.xml_tab_timeinfo))
						.setText(cursor.getString(cursor.getColumnIndex("time")));
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
				((TextView) rootView.findViewById(R.id.xml_tab_moneyinfo))
						.setText(getinfo[0]);
				((TextView) rootView.findViewById(R.id.xml_tab_elecinfo))
						.setText(getinfo[1]);
				((TextView) rootView.findViewById(R.id.xml_tab_timeinfo))
						.setText(com.getelectric_pro.database.Basedata
								.getTime());
				dbUpdata.update("info", values, "id=?", new String[] { "1" });
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
