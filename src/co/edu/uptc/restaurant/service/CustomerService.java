package co.edu.uptc.restaurant.service;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.repository.CustomerRepository;

public class CustomerService {
	
	private CustomerRepository customerRepository;

	public CustomerService() {
		super();
	}

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	public boolean validate(Customer customer) {
		if(customer.getDni() == null || customer.getDni() <= 0) {
			return false;
		}
		if(customer.getFirstName() == null || customer.getFirstName().isBlank()) {
			return false;
		}
		if(customer.getLastName() == null || customer.getLastName().isBlank()) {
			return false;
		}
		if(customer.getAssignedTable() <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean addCustomer(Customer customer) {
		if(validate(customer)) {
			return this.customerRepository.addCustomer(customer);
		}
		
		return false;
	}
	
	public HashMap<Integer, Customer> findAll(){
		return customerRepository.findAll();
	}
	
	public Customer findByDni(Integer dni) {
		return this.customerRepository.findByDni(dni);
	}
	
	public boolean updateCustomer(Customer customer) {
		if(!validate(customer)) {
			return false;
		}
		if(customerRepository.existByDni(customer.getDni())) {
			return false;
		}
		return customerRepository.updateCustomer(customer);
	}
	
	public boolean deleteCustomer(Integer dni) {
		return customerRepository.deleteCustomer(dni);
	}
	public boolean existByDni(Integer dni) {
		return customerRepository.existByDni(dni);
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	
	
}
