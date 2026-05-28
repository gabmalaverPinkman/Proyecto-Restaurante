package co.edu.uptc.restaurant.domain;

import java.util.List;

public class Customer {

	private String firstName;
	private String lastName;
	private int dni;
	private List<Order> orders;
	private int assignedTable;
	
	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, int dni, List<Order> orders, int assignedTable) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.orders = orders;
		this.assignedTable = assignedTable;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getAssignedTable() {
		return assignedTable;
	}

	public void setAssignedTable(int assignedTable) {
		this.assignedTable = assignedTable;
	}
}
