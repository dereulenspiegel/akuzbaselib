package de.akuz.android.app;

import android.util.AndroidException;

/**
 * Base class for exceptions which should contain a resource identifier for the
 * error message and probably some message parameters. This can be useful to
 * present an error message to the user.
 * 
 * @author Till Klocke
 * 
 */
public class AkuzException extends AndroidException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3266745853305712904L;

	private int messageId = -1;

	private Object[] messageParameters;

	public AkuzException(Exception cause) {
		super(cause);
	}

	public AkuzException(int messageId, Exception cause) {
		this(cause);
		this.messageId = messageId;
	}

	public AkuzException(int messageId) {
		this.messageId = messageId;
	}

	public int getMessageId() {
		return messageId;
	}

	public Object[] getMessageParameters() {
		return messageParameters;
	}

	public void setMessageParameters(Object[] messageParameters) {
		this.messageParameters = messageParameters;
	}

}
