package com.thirtydays.letseat;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.thirtydays.misc.Meal;
import com.thirtydays.misc.MealListItemAdapter;

public class SeeMealListActivity extends ActionBarActivity {
	
	String filename = "MEALS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_meal_list);
		
		ListView listView = (ListView) findViewById(R.id.see_meal_list);
		
		File file = getBaseContext().getFileStreamPath(filename);
 	    if(file.exists()){
 	    	try {
				FileInputStream fis = openFileInput(filename);
			    ObjectInputStream ois = new ObjectInputStream(fis);
				ArrayList<Meal> savedMeals = (ArrayList<Meal>) ois.readObject();
				ois.close();
				fis.close();
				System.out.println("before list stuff");
				System.out.println(savedMeals.size());
				ArrayList<String> mealNames = new ArrayList<String>();
				for(Meal meal:savedMeals){
					mealNames.add(meal.name);
				}
				String[] listMeals = mealNames.toArray(new String[mealNames.size()]);
				System.out.println("middle of list stuff.");
				listView.setAdapter(new MealListItemAdapter(this, listMeals));
				System.out.println("after list stuff");
 	    	}catch(Exception e){
 	    		System.out.println("Oops.");
 	    	}
 	    }else{
 	    	//TODO no list exists yets
 	    	System.out.println("No files");
 	    }
		
		listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			}
		});
		
	}
	
}
