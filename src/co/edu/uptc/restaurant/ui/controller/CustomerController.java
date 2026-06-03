package co.edu.uptc.restaurant.ui.controller;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.service.CustomerService;

public class CustomerController {

	private CustomerService customerService;

	public CustomerController() {
		super();
	}

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	public ResultDTO addCustomer(Customer customer) {
		return customerService.addCustomer(customer);
	}

	public HashMap<Integer, Customer> findAll() {
		return customerService.findAll();
	}

	public Customer findByDni(Integer dni) {
		return customerService.findByDni(dni);
	}

	public ResultDTO updateCustomer(Customer customer) {
		return customerService.updateCustomer(customer);
	}

	public ResultDTO deleteCustomer(Integer dni) {
		return customerService.deleteCustomer(dni);
	}

	public boolean existByDni(Integer dni) {
		return customerService.existByDni(dni);
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
