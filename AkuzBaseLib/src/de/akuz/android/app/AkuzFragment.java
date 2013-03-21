package de.akuz.android.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * Base class for all Fragment with some concenience. Currenty this class
 * simplifies
 * <ul>
 * <li>Retrieval of {@link View} objects by id and casting them to the
 * appropriate class</li>
 * <li>Building the ui and mimicing the lifecycle of an {@link Activity}</li>
 * </ul>
 * 
 * @author Till Klocke
 * 
 */
public abstract class AkuzFragment extends SherlockFragment implements OnClickListener {

	private View root;
	private LayoutInflater inflater;
	private ViewGroup container;
	private ViewGroup errorContainer;

	private final static String ERROR_CONTAINER_NAME = "errorContainer";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!(getActivity() instanceof AkuzActivity)) {
			throw new AndroidRuntimeException("The activity MUST inherit from " + AkuzActivity.class.getCanonicalName());
		}
	}

	/**
	 * This method is final here because we should use buildUi.
	 */
	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		buildUi();
		return root;
	}

	/**
	 * Load the {@link View} with the given id from the layout. This method does
	 * also cast the {@link View} to the appropriate class.
	 * 
	 * @param id
	 *            resource identifier of the {@link View} you want to retrieve
	 * @return A {@link View} object casted to the appropriate class
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T findView(int id) {
		return (T) root.findViewById(id);
	}

	/**
	 * convenience method to mimic an {@link Activity}. This method simply
	 * inflates an {@link View} with a {@link LayoutInflater} and sets this as
	 * the root view which is later returned by onCreateView.
	 * 
	 * @param id
	 */
	public void setContentView(int id) {
		root = inflater.inflate(id, container);
	}

	/**
	 * Instead of using onCreateView use this method to build the ui. In here it
	 * is possible to use setContentView to load the layout
	 */
	public abstract void buildUi();

	public AkuzActivity getAkuzActivity() {
		return (AkuzActivity) getActivity();
	}

	/**
	 * Convenience method to handle Exceptions by showing a message dialog. This
	 * method simply calls handleException of the class {@link AkuzActivity}.
	 * 
	 * @param e
	 *            The Exception to show a message for.
	 */
	public void handleException(Exception e) {
		getAkuzActivity().handleException(e);
	}

	protected void showErrorMessage(ErrorMessage message) {
		if (getActivity() instanceof AkuzActivity) {
			((AkuzActivity) getActivity()).showErrorMessage(message);
		} else {
			showMessageInFragment(message);
		}
	}

	private void showMessageInFragment(ErrorMessage message) {
		if (errorContainer == null) {
			int id = getResources().getIdentifier(ERROR_CONTAINER_NAME, "id", getActivity().getPackageName());
			errorContainer = findView(id);
			if (errorContainer == null) {
				// TODO fall back to dialog
				return;
			}
		}
		errorContainer.removeAllViews();
		errorContainer.setOnClickListener(this);
		ErrorView view = new ErrorView(getActivity(), message);
		errorContainer.addView(view);
		errorContainer.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		if (errorContainer != null && v.getId() == errorContainer.getId()) {
			errorContainer.setVisibility(View.GONE);
		}

	}

}
