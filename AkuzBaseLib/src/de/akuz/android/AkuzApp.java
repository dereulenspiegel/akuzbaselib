package de.akuz.android;

import android.app.Application;

public class AkuzApp extends Application {

	private static AkuzApp instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static AkuzApp getInstance() {
		return instance;
	}

}
