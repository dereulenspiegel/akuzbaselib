package de.akuz.android.accounts;

import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.os.Bundle;
import de.akuz.android.app.AkuzActivity;

public abstract class AccountAuthenticatorFragmentActivity extends AkuzActivity {

	private AccountAuthenticatorResponse mAccountAuthenticatorResponse = null;
	private Bundle mResultBundle = null;

	/**
	 * Set the result that is to be sent as the result of the request that
	 * caused this Activity to be launched. If result is null or this method is
	 * never called then the request will be canceled.
	 * 
	 * @param result
	 *            this is returned as the result of the
	 *            AbstractAccountAuthenticator request
	 */
	public final void setAccountAuthenticatorResult(Bundle result) {
		mResultBundle = result;
	}

	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		mAccountAuthenticatorResponse = getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);

		if (mAccountAuthenticatorResponse != null) {
			mAccountAuthenticatorResponse.onRequestContinued();
		}
	}

	/**
	 * Sends the result or a Constants.ERROR_CODE_CANCELED error if a result
	 * isn't present.
	 */
	public void finish() {
		if (mAccountAuthenticatorResponse != null) {
			// send the result bundle back if set, otherwise send an error.
			if (mResultBundle != null) {
				mAccountAuthenticatorResponse.onResult(mResultBundle);
			} else {
				mAccountAuthenticatorResponse.onError(AccountManager.ERROR_CODE_CANCELED, "canceled");
			}
			mAccountAuthenticatorResponse = null;
		}
		super.finish();
	}

}
