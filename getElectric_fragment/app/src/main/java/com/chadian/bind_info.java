package com.chadian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.chadian.R;
import com.chadian.database.Basedata;
import com.chadian.database.Sql_data;

public class bind_info extends Activity {

    String name_drxiaoqu[] = {"校区", "本部芙蓉区", "本部石井区", "本部南光区", "本部凌云区",
            "本部勤业区", "本部海滨新区", "本部丰庭区", "漳州校区芙蓉园", "海韵学生公寓", "曾厝安学生公寓",
            "漳州校区博学园", "漳州校区囊萤园", "漳州校区笃行园", "漳州校区映雪园", "漳州校区勤业园", "漳州校区若谷园",
            "漳州校区凌云园", "漳州校区丰庭园", "漳州校区南安园", "漳州校区南光园", "漳州校区嘉庚若谷园", "翔安校区芙蓉区",
            "翔安校区南安区", "翔安校区南光区", "翔安国光区", "翔安丰庭区", "翔安笃行区"};

    String val_drxiaoqu[] = {"", "01", "02", "03", "04", "05", "06", "07",
            "26", "08", "09", "21", "22", "23", "24", "25", "27", "28", "29",
            "30", "31", "32", "33", "34", "35", "42", "41", "40"};

    String drlou_num[][] = {{"", "1"}, {"", "1"}, {"", "1"},
            {"", "1"}, {"", "1"}, {"", "1"}, {"", "1"}, {"", "1"},
            {"芙蓉", "5"}, {"", "1"}, {"", "1"}, {"博学", "3"},
            {"囊萤", "3"}, {"笃行", "2"}, {"映雪", "3"}, {"勤业", "7"},
            {"若谷", "1"}, {"凌云", "3"}, {"丰庭", "12"}, {"南安", "7"},
            {"南光", "13"}, {"若谷贰", "若谷叁"}, {"", "1"}, {"", "1"},
            {"", "1"}, {"", "1"}, {"", "1"}, {"", "1"}};

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

        Button btnbind = (Button) findViewById(R.id.xml_btnbind);
        btnbind.setText("保存");
        btnbind.setOnClickListener(
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

        Button bindclose = (Button) findViewById(R.id.xml_btn_bindclose);
        bindclose.setText("关闭");
        bindclose.setOnClickListener(
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
            System.out.println("dd = " + dd);
            result += "0" + (sp_drlou.getSelectedItemPosition() + dd);
        } else {
            result += (sp_drlou.getSelectedItemPosition() + 1);
        }
        result += ((EditText) findViewById(R.id.xml_txtRoomid)).getText();
        // System.out.println(result);

        return result;
    }
}
