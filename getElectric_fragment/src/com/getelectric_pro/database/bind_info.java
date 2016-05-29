package com.getelectric_pro.database;

import java.sql.SQLData;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.getelectric_fragment.R;
import com.getelectric_pro.main.ElecMain;

import android.R.xml;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class bind_info extends Activity {

	String name_drxiaoqu[] = { "У��", "����ܽ����", "����ʯ����", "�����Ϲ���", "����������",
			"������ҵ��", "������������", "������ͥ��", "����У��ܽ��԰", "����ѧ����Ԣ", "���Ȱ�ѧ����Ԣ",
			"����У����ѧ԰", "����У����ө԰", "����У������԰", "����У��ӳѩ԰", "����У����ҵ԰", "����У������԰",
			"����У������԰", "����У����ͥ԰", "����У���ϰ�԰", "����У���Ϲ�԰", "����У���θ�����԰", "�谲У��ܽ����",
			"�谲У���ϰ���", "�谲У���Ϲ���", "�谲������", "�谲��ͥ��", "�谲������" };

	String val_drxiaoqu[] = { "", "01", "02", "03", "04", "05", "06", "07",
			"26", "08", "09", "21", "22", "23", "24", "25", "27", "28", "29",
			"30", "31", "32", "33", "34", "35", "42", "41", "40" };

	String drlou_num[][] = { { "", "1" }, { "", "1" }, { "", "1" },
			{ "", "1" }, { "", "1" }, { "", "1" }, { "", "1" }, { "", "1" },
			{ "ܽ��", "5" }, { "", "1" }, { "", "1" }, { "��ѧ", "3" },
			{ "��ө", "3" }, { "����", "2" }, { "ӳѩ", "3" }, { "��ҵ", "7" },
			{ "����", "1" }, { "����", "3" }, { "��ͥ", "12" }, { "�ϰ�", "7" },
			{ "�Ϲ�", "13" }, { "���ȷ�", "������" }, { "", "1" }, { "", "1" },
			{ "", "1" }, { "", "1" }, { "", "1" }, { "", "1" } };

	// <option selected="selected" value="">У��</option>
	// <option value="01">����ܽ����</option>
	// <option value="02">����ʯ����</option>
	// <option value="03">�����Ϲ���</option>
	// <option value="04">����������</option>
	// <option value="05">������ҵ��</option>
	// <option value="06">������������</option>
	// <option value="07">������ͥ��</option>
	// <option value="26">����У��ܽ��԰</option>
	// <option value="08">����ѧ����Ԣ</option>
	// <option value="09">���Ȱ�ѧ����Ԣ</option>
	// <option value="21">����У����ѧ԰</option>
	// <option value="22">����У����ө԰</option>
	// <option value="23">����У������԰</option>
	// <option value="24">����У��ӳѩ԰</option>
	// <option value="25">����У����ҵ԰</option>
	// <option value="27">����У������԰</option>
	// <option value="28">����У������԰</option>
	// <option value="29">����У����ͥ԰</option>
	// <option value="30">����У���ϰ�԰</option>
	// <option value="31">����У���Ϲ�԰</option>
	// <option value="32">����У���θ�����԰</option>
	// <option value="33">�谲У��ܽ����</option>
	// <option value="34">�谲У���ϰ���</option>
	// <option value="35">�谲У���Ϲ���</option>
	// <option value="42">�谲������</option>
	// <option value="41">�谲��ͥ��</option>
	// <option value="40">�谲������</option>

	Spinner sp_drxiaoqu = null;
	Spinner sp_drlou = null;
	int drlouPositon = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bind_login);
		initView();
		initListener();
	}

	public void initView() {

		sp_drxiaoqu = (Spinner) findViewById(R.id.xml_drxiaoqu);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, name_drxiaoqu);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_drxiaoqu.setAdapter(adapter);
		sp_drlou = (Spinner) findViewById(R.id.xml_drlou);
		Sql_data sql_data = new Sql_data(this);
		if (sql_data.isDb(this)) {
			sp_drxiaoqu.setSelection(Integer.parseInt(sql_data.getDrxiaoqu()),
					true);
			drlouPositon = Integer.parseInt(sql_data.getDrxiaoqu());
			setDrlouAdater(Integer.parseInt(sql_data.getDrxiaoqu()));
			sp_drlou.setSelection(
					getdrlouId(sql_data.getDrlou(),
							Integer.parseInt(sql_data.getDrxiaoqu())), true);
			((EditText) findViewById(R.id.xml_txtRoomid)).setText(sql_data
					.getTxtroomid());
		}

	}

	public void initListener() {
		sp_drxiaoqu.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				drlouPositon = arg2;
				setDrlouAdater(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		findViewById(R.id.xml_btnbind).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						String drxiaoqu = sp_drxiaoqu.getSelectedItemId() + "";
						drlouPositon = (int) sp_drxiaoqu.getSelectedItemId();
						String drlou = sp_drlou.getSelectedItem() + "";
						String txtroomid = ((EditText) findViewById(R.id.xml_txtRoomid))
								.getText() + "";
						bindInfo(drxiaoqu, drlou, txtroomid, getCode());
					}
				});

		findViewById(R.id.xml_btn_bindclose).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						close();
					}
				});
	}

	public void close() {
		this.finish();
	}

	public void bindInfo(String drxiaoqu, String drlou, String txtroomid,
			String code) {
		Intent intent = new Intent();
		Sql_data sql_data = new Sql_data(this);
		sql_data.bindinfo(this, drxiaoqu, drlou, txtroomid, code);
		// this.getParent().notify();
		// Toast.makeText(this, "�󶨳ɹ�����ˢ��", Toast.LENGTH_SHORT).show();
		// startActivity(intent);
		// this.getParent().finish();
		intent.putExtra("bindsuccess", 111);
		setResult(20, intent);
		this.finish();
	}

	public void setDrlouAdater(int arg2) {
		ArrayAdapter<String> adapter = null;
		if (arg2 != 21) {
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, getLinkName(
							drlou_num[arg2][0],
							Integer.parseInt(drlou_num[arg2][1])));
		} else {

			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, drlou_num[21]);
		}
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_drlou.setAdapter(adapter);
	}

	public static String[] getLinkName(String top, int len) {
		String result[] = new String[len];

		for (int i = 0; i < len; i++) {
			result[i] = top + (i + 1);
		}

		return result;
	}

	public int getdrlouId(String name, int arg) {
		int i = 0;
		String drlouName[] = null;
		if (arg == 21) {
			drlouName = drlou_num[arg];
		} else {
			drlouName = getLinkName(drlou_num[arg][0],
					Integer.parseInt(drlou_num[arg][1]));
		}
		for (String keyid : drlouName) {
			if (name.equals(keyid))
				return i;
			i++;
		}
		return 0;
	}

	public String getCode() {
		String result = "";
		result += Basedata.val_drxiaoqu[(int) sp_drxiaoqu.getSelectedItemId()];
		if (sp_drlou.getSelectedItemPosition() <= 9) {
			int dd = drlouPositon == 21 ? 2 : 1;
			System.out.println("dd = "+dd);
			result += "0" + (sp_drlou.getSelectedItemPosition() + dd);
		} else {
			result += (sp_drlou.getSelectedItemPosition() + 1);
		}
		result += ((EditText) findViewById(R.id.xml_txtRoomid)).getText();
		// System.out.println(result);

		return result;
	}
}
