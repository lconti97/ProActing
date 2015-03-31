package com.example.proacting;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.learnmylines.R;

public class ProjectListFragment extends Fragment {
	private static final String TAG = "ProjectListFragment";
	private static final String DIALOG_TITLE = "title";
	private static final int REQUEST_TITLE = 0;
	public static final String EXTRA_SCENE_INDEX = 
			"com.starlightstudios.learnmylines.scene_index";

	private ArrayList<NLevelItem> mList;
	private ListView mListView;
	private ProjectManager mManager;
	private NLevelAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup vg, 
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_project_list, null);

		//TODO: Change listView1's name
		mListView = (ListView)v.findViewById(R.id.listView1);
		mManager = ProjectManager.get();

		updateList();


		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				NLevelItem temp = (NLevelItem) mAdapter.filtered.get(arg2);
				//if a scene is clicked, launch EditPlayPagerActivity
				if (temp.getWrappedObject() instanceof Scene)
				{
					Scene s = (Scene) temp.getWrappedObject();
					Intent i = new Intent(getActivity(), EditPlayPagerActivity.class);
					int projectIndex = mManager.getProjectIndex(s.getProject());
					int actIndex = s.getProject().getActIndex(s.getAct());
					int sceneIndex = s.getAct().getSceneIndex(s);
					int[] index = {projectIndex, actIndex, sceneIndex};
					i.putExtra(EXTRA_SCENE_INDEX, index);
					getActivity().startActivity(i);
				}
				mAdapter.toggle(arg2);
				mAdapter.getFilter().filter();

			}
		});
		return v;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_project_list, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.menu_item_new_project:
			FragmentManager fm = getActivity()
			.getSupportFragmentManager();
			ProjectTitleDialog dialog = new ProjectTitleDialog();
			dialog.setTargetFragment(ProjectListFragment.this, REQUEST_TITLE);
			dialog.show(fm, DIALOG_TITLE);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(resultCode != Activity.RESULT_OK) return;
		if(requestCode == REQUEST_TITLE)
		{
			Project p = new Project(
					data.getStringExtra(ProjectTitleDialog.EXTRA_TITLE));
			mManager.addProject(p);
			Act a = new Act("Act I");
			p.addAct(a);
			Scene s = new Scene("Scene I");
			a.addScene(s);
			
			updateList();
		}
	}

	private void updateList()
	{
		mList = new ArrayList<NLevelItem>();
		for(int i = 0; i < mManager.getProjects().size(); i++)
		{
			final Project project = mManager.getProjects().get(i);
			final NLevelItem grandParent = new NLevelItem(
					project,
					null,
					new NLevelView() {

						@Override
						public View getView(NLevelItem item) {
							//TODO: find a different list item style
							View view = getActivity().getLayoutInflater()
									.inflate(R.layout.list_item, null);
							TextView tv = (TextView) view.findViewById(R.id.textView);
							String name = project.getTitle();
							tv.setText(name);
							tv.setTypeface(Typeface.DEFAULT_BOLD);
							return view;
						}
					});		
			mList.add(grandParent);

			ArrayList<Act> acts = project.getActs();
			for(int j = 0; j < acts.size(); j++)
			{
				final Act act = acts.get(j);
				NLevelItem parent = new NLevelItem(
						act,
						grandParent,
						new NLevelView() {

							@Override
							public View getView(NLevelItem item) {
								View view = getActivity().getLayoutInflater().
										inflate(R.layout.list_item, null);
								TextView tv = (TextView) view.findViewById(R.id.textView);
								String name = act.getTitle();
								tv.setText("\t\t" + name);
								return view;
							}
						});

				mList.add(parent);

				ArrayList<Scene> scenes = act.getScenes();
				for(int n = 0; n < scenes.size(); n++)
				{
					final Scene scene = scenes.get(n);
					NLevelItem child = new NLevelItem(
							scene,
							parent,
							new NLevelView() {

								@Override
								public View getView(NLevelItem item) {
									View view = getActivity().getLayoutInflater()
											.inflate(R.layout.list_item, null);
									TextView tv = (TextView) view.findViewById(R.id.textView);
									String name = scene.getTitle();
									tv.setText("\t\t\t\t" + name);
									return view;
								}
							});

					mList.add(child);
				}
			}

		}
		
		mAdapter = new NLevelAdapter(mList);
		mListView.setAdapter(mAdapter);
	}
}
