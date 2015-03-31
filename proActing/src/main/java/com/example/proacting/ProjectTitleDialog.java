package com.example.proacting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

import com.example.learnmylines.R;

public class ProjectTitleDialog extends DialogFragment {
	public static final String EXTRA_TITLE = 
			"com.starlightstudios.learnmylines.title";
	
	private EditText mTitleEditText;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_title, null);
		
		mTitleEditText = (EditText)v.findViewById(R.id.dialog_project_title_editText);
		
		return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(R.string.project_title)
			.setPositiveButton(
					android.R.string.ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sendResult(Activity.RESULT_OK);
						}
					})
			.create();
	}
	
	private void sendResult(int result)
	{
		if(getTargetFragment() == null)
			return;
		
		Intent i = new Intent();
		i.putExtra(EXTRA_TITLE, mTitleEditText.getText().toString());
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), result, i);
	}
}
