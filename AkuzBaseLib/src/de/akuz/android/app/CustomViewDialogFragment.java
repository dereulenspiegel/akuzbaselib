package de.akuz.android.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public abstract class CustomViewDialogFragment extends DialogFragment {

	protected View rootView;

	protected LayoutInflater inflater;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		inflater = getActivity().getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		buildUi(builder);
		builder.setView(rootView);
		return builder.create();
	}

	protected abstract void buildUi(AlertDialog.Builder builder);

	public void setContentView(int layoutId) {
		rootView = inflater.inflate(layoutId, null);
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T findViewById(int id) {
		return (T) rootView.findViewById(id);
	}

}
