package de.akuz.android.app;

import android.graphics.drawable.Drawable;

public class ErrorMessage {

	private String message;
	private Drawable icon;

	public String getMessageId() {
		return message;
	}

	public void setMessageId(String message) {
		this.message = message;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

}
