package com.thirtydays.letseat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.thirtydays.misc.Meal;

public class AddMealActivity extends ActionBarActivity {

	private boolean breakfast = false;
	private boolean lunch = false;
	private boolean dinner = false;
	private String name = "";
	private String filename = "MEALS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_meal);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_add_meal_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	            goSeeMealList();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void onCheckboxClicked(View view){
		
		boolean checked = ((CheckBox) view).isChecked();
		
		switch(view.getId()) {
			case R.id.checkbox_meal_breakfast:
		 		if(checked){
		 			breakfast = true;
		 		}else{
		 			breakfast = false;
		 		}break;
		 	case R.id.checkbox_meal_lunch:
		 		if(checked){
		 			lunch = true;
		 		}else{
		 			lunch = false;
		 		}break;
		 	case R.id.checkbox_meal_dinner:
		 		if(checked){
		 			dinner = true;
		 		}else{
		 			dinner = false;
		 		}break;
		}
	}
	
	public void goSeeMealList( ){
		Intent intent = new Intent(this, SeeMealListActivity.class);
		startActivity(intent);
	}
	
	public void finishAddMeal(View view){
		name = ((EditText) findViewById(R.id.meal_name)).getText().toString();
		if( ( breakfast == true || lunch == true || dinner == true ) && name.isEmpty() == false){
			Meal addedMeal = new Meal(name, breakfast, lunch, dinner);
	    	try {
	    	    File file = getBaseContext().getFileStreamPath(filename);
	    	    if(file.exists()){
					FileInputStream fis = openFileInput(filename);
				    ObjectInputStream ois = new ObjectInputStream(fis);
					ArrayList<Meal> meals = (ArrayList<Meal>) ois.readObject();
					ois.close();
					fis.close();
					boolean flag = false;
					for(Meal meal: meals){
						if(meal.name.toLowerCase() == addedMeal.name.toLowerCase()){
							flag = true;
						}
					}
					if( flag == false ){
						meals.add(addedMeal);
						FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(meals);
						oos.close();
						fos.close();
					}else{
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
			            builder.setMessage("Meal already in list.");
			            builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int id) {
			                    dialog.cancel();
			                }
			            });
			            AlertDialog alert = builder.create();
			            alert.show();
					}
	    	    }else{
					FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					List<Meal> meals = new ArrayList<Meal>();
					meals.add(addedMeal);
					oos.writeObject(meals);
					oos.close();
					fos.close();
	    	    }
			} catch (FileNotFoundException e) {
				
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	finish();
		}else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Something is Missing!");
            builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
		}
	}
}
