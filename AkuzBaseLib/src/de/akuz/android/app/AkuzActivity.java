package de.akuz.android.app;

import static de.akuz.android.util.LogUtil.e;

import java.text.MessageFormat;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import de.akuz.android.R;

/**
 * Base class for all Activities. This class gives you some convenience methods.
 * Currently it simplifies
 * <ul>
 * <li>retrieval of {@link View} objects and casting them to the appropriate
 * class</li>
 * <li>Showing a message dialog for exceptions we encountered and creating the
 * most meaningful message we can come up with</li>
 * </ul>
 * 
 * @author Till Klocke
 * 
 */
public class AkuzActivity extends SherlockFragmentActivity implements OnClickListener {

	public final static int INVALID_IDENTIFIER = 0;

	private final static String EXCEPTION_DIALOG_TAG = "de.akuz.EXCEPTION_DIALOG";
	private final static String ERROR_CONTAINER_NAME = "errorContainer";

	private ViewGroup errorContainer;

	/**
	 * Convenience method to retrieve {@link View} objects from the layout and
	 * automatically cast the to the correct type
	 * 
	 * @param id
	 *            integer identifier for the view to retrieve
	 * @return a {@link View} object and cast it to the appropriate class
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T findView(int id) {
		return (T) super.findViewById(id);
	}

	/**
	 * Handle an Exception. This method tries to create the most useful message
	 * dialog for the given Exception. In case of an {@link AkuzException} it
	 * tries to get message String via the given integer message id and format
	 * it with the given message parameters. For other exceptions or if no
	 * message id is given it creates a default message containing the simple
	 * class name of the exception and the message.
	 * 
	 * @param exception
	 */
	public void handleException(final Exception exception) {
		e(this, "Unexpected exception occured", exception);
		final String message = resolveErrorMessage(exception);
		final ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessageId(message);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				showErrorMessage(errorMessage);

			}
		});
	}

	/**
	 * Tries to resolve the error message for the given message id and format it
	 * with the give message parameters. MessageFormat is used to format the
	 * message. If this fails the {@link AkuzException} is handled like a normal
	 * Exception.
	 * 
	 * @param exception
	 *            The Exception to create a meaningful error message for
	 * @return The most meaningful message we can come up with
	 */
	public String resolveErrorMessage(AkuzException exception) {
		Object[] messageParams = exception.getMessageParameters();
		int messageId = exception.getMessageId();
		String message = null;
		if (messageId != INVALID_IDENTIFIER) {
			message = getString(messageId);
		} else {
			return resolveErrorMessage((Exception) exception);
		}
		if (messageParams != null && messageParams.length > 0) {
			message = MessageFormat.format(message, messageParams);
		}

		return message;
	}

	/**
	 * Creates an error message for generic Exceptions. First we try create an
	 * integer identifier from the given message, and lookup the message in the
	 * resources. If this fails we fall back to a default message containing the
	 * Exceptions simple class name and its message.
	 * 
	 * @param e
	 *            the Exception to create a meaningful message for.
	 * @return the most meaningful message we can come up with
	 */
	public String resolveErrorMessage(Exception e) {
		int messageId = getStringIdentifierFromMessage(e.getMessage());
		if (messageId != INVALID_IDENTIFIER) {
			return getString(messageId);
		}
		String message = getString(R.string.default_error_message);
		message = MessageFormat.format(message, e.getClass().getSimpleName(), e.getMessage());
		return message;
	}

	/**
	 * Convenience method to get an integer identifier for strings by the name
	 * of string resource
	 * 
	 * @param message
	 *            the name of the string resource we need the identifier for
	 * @return a valid resource identifier or 0
	 */
	public int getStringIdentifierFromMessage(String message) {
		if (message != null) {
			return getResources().getIdentifier(message, "string", getPackageName());
		}
		return INVALID_IDENTIFIER;
	}

	public boolean showErrorMessage(ErrorMessage message) {
		if (errorContainer == null) {
			int id = getResources().getIdentifier(ERROR_CONTAINER_NAME, "id", getPackageName());
			errorContainer = findView(id);
			if (errorContainer == null) {
				DefaultMessageDialogFragment dialog = DefaultMessageDialogFragment.create(message.getMessageId(), INVALID_IDENTIFIER,
						R.string.default_error_title);
				dialog.show(getSupportFragmentManager(), EXCEPTION_DIALOG_TAG);
				return true;
			}
		}

		errorContainer.removeAllViews();
		errorContainer.setOnClickListener(this);
		ErrorView view = new ErrorView(this, message);
		errorContainer.addView(view);
		errorContainer.setVisibility(View.VISIBLE);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (errorContainer != null && v.getId() == errorContainer.getId()) {
			errorContainer.setVisibility(View.GONE);
		}

	}
}
