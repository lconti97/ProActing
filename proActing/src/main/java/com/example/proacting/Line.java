package com.example.proacting;

public class Line {

	private String mFileName;
	private Scene mScene;
	private String mSpeaker;
	
	public String getSpeaker() {
		return mSpeaker;
	}

	public void setSpeaker(String speaker) {
		mSpeaker = speaker;
	}

	public Line(String filename, Scene scene, String speaker)
	{
		mFileName = filename;
		mScene = scene;
		mSpeaker = speaker;
	}

	public String getFileName() {
		return mFileName;
	}

	public void setFileName(String fileName) {
		mFileName = fileName;
	}

	public Scene getScene() {
		return mScene;
	}
	
	public Act getAct()
	{
		return mScene.getAct();
	}
	
	public Project getProject()
	{
		return mScene.getProject();
	}
	
	public ProjectManager getManager()
	{
		return mScene.getManager();
	}

	public void setScene(Scene scene) {
		mScene = scene;
	}
	
	public String toString()
	{
		return mSpeaker;
	}
	
}
