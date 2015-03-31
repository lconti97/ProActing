package com.example.proacting;

import android.support.v4.app.Fragment;

public class ProjectListActivity extends SingleFragmentActivity {
	public static final ProjectManager sManager = ProjectManager.get();
	public static final Project sampleProjectOne = new Project("Romeo and Juliet");
	public static final Project sampleProjectTwo = new Project("Frankenstein");
	public static final Act sampleRJActOne = new Act("Act I");
	public static final Act sampleRJActTwo = new Act("Act II");
	public static final Scene sampleRJSceneOne = new Scene("Scene I");
	public static final Scene sampleRJSceneTwo = new Scene("Scene II");
	
	@Override
	protected Fragment createFragment() {
		createSampleCase();
		return new ProjectListFragment();
	}

	private void createSampleCase()
	{
		ProjectManager manager = ProjectManager.get();
		manager.addProject(sampleProjectOne);
		manager.addProject(sampleProjectTwo);
		sampleProjectOne.addAct(sampleRJActOne);
		sampleProjectOne.addAct(sampleRJActTwo);
		sampleRJActOne.addScene(sampleRJSceneOne);
		sampleRJActOne.addScene(sampleRJSceneTwo);
	}
}
