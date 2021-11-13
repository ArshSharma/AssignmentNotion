package com.assignment.deliveryCalculator;

import java.util.List;

class Order{

	public Order() {
		super();
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setMeals(List<Character> meals) {
		this.meals = meals;
	}

	Order(int orderId, double distance, List<Character> meals){
	    this.orderId = orderId;
	    this.distance = distance;
	    this.meals = meals;
	  }
	  private int orderId;

	  private double distance;

	  private List<Character> meals;

	  public int getOrderId() {
	    return orderId;
	  }

	  public double getDistance(){
	    return distance;
	  }

	  public List<Character> getMeals() {
	    return meals;
	  }
	  
	  public int getRequiredSlots() {
		  int slots = 0;
		  for(Character mealType:  this.meals) {
			  slots = mealType == 'A'? slots +1 : slots+2;
		  }
		  return slots;
	  }
	  
	  public int getMaxPreptime() {
		  return this.meals.contains('M') ? 29 : 17;
	  }
	   
	}