package de.akuz.android.app;

import de.akuz.android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Very simple implementation of an message dialog. By default this is
 * configured as a message dialog for exceptions using a default title and a
 * default icon. There is only one positive with the text "Ok" wich dismisses
 * this dialog.
 * 
 * @author Till Klocke
 * 
 */
public class DefaultMessageDialogFragment extends DialogFragment implements OnClickListener {

	private final static String ARGUMENT_MESSAGE = "de.akuz.MESSAGE";
	private final static String ARGUMENT_ICON = "de.akuz.ICON";
	private final static String ARGUMENT_TITLE = "de.akuz.TITLE";

	/**
	 * Creates a new dialog with the given message, icon and title.
	 * 
	 * @param message
	 *            the message to show as {@link String}.
	 * @param iconId
	 *            a resource identifier for the icon to use.
	 * @param titleId
	 *            a resource identifier for the title to use.
	 * @return a {@link DefaultMessageDialogFragment} configured with the given
	 *         parameters
	 */
	public static DefaultMessageDialogFragment create(String message, int iconId, int titleId) {
		DefaultMessageDialogFragment fragment = new DefaultMessageDialogFragment();
		Bundle args = new Bundle();
		args.putString(ARGUMENT_MESSAGE, message);
		args.putInt(ARGUMENT_ICON, iconId);
		args.putInt(ARGUMENT_TITLE, titleId);
		fragment.setArguments(args);
		return fragment;
	}

	/**
	 * Convenience method to create a message dialog for errors with the given
	 * message
	 * 
	 * @param message
	 *            the message to show.
	 * @return a {@link DefaultMessageDialogFragment} configured as an error
	 *         message dialog.
	 */
	public static DefaultMessageDialogFragment create(String message) {
		return create(message, AkuzActivity.INVALID_IDENTIFIER, AkuzActivity.INVALID_IDENTIFIER);
	}

	private String message;

	private int iconId = AkuzActivity.INVALID_IDENTIFIER;
	private int titleId = AkuzActivity.INVALID_IDENTIFIER;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		message = args.getString(ARGUMENT_MESSAGE);
		iconId = args.getInt(ARGUMENT_ICON);
		titleId = args.getInt(ARGUMENT_TITLE);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setIcon(iconId != AkuzActivity.INVALID_IDENTIFIER ? iconId : android.R.drawable.ic_dialog_alert);
		builder.setMessage(message);
		builder.setTitle(titleId != AkuzActivity.INVALID_IDENTIFIER ? titleId : R.string.default_error_title);
		builder.setPositiveButton(android.R.string.ok, this);
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int buttonId) {
		// Just dismiss the dialog
		dialog.dismiss();
	}

}
