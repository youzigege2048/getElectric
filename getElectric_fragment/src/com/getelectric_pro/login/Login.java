package com.getelectric_pro.login;

import com.example.getelectric_fragment.R;
import com.getelectric_pro.main.ElecMain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class Login extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//ButterKnife.bind(this);
		findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(),ElecMain.class));
				
			}
		});
		
		findViewById(R.id.a_login_about).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(),login_about.class));
			}
		});
	}
}
