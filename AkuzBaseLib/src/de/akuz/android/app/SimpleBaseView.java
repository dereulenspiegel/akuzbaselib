package de.akuz.android.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public abstract class SimpleBaseView<T> extends LinearLayout {

	private View root;
	private LayoutInflater inflater;

	protected T object;

	public SimpleBaseView(Context context) {
		super(context);
		inflater = LayoutInflater.from(context);
		buildView();
		addView(root);
	}

	protected void setContentView(int id) {
		root = inflater.inflate(id, null);
	}

	protected abstract void buildView();

	public View findView(int id) {
		return root.findViewById(id);
	}

	public void bindView(T object) {
		this.object = object;
		bindViewToObject(object);
	}

	protected abstract void bindViewToObject(T object);

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		bindView(object);
	}
	
	public abstract void clear();

}
