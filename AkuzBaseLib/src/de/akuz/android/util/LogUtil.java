package de.akuz.android.util;

import android.content.pm.ApplicationInfo;
import android.util.Log;
import de.akuz.android.AkuzApp;

public class LogUtil {

	public static void d(String tag, String message, Throwable reason) {
		if (!isDebug()) {
			return;
		}
		if (reason != null) {
			Log.d(tag, message, reason);
		} else {
			Log.d(tag, message);
		}
	}

	public static void d(String tag, String message) {
		d(tag, message, null);
	}

	public static void d(Object caller, String message, Throwable reason) {
		d(getOjectTag(caller), message, reason);
	}

	public static void d(Object caller, String message) {
		d(getOjectTag(caller), message);
	}

	public static void i(String tag, String message, Throwable reason) {
		if (reason != null) {
			Log.i(tag, message, reason);
		} else {
			Log.i(tag, message);
		}
	}

	public static void i(String tag, String message) {
		i(tag, message, null);
	}

	public static void i(Object caller, String message, Throwable reason) {
		i(getOjectTag(caller), message, reason);
	}

	public static void i(Object caller, String message) {
		i(getOjectTag(caller), message, null);
	}

	public static void e(String tag, String message, Throwable reason) {
		if (reason != null) {
			Log.e(tag, message, reason);
		} else {
			Log.e(tag, message);
		}
	}

	public static void e(String tag, String message) {
		e(tag, message, null);
	}

	public static void e(Object caller, String message, Throwable reason) {
		e(getOjectTag(caller), message, reason);
	}

	public static void e(Object caller, String message) {
		e(getOjectTag(caller), message, null);
	}

	private static String getOjectTag(Object o) {
		if (o != null) {
			return o.getClass().getSimpleName();
		}
		return "Unknown tag";
	}

	private static boolean isDebug() {
		if (AkuzApp.getInstance() != null) {
			boolean isDebuggable = (0 != (AkuzApp.getInstance().getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE));
			return isDebuggable;
		} else
			return true;
	}
}
