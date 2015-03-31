package com.example.proacting;

import java.util.ArrayList;

public class ProjectManager {

	private ArrayList<Project> mProjects;
	private static ProjectManager sManager;
	
	private ProjectManager()
	{
		mProjects = new ArrayList<Project>();
	}
	
	public static ProjectManager get()
	{
		if (sManager == null)
		{
			sManager = new ProjectManager();
		}
		return sManager;
	}
	
	public void addProject(Project project)
	{
		mProjects.add(project);
		project.setManager(this);
	}
	
	public ArrayList<Project> getProjects()
	{
		return mProjects;
	}
	
	public int getProjectIndex(Project project)
	{
		for(int i = 0; i < mProjects.size(); i++)
		{
			if(mProjects.get(i).equals(project))
			{
				return i;
			}
		}
		return -1;
	}
			
}
