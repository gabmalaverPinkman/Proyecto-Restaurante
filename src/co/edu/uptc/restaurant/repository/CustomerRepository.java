package co.edu.uptc.restaurant.repository;

import java.util.HashMap;
import co.edu.uptc.restaurant.domain.Customer;

public class CustomerRepository {
	
	private HashMap<Integer, Customer> customers = new HashMap<>();

	public CustomerRepository(HashMap<Integer, Customer> customers) {
		super();
		this.customers = customers;
	}
	
	public boolean addCustomer(Customer customer) {
	    customers.put(customer.getDni(), customer); 
	    return true;
	}
	
	public HashMap<Integer, Customer> findAll() {
		return customers;
	}
	
	public Customer findByDni(Integer dni) {
		return this.customers.get(dni);
	}
	
	public boolean updateCustomer(Customer customer) {
		this.customers.put(customer.getDni(), customer);
		return true;
	}
	
	public boolean deleteCustomer(Integer dni) {
		this.customers.remove(dni);
		return true;
	}
	
	public boolean existByDni(Integer dni) {
		return customers.containsKey(dni);
	}

	public HashMap<Integer, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<Integer, Customer> customers) {
		this.customers = customers;
	}

}
