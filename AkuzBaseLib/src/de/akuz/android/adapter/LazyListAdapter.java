package de.akuz.android.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import de.greenrobot.dao.LazyList;

public abstract class LazyListAdapter<T> extends BaseAdapter {

	private LazyList<T> objects;

	public LazyListAdapter(LazyList<T> objects) {
		this.objects = objects;
	}

	@Override
	public int getCount() {
		if (objects == null) {
			return 0;
		}
		return objects.size();
	}

	@Override
	public T getItem(int position) {
		if (objects == null) {
			return null;
		}
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentView) {
		if (convertView == null) {
			convertView = newView(parentView);
		}

		return bindView(getItem(position), convertView);
	}

	protected abstract View bindView(T object, View view);

	protected abstract View newView(ViewGroup parent);

}
