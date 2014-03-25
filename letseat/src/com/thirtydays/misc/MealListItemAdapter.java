package com.thirtydays.misc;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thirtydays.letseat.R;

public class MealListItemAdapter extends ArrayAdapter<String> {
	
	private final Context context;
	private final String[] values;
	
	public MealListItemAdapter ( Context context, String[] values){
		super(context, R.layout.list_meal, values);
		this.context = context;
		this.values = values;
	}

	public View getView ( int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_meal, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.list_items);
		textView.setText(values[position]);
		return textView;
	}
	
}
