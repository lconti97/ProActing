package com.example.proacting;



import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.learnmylines.R;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class EditPlayPagerActivity extends ActionBarActivity {
	private static final String TAG = "EditPlayPagerActivity";

	private Scene mScene;
	private ViewPager mViewPager;
	private ArrayAdapter<Line> mLineListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		mScene = new Scene("Sample");
		
		mLineListAdapter = new ArrayAdapter<Line>(
				this, 
				android.R.layout.simple_list_item_1,
				mScene.getLines());

		setContentView(R.layout.activity_edit_play_pager);
		mViewPager = (ViewPager)findViewById(R.id.activity_edit_play_view_pager);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int pos) {
				getSupportActionBar().setSelectedNavigationItem(pos);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentPagerAdapter(fm) {

			@Override
			public int getCount() {
				return 2;
			}

			@Override
			public Fragment getItem(int pos) {
				if(pos == 0)
					return new SceneEditFragment();
				else
					return new ScenePlayFragment();
			}
		});

	
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.TabListener tabListener = new TabListener() {

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {

			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {

			}
		};
		actionBar.addTab(
				actionBar.newTab() 
				.setText(R.string.edit)
				.setTabListener(tabListener));

		actionBar.addTab(
				actionBar.newTab() 
				.setText(R.string.play)
				.setTabListener(tabListener));

	}
	
	public ArrayAdapter<Line> getLineListAdapter()
	{
		return mLineListAdapter;
	}
	
	public ViewPager getViewPager() {
		return mViewPager;
	}
	
	public Scene getScene() {
		return mScene;
	}

}
