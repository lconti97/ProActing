package com.example.proacting;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.learnmylines.R;
import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonRectangle;

public class SceneEditFragment extends Fragment{
	private static final String TAG = "SceneEditFragment";

	private ButtonFloat mRecordButton;
	private ButtonRectangle mCharMeButton;
	private ButtonRectangle mCharNotMeButton;
	private SceneAudioRecorder mRecorder;
	private boolean mRecording = false;
	private String mSpeaker;
	private Scene mScene;
	private ListView mLineList;
	private ArrayAdapter<Line> mLineListAdapter;
	private EditPlayPagerActivity mActivity;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);
				
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
				&& NavUtils.getParentActivityName(getActivity()) != null) {
			((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		EditPlayPagerActivity c = (EditPlayPagerActivity) getActivity();
		mScene = c.getScene();

		View v = inflater.inflate(R.layout.fragment_scene_edit, parent, false);

		mLineList = (ListView)v.findViewById(R.id.fragment_scene_edit_lineList);
		mActivity = (EditPlayPagerActivity)getActivity();
		mLineListAdapter = mActivity.getLineListAdapter();
		mLineList.setAdapter(mLineListAdapter);
		
		mSpeaker = c.getString(R.string.me);

		mRecordButton = (ButtonFloat)v.findViewById(R.id.fragment_scene_edit_buttonBar)
				.findViewById(R.id.button_center);
		mCharMeButton = (ButtonRectangle)v.findViewById(R.id.fragment_scene_edit_buttonBar)
				.findViewById(R.id.button_left);
		mCharNotMeButton = (ButtonRectangle)v.findViewById(R.id.fragment_scene_edit_buttonBar)
				.findViewById(R.id.button_right);
		
		mCharMeButton.setText(getString(R.string.me));
		mCharNotMeButton.setText(getString(R.string.not_me));
		
		mCharMeButton.setBackgroundColor(getResources().getColor(R.color.primary));
		mCharNotMeButton.setBackgroundColor(getResources().getColor(R.color.primary));
		mRecordButton.setBackgroundColor(getResources().getColor(R.color.accent));
		
		
		mCharMeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				v.setSelected(true);
				mSpeaker = getString(R.string.me);
			}
		});
		mCharNotMeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				v.setSelected(true);
				mSpeaker = getString(R.string.not_me);
			}
		});
		
		mCharNotMeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSpeaker = getString(R.string.not_me);
			}
		});
		//if mRecording, the fragment has been rotated
		if(!mRecording)
		{
//			mRecordButton.setText(R.string.record);
		}
		else
		{
//			mRecordButton.setText(R.string.stop);
		}
		mRecordButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!mRecording)
				{
					mRecording = mRecorder.start(mScene, mSpeaker);
					if(mRecording)
					{
//						mRecordButton.setText(R.string.stop);
					}
					else
					{
						Log.i(TAG, "Recording failed to start");
					}
				}
				else
				{
					stopRecording();
				}
			}
		});

		return v;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;
		default: 
			return super.onOptionsItemSelected(item);
		}
	};

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if(mRecording)
		{
			stopRecording();
		}
		mRecorder.release();
		mRecorder = null;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mRecorder = new SceneAudioRecorder();
	}

	private void stopRecording()
	{
		if(mRecorder!=null && mRecording) {
			mRecorder.stop();
			mRecorder.reset();
//			mRecordButton.setText(R.string.record);
			mRecording = false;
			mLineListAdapter.notifyDataSetChanged();
		}
	}
}
