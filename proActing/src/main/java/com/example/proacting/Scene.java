package com.example.proacting;

import java.io.Serializable;
import java.util.ArrayList;

public class Scene implements Serializable{
	private String mTitle;
	private ArrayList<Line> mLines;
	private Scene.OnDataChangedListener mListener;
	private Act mAct;
	private ArrayList<String> mSpeakers;
	
	public Scene(String title)
	{
		mTitle = title;
		mLines = new ArrayList<Line>();
		mSpeakers = new ArrayList<String>();
	}
	
	public void setAct(Act act)
	{
		mAct = act;
	}
	
	public void addLine(Line line)
	{
		mLines.add(line);
		line.setScene(this);
		if(mListener != null)
		{
			mListener.onDataChanged(mLines);
		}
	}

	public ArrayList<Line> getLines() {
		return mLines;
	}
	
	public void setOnDataChangedListener(OnDataChangedListener l)
	{
		mListener = l;
	}
	
	public interface OnDataChangedListener {
		
		public void onDataChanged(ArrayList<Line> lines);
	}
	
	public Act getAct()
	{
		return mAct;
	}
	
	public Project getProject()
	{
		return mAct.getProject();
	}
	
	public ArrayList<String> getSpeakers()
	{
		return mSpeakers;
	}
	
	public void addSpeaker(String name)
	{
		mSpeakers.add(name);
	}
	
	public ProjectManager getManager()
	{
		return mAct.getManager();
	}
	
	public String getTitle()
	{
		return mTitle;
	}
	

}