package com.example.proacting;

import java.io.IOException;

import android.media.MediaPlayer;
import android.util.Log;

public class SceneAudioPlayer extends MediaPlayer {
	private static final String TAG = "SceneAudioPlayer";

	private boolean mPaused;

	public SceneAudioPlayer()
	{
		mPaused = false;
	}

	public void playLine(Line line)
	{
		if(!mPaused)
		{
			try
			{
				reset();
				setDataSource(line.getFileName());
				prepare();
				start();
			}
			catch(IOException e)
			{
				Log.e(TAG, "prepare() failed", e);
			}
		}
		else
		{
			start();
			mPaused = false;
		}

	}

	@Override
	public void pause()
	{
		super.pause();
		mPaused = true;
	}
}
