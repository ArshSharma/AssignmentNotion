package com.assignment.deliveryCalculator;

public class OrderItem {
	
	public OrderItem(int orderId, double time, int slots) {
		super();
		this.orderId = orderId;
		this.time = time;
		this.slots = slots;
	}

	private int orderId;
	
	private double time;
	
	private int slots;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}


}
