package de.akuz.android.app;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import de.akuz.android.R;

public class ErrorView extends SimpleBaseView<ErrorMessage> {

	private ImageView errorIcon;
	private TextView errorText;

	public ErrorView(Context context, ErrorMessage message) {
		super(context);
		clear();
		bindView(message);
	}

	@Override
	protected void buildView() {
		setContentView(R.layout.error_view);
		errorIcon = (ImageView) findView(R.id.errorIcon);
		errorText = (TextView) findView(R.id.errorText);
	}

	@Override
	protected void bindViewToObject(ErrorMessage object) {
		errorIcon.setImageDrawable(object.getIcon());
		errorText.setText(object.getMessageId());

	}

	@Override
	public void clear() {
		errorIcon.setImageBitmap(null);
		errorText.setText("");
	}

}
