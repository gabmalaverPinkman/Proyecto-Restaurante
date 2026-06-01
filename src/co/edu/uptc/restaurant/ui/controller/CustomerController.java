package co.edu.uptc.restaurant.ui.controller;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.service.CustomerService;

public class CustomerController {
	
	private CustomerService customerService;

	public CustomerController( CustomerService customerService) {
		super();
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

        boolean result = customerService.addCustomer(new Customer(firstName, lastName, Integer.parseInt(dni), Integer.parseInt(assignedTable)));
        if (!result) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("Ya existe un cliente con ese DNI");
        }
        return resultDTO;
	}
	
	public ResultDTO updateCustomer(String dni, String firstName, String lastName, String assignedTable) {
	    ResultDTO resultDTO = new ResultDTO();
	    resultDTO.setSuccessful(true);

	    validateAlphanumericField("ValidationDni", dni, "\\d+", resultDTO);
	    validateAlphanumericField("ValidationFirstName", firstName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
	    validateAlphanumericField("ValidationLastName", lastName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
	    validateAlphanumericField("ValidationAssignedTable", assignedTable, "\\d+", resultDTO);

	    if (!resultDTO.isSuccessful()) {
	        return resultDTO;
	    }

	    boolean result = customerService.updateCustomer(new Customer(
	            firstName, lastName, Integer.parseInt(dni), Integer.parseInt(assignedTable)
	    ));
	    if (!result) {
	        resultDTO.setSuccessful(false);
	        resultDTO.getListMessageError().add("El cliente no fue encontrado.");
	    } else {
	        resultDTO.setMessage("Se actualizó el registro del cliente");
	    }
	    return resultDTO;
	}
	
	public HashMap<Integer, Customer> findAll() {
        return customerService.findAll();
    }
	
	public ResultDTO findByDni(String dni) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("ValidationDni", dni, "\\d+", resultDTO);
        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }
        resultDTO.setCustomer(customerService.findByDni(Integer.parseInt(dni)));
        return resultDTO;
    }
	
	public ResultDTO deleteCustomer(String dni) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("ValidationDni", dni, "\\d+", resultDTO);
        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }

        boolean result = this.customerService.deleteCustomer(Integer.parseInt(dni));
        if (!result) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("El registro no se pudo eliminar");
            return resultDTO;
        }
        resultDTO.setMessage("El registro fue eliminado");
        return resultDTO;
    }

	private ResultDTO validateAlphanumericField(String nameValidation, String field,
	        String pattern, ResultDTO resultDTO) {
	    if (field == null || field.trim().isEmpty()) {
	        resultDTO.setSuccessful(false);
	        resultDTO.getListMessageError().add("El campo " + nameValidation + " no puede ser null ni vacío");
	        return resultDTO;
	    }
	    boolean result = field.matches(pattern);
	    if (!result) {
	        resultDTO.setSuccessful(false);
	        resultDTO.getListMessageError().add("Falló la validación: " + nameValidation);
	    }
	    return resultDTO;
	}
	
}
