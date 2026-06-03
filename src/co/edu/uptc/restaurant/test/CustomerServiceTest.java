package co.edu.uptc.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.repository.CustomerRepository;
import co.edu.uptc.restaurant.service.CustomerService;

class CustomerServiceTest {

	private CustomerRepository customerRepository;
	private CustomerService customerService;

	private Customer crearClienteValido() {
		return new Customer("Santiago", "Borda", 12345678, 3);
	}

	@BeforeEach
	void setUp() {
		customerRepository = new CustomerRepository();
		customerService = new CustomerService(customerRepository);
	}

	@Test
	void addCustomer_True() {
		ResultDTO result = customerService.addCustomer(crearClienteValido());
		assertTrue(result.isSuccessful());
		assertTrue(customerRepository.existByDni(12345678));
	}

	@Test
	void addCustomerFailedDuplicatedDni() {
		customerService.addCustomer(crearClienteValido());
		ResultDTO result = customerService.addCustomer(crearClienteValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addCustomerFailedNameVoid() {
		Customer c = crearClienteValido();
		c.setFirstName("");
		ResultDTO result = customerService.addCustomer(c);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addCustomerFailedLastNameVoid() {
		Customer c = crearClienteValido();
		c.setLastName("");
		ResultDTO result = customerService.addCustomer(c);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addCustomerFailedNegativeDni() {
		Customer c = crearClienteValido();
		c.setDni(-1);
		ResultDTO result = customerService.addCustomer(c);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addCustomerFailedNegativeTable() {
		Customer c = crearClienteValido();
		c.setAssignedTable(-1);
		ResultDTO result = customerService.addCustomer(c);
		assertFalse(result.isSuccessful());
	}


	@Test
	void findByDniTrue() {
		customerService.addCustomer(crearClienteValido());
		Customer c = customerService.findByDni(12345678);
		assertNotNull(c);
		assertEquals("Santiago", c.getFirstName());
	}

	@Test
	void findByDniFailed() {
		assertNull(customerService.findByDni(99999999));
	}

	@Test
	void updateCustomerTrue() {
		customerService.addCustomer(crearClienteValido());
		Customer actualizado = new Customer("Santiago Actualizado", "Borda", 12345678, 5);
		ResultDTO result = customerService.updateCustomer(actualizado);
		assertTrue(result.isSuccessful());
		assertEquals("Santiago Actualizado", customerService.findByDni(12345678).getFirstName());
	}

	@Test
	void updateCustomerFailed() {
		ResultDTO result = customerService.updateCustomer(crearClienteValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void updateCustomerFailedInvalidDdata() {
		customerService.addCustomer(crearClienteValido());
		Customer invalido = new Customer("", "Borda", 12345678, 5);
		ResultDTO result = customerService.updateCustomer(invalido);
		assertFalse(result.isSuccessful());
	}

	@Test
	void deleteCustomerTrue() {
		customerService.addCustomer(crearClienteValido());
		ResultDTO result = customerService.deleteCustomer(12345678);
		assertTrue(result.isSuccessful());
		assertFalse(customerRepository.existByDni(12345678));
	}

	@Test
	void deleteCustomerFailed() {
		ResultDTO result = customerService.deleteCustomer(99999999);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void existByDniTrue() {
		customerService.addCustomer(crearClienteValido());
		assertTrue(customerService.existByDni(12345678));
	}

	@Test
	void existByDniFailed() {
		assertFalse(customerService.existByDni(99999999));
	}
}
