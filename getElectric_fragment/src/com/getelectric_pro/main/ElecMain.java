package com.getelectric_pro.main;

import java.util.ArrayList;
import java.util.List;

import com.example.getelectric_fragment.R;
import com.getelectric_pro.login.Login;
import com.umeng.update.UmengUpdateAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.Toast;

public class ElecMain extends FragmentActivity {

	private Fragment tab_data;
	private Fragment tab_about;

	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mList;

	// @Bind(R.id.tab_content)
	private ViewPager mvPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elec_main);
		UmengUpdateAgent.update(this);
		//ButterKnife.bind(this);
		initView();
		initEvent();
	}

	private void initEvent() {
		mvPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				int currentitem = mvPager.getCurrentItem();
				switch (currentitem) {
				case 0:
//					Toast.makeText(getApplicationContext(), "1",
//							Toast.LENGTH_SHORT).show();
					break;
				case 1:
//					Toast.makeText(getApplicationContext(), "2",
//							Toast.LENGTH_SHORT).show();
					break;
				default:
//					Toast.makeText(getApplicationContext(), "default",
//							Toast.LENGTH_SHORT).show();
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private void initView() {
		mvPager = (ViewPager) findViewById(R.id.tab_content);
		tab_data = new DataMain();
		tab_about = new About();
		mList = new ArrayList<Fragment>();
		mList.add(tab_data);
		mList.add(tab_about);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mList.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mList.get(arg0);
			}
		};

		mvPager.setAdapter(mAdapter);
	}

	public void setSelect(int i) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ts = fm.beginTransaction();
		hideFragment(ts);

		switch (i) {
		case 0:
			if (tab_data == null)
				tab_data = new DataMain();
			else {
				ts.show(tab_data);
			}
			break;

		case 1:
			if (tab_about == null)
				tab_about = new About();
			else {
				ts.show(tab_about);
			}
			break;
		default:
			break;
		}

		ts.commit();
	}

	private void hideFragment(FragmentTransaction ts) {
		if (tab_data != null) {
			ts.hide(tab_data);
		}
		if (tab_about != null) {
			ts.hide(tab_about);
		}
	}
}
