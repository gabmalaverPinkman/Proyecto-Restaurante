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

	public ResultDTO addCustomer(String dni, String firstName, String lastName, String assignedTable) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateAlphanumericField("Validación dni", dni, "\\d+", resultDTO);
		validateAlphanumericField("Validación firstName", firstName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		validateAlphanumericField("Validación lastName", lastName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		validateAlphanumericField("Validación assignedTable", assignedTable, "\\d+", resultDTO);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Customer customer = new Customer(firstName, lastName, Integer.parseInt(dni), Integer.parseInt(assignedTable));
		return customerService.addCustomer(customer);
	}

	public HashMap<Integer, Customer> findAll() {
		return customerService.findAll();
	}

	public ResultDTO findByDni(String dni) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateAlphanumericField("Validación dni", dni, "\\d+", resultDTO);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Customer customer = customerService.findByDni(Integer.parseInt(dni));
		if (customer == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("No existe un cliente con ese DNI.");
		} else {
			resultDTO.setCustomer(customer);
		}
		return resultDTO;
	}

	public ResultDTO updateCustomer(String dni, String firstName, String lastName, String assignedTable) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateAlphanumericField("Validación dni", dni, "\\d+", resultDTO);
		validateAlphanumericField("Validación firstName", firstName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		validateAlphanumericField("Validación lastName", lastName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		validateAlphanumericField("Validación assignedTable", assignedTable, "\\d+", resultDTO);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Customer customer = new Customer(firstName, lastName, Integer.parseInt(dni), Integer.parseInt(assignedTable));
		return customerService.updateCustomer(customer);
	}

	public ResultDTO deleteCustomer(String dni) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateAlphanumericField("Validación dni", dni, "\\d+", resultDTO);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		return customerService.deleteCustomer(Integer.parseInt(dni));
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

	private ResultDTO validateAlphanumericField(String nameValidation, String field,
			String pattern, ResultDTO resultDTO) {
		if (field == null || field.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El campo " + nameValidation + " no puede ser null ni vacío.");
			return resultDTO;
		}
		if (!field.matches(pattern)) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("Falló la validación: " + nameValidation);
		}
		return resultDTO;
	}
}
