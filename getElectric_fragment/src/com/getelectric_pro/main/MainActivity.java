package com.getelectric_pro.main;

import com.example.getelectric_fragment.R;
import com.getelectric_pro.database.Sql_data;
import com.getelectric_pro.database.bind_info;
import com.getelectric_pro.login.Login;

import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.bind_login);
//		Sql_data sql_data = new Sql_data();
//		if (sql_data.isDb(this)) {
			Intent i = new Intent(this, ElecMain.class);
			startActivity(i);
//		} else {
//			Intent i = new Intent(this, bind_info.class);
//			i.putExtra("frist", "frist");
//			startActivity(i);
//		}
		this.finish();
	}
}
