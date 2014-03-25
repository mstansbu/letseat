package com.thirtydays.letseat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity  extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	            //openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void goEatOut( View view ){
		//Intent intent = new Intent(this,EatOutActivity.class);
		//startActivity(intent);
	}

	public void goEatIn( View view ){
		Intent intent = new Intent(this,EatInActivity.class);
		startActivityForResult(intent,1);
	}
	
	public void goSeeList( View view ){
		Intent intent = new Intent(this, SeeMealListActivity.class);
		startActivityForResult(intent,1);
	}
}
