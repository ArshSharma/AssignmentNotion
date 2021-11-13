package com.assignment.deliveryCalculator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

	public static void main ( String arg[]) {
		ObjectMapper objectMapper = new ObjectMapper();
		FileReader fileReader;
		try {
			fileReader = new FileReader("src/main/java/input.txt");
			Queue<OrderItem> deliveryQueue = new PriorityQueue<OrderItem>(new SortPrepItems());
	        List<Order> orderList = objectMapper.readValue(fileReader, new TypeReference<List<Order>>(){});
	        int slots = 0;
	        for( Order order: orderList) {
	        	int tempSlots = slots;
	        	Queue<OrderItem> tempOrderQueue = new PriorityQueue<OrderItem>(deliveryQueue);
	        	int requiredSlots = order.getRequiredSlots();
	        	double distanceTime = order.getDistance()*8;
	        	
	        	if(order.getMeals().size()  >7 || requiredSlots > 7 || distanceTime + 17 > 150) {
	        		printStatus(order.getOrderId(), 151);
	        		continue;
	        	}
	        	double maxWaitTime = 0;
	        	while(slots+ requiredSlots > 7) {
	        		OrderItem lastItem = deliveryQueue.remove();
	        		slots = slots - lastItem.getSlots();
	        		maxWaitTime = lastItem.getTime();
	        	}
	        	double totalTime = distanceTime + maxWaitTime + order.getMaxPreptime();
	        	printStatus(order.getOrderId(), totalTime);
	        	if(totalTime > 150) {
	        		slots = tempSlots;
	        		deliveryQueue = tempOrderQueue;
	        		continue;
	        	}
	        	slots = slots + order.getRequiredSlots();
	        	deliveryQueue.add(new OrderItem(order.getOrderId(), totalTime, requiredSlots));
	        	
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public static void printStatus(int orderId,double time) {
		if(time <= 150) {
			System.out.println("Order " + orderId + " will get delivered in " + time+ " minutes.");
		}
		else {
			System.out.println("Order " + orderId +" is denied because the restaurant cannot accommodate it.");
		}
		
	}
	
}

class SortPrepItems implements Comparator<OrderItem> {
	@Override
	public int compare(OrderItem o1, OrderItem o2) {
		// TODO Auto-generated method stub
		
		return o1.getTime() < o2.getTime() ? -1 : 1;
	}
}