package co.edu.uptc.restaurant.service;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.repository.CustomerRepository;

public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService() {
		super();
	}

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public ResultDTO validate(Customer customer) {
		ResultDTO result = new ResultDTO();
		if (customer.getDni() == null || customer.getDni() <= 0) {
			result.getListMessageError().add("El DNI debe ser un número positivo.");
		}
		if (customer.getFirstName() == null || customer.getFirstName().isBlank()) {
			result.getListMessageError().add("El nombre no puede estar vacío.");
		}
		if (customer.getLastName() == null || customer.getLastName().isBlank()) {
			result.getListMessageError().add("El apellido no puede estar vacío.");
		}
		if (customer.getAssignedTable() <= 0) {
			result.getListMessageError().add("La mesa asignada debe ser un número positivo.");
		}
		result.setSuccessful(result.getListMessageError().isEmpty());
		return result;
	}

	public ResultDTO addCustomer(Customer customer) {
		ResultDTO result = validate(customer);
		if (!result.isSuccessful()) {
			return result;
		}
		if (customerRepository.existByDni(customer.getDni())) {
			result.setSuccessful(false);
			result.getListMessageError().add("Ya existe un cliente con ese DNI.");
			return result;
		}
		customerRepository.addCustomer(customer);
		result.setSuccessful(true);
		result.setMessage("Cliente agregado exitosamente.");
		return result;
	}

	public HashMap<Integer, Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer findByDni(Integer dni) {
		return customerRepository.findByDni(dni);
	}

	public ResultDTO updateCustomer(Customer customer) {
		ResultDTO result = validate(customer);
		if (!result.isSuccessful()) {
			return result;
		}
		if (!customerRepository.existByDni(customer.getDni())) {
			result.setSuccessful(false);
			result.getListMessageError().add("No existe un cliente con ese DNI.");
			return result;
		}
		customerRepository.updateCustomer(customer);
		result.setSuccessful(true);
		result.setMessage("Cliente actualizado exitosamente.");
		return result;
	}

	public ResultDTO deleteCustomer(Integer dni) {
		ResultDTO result = new ResultDTO();
		if (!customerRepository.existByDni(dni)) {
			result.setSuccessful(false);
			result.getListMessageError().add("No existe un cliente con ese DNI.");
			return result;
		}
		customerRepository.deleteCustomer(dni);
		result.setSuccessful(true);
		result.setMessage("Cliente eliminado exitosamente.");
		return result;
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