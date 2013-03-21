package de.akuz.android.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import de.akuz.android.app.SimpleBaseView;

public abstract class AkuzListAdapter<T> extends BaseAdapter {

	protected List<T> objects;
	
	public AkuzListAdapter(List<T> objects){
		this.objects = objects;
	}
	

	@Override
	public int getCount() {
		return objects != null ? objects.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return objects != null ? objects.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T object = objects.get(position);
		SimpleBaseView<T> view = null;
		if (convertView != null) {
			view = (SimpleBaseView<T>) convertView;
			view.clear();
		} else {
			view = createNewView(parent);
		}
		view.bindView(object);
		return view;
	}

	public void setList(List<T> objects) {
		this.objects = objects;
		this.notifyDataSetChanged();
	}

	public abstract SimpleBaseView<T> createNewView(ViewGroup parent);

}
