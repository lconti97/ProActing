package com.example.proacting;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.example.learnmylines.R;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class EditPlayPagerActivity extends ActionBarActivity {
	private static final String TAG = "EditPlayPagerActivity";

	private Scene mScene;
	private ViewPager mViewPager;
	private ArrayAdapter<Line> mLineListAdapter;
	private Toolbar mToolbar;
	private PagerSlidingTabStrip mTabs;

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

		mToolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		mViewPager = (ViewPager)findViewById(R.id.activity_edit_play_view_pager);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int pos) {
//				getSupportActionBar().setSelectedNavigationItem(pos);
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

			String[] TITLES = {"edit", "play"};

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

			@Override
			public CharSequence getPageTitle(int position) {
				return TITLES[position];
			}
		});

		mTabs = (PagerSlidingTabStrip)findViewById(R.id.tabs);
		mTabs.setViewPager(mViewPager);
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
