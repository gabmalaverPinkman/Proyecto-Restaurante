package co.edu.uptc.restaurant.dto;

import java.util.ArrayList;

import java.util.List;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.domain.Order;
import co.edu.uptc.restaurant.domain.Restaurant;

public class ResultDTO {
	private boolean isSuccessful;
	private String message;
	private Customer customer;
	private Order order;
	private Restaurant restaurant;
	private List<String> listMessageError;
	
	public ResultDTO() {
		this.listMessageError = new ArrayList<>();
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}
	
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<String> getListMessageError() {
		return listMessageError;
	}

	
	public void setListMessageError(List<String> listMessageError) {
		this.listMessageError = listMessageError;
	}
}
