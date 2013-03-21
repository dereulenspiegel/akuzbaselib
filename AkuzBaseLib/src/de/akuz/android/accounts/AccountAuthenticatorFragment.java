package de.akuz.android.accounts;

import android.os.Bundle;
import de.akuz.android.app.AkuzFragment;

public abstract class AccountAuthenticatorFragment extends AkuzFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!(getActivity() instanceof AccountAuthenticatorFragmentActivity)) {
			throw new IllegalArgumentException("This fragment can only live inside AccountAuthenticatorFragmentActivity");
		}
	}

	public final void setAccountAuthenticatorResult(Bundle result) {
		((AccountAuthenticatorFragmentActivity) getActivity()).setAccountAuthenticatorResult(result);
	}

}
