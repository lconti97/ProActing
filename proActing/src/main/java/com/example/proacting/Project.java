package com.example.proacting;

import java.util.ArrayList;

public class Project {

	private ProjectManager mManager;
	private ArrayList<Act> mActs;
	private String mTitle;
	
	public Project(String title)
	{
		mTitle = title;
		mActs = new ArrayList<Act>();
	}
	
	public void addAct(Act act)
	{
		mActs.add(act);
		act.setProject(this);
	}

	public ProjectManager getManager() {
		return mManager;
	}

	public void setManager(ProjectManager manager) {
		mManager = manager;
	}

	public ArrayList<Act> getActs() {
		return mActs;
	}

	public void setActs(ArrayList<Act> acts) {
		mActs = acts;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}
	
	@Override
	public String toString()
	{
		return mTitle;
	}

	public int getActIndex(Act act) {
		for(int i = 0; i < mActs.size(); i ++)
			if(mActs.get(i).equals(act))
				return i;
		return -1;
	}
	
	
}
