package com.example.proacting;


import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.example.learnmylines.R;

import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class EditPlayPagerActivity extends ActionBarActivity {
    private static final String TAG = "EditPlayPagerActivity";
    final String[] TAB_TITLES = {"edit", "play"};

    private Scene mScene;
    private ViewPager mViewPager;
    private ArrayAdapter<Line> mLineListAdapter;
    private Toolbar mToolbar;
    private PagerSlidingTabStrip mTabs;
    private RecyclerView mRecyclerView;
    private MyAdapter mNavDrawerAdapter;
    private ArrayList<String> mProjectTitles = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createSampleCase();
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mScene = new Scene("Sample");

        mLineListAdapter = new ArrayAdapter<Line>(
                this,
                android.R.layout.simple_list_item_1,
                mScene.getLines());

        setContentView(R.layout.activity_edit_play_pager);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mViewPager = (ViewPager) findViewById(R.id.activity_edit_play_view_pager);
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



            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int pos) {
                if (pos == 0)
                    return new SceneEditFragment();
                else
                    return new ScenePlayFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TAB_TITLES[position];
            }
        });

        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mTabs.setViewPager(mViewPager);

        mRecyclerView = (RecyclerView)findViewById(R.id.Recycler_view);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ColorDrawable cd = new ColorDrawable();
        cd.setColor(getResources().getColor(R.color.accent));

        ArrayList<Project> projects = ProjectManager.get().getProjects();
        for(int i = 0; i < projects.size(); i++) {
            mProjectTitles.add(projects.get(i).getTitle());
        }

        ColorDrawable[] ints = {cd, cd};
        mNavDrawerAdapter = new MyAdapter(mProjectTitles, ints,
                getResources().getString(R.string.my_projects), R.drawable.ic_launcher);
        mRecyclerView.setAdapter(mNavDrawerAdapter);
    }

    public ArrayAdapter<Line> getLineListAdapter() {
        return mLineListAdapter;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void createSampleCase() {
        ProjectManager pm = ProjectManager.get();
        Project rj = new Project("Romeo and Juliet");
        Project frank = new Project("Frankenstein");
        Act rj0 = new Act("Act I");
        Act rj1 = new Act("Act II");
        Act frank0 = new Act("Act I");
        Act frank1 = new Act("Act II");
        Scene rj00 = new Scene("Scene I");
        Scene rj01 = new Scene("Scene II");
        Scene rj10 = new Scene("Scene I");
        Scene frank00 = new Scene("Scene 1");

        pm.addProject(rj);
        pm.addProject(frank);
        rj.addAct(rj0);
        rj.addAct(rj1);
        frank.addAct(frank0);
        frank.addAct(frank1);
        rj0.addScene(rj00);
        rj0.addScene(rj01);
        rj1.addScene(rj10);
        frank0.addScene(frank00);
    }

    public Scene getScene() {
        return mScene;
    }

}
