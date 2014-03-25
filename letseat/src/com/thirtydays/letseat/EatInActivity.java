package com.thirtydays.letseat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.thirtydays.misc.Meal;

public class EatInActivity extends ActionBarActivity{

	boolean[] meals = {false,false,false};
	String filename = "MEALS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eat_in);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_eat_in_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	            //openSettings();
	            return true;
	        case R.id.action_add:
	        	goAddMeal();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void goFindMeal(View view){
		if( meals[0] == true || meals[1] == true || meals[2] == true ){
			List<Meal> searchedMeals = new ArrayList<Meal>();
			File file = getBaseContext().getFileStreamPath(filename);
	 	    if(file.exists()){
	 	    	try {
					FileInputStream fis = openFileInput(filename);
				    ObjectInputStream ois = new ObjectInputStream(fis);
					ArrayList<Meal> savedMeals = (ArrayList<Meal>) ois.readObject();
					ois.close();
					fis.close();
					for(Meal meal: savedMeals){
						if((meal.type[0] == true && meals[0] == true) || (meal.type[1] == true && meals[1] == true) || (meal.type[2] == true && meals[2] == true)){
							searchedMeals.add(meal);
						}
					}
					int number = (int) Math.floor(Math.random()*searchedMeals.size());
					if(searchedMeals.size() == 0){
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
			            builder.setMessage("No meals of that type exist.");
			            builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int id) {
			                    dialog.cancel();
			                }
			            });
			            AlertDialog alert = builder.create();
			            alert.show();
			            return;
					}
					System.out.println(number);
					System.out.println(searchedMeals.size());
					Meal meal = searchedMeals.get(number);
					
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
		            builder.setMessage(meal.name);
		            builder.setCancelable(true);
		            builder.setPositiveButton("OK!",new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                    finish();
		                }
		            });
		            builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            });
		            AlertDialog alert = builder.create();
		            alert.show();
				} catch (OptionalDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 	    }else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage("No meals added yet.");
	            builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            AlertDialog alert = builder.create();
	            alert.show();
	 	    }
		}else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please check a meal type.");
            builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
		}
	}
	
	public void onCheckboxClicked(View view){
		
		boolean checked = ((CheckBox) view).isChecked();
		
		switch(view.getId()) {
			case R.id.checkbox_breakfast:
		 		if(checked){
		 			meals[0] = true;
		 		}else{
		 			meals[0] = false;
		 		}break;
		 	case R.id.checkbox_lunch:
		 		if(checked){
		 			meals[1] = true;
		 		}else{
		 			meals[1] = false;
		 		}break;
		 	case R.id.checkbox_dinner:
		 		if(checked){
		 			meals[2] = true;
		 		}else{
		 			meals[2] = false;
		 		}break;
		}
	}
	
	public void goAddMeal( ){
		Intent intent = new Intent(this,AddMealActivity.class);
		startActivityForResult(intent,1);
	}
}
