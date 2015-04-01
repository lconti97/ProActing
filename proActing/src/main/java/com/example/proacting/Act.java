package com.example.proacting;

import java.util.ArrayList;

public class Act {
	
	private ArrayList<Scene> mScenes;
	private Project mProject;
	private String mTitle;

	
	public Act(String title)
	{
		mTitle = title;
		mScenes = new ArrayList<Scene>();
	}
	
	public void addScene(Scene scene)
	{
		mScenes.add(scene);
		scene.setAct(this);
	}

	public ArrayList<Scene> getScenes() {
		return mScenes;
	}
	
	public ProjectManager getManager()
	{
		return mProject.getManager();
	}

	public Project getProject() {
		return mProject;
	}

	public void setProject(Project project) {
		mProject = project;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public int getSceneIndex(Scene s) {
		for(int i = 0; i < mScenes.size(); i++)
			if(mScenes.get(i).equals(s))
				return i;
		return -1;
	}
	
	
}
