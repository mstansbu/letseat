package com.thirtydays.misc;

import java.io.Serializable;

public class Meal implements Serializable {

	public String name;
	public boolean[] type = {false, false, false}; // (Breakfast, Lunch, Dinner)
	
	public Meal(String name, boolean breakfast, boolean lunch, boolean dinner){
		this.name = name;
		this.type[0] = breakfast;
		this.type[1] = lunch;
		this.type[2] = dinner;
	}
	
}
