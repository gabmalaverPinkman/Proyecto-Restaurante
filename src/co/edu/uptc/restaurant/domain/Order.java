package co.edu.uptc.restaurant.domain;

import java.time.LocalDate;

public class Order {

	private int idOrder;
	private LocalDate date;
	private double totalCost;
	private String dish;
	private Customer customer;
	
	public Order() {
		super();
	}

	public Order(int idOrder, LocalDate date, double totalCost, String dish, Customer customer) {
		super();
		this.idOrder = idOrder;
		this.date = date;
		this.totalCost = totalCost;
		this.dish = dish;
		this.customer = customer;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
