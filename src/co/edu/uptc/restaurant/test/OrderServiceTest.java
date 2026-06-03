package co.edu.uptc.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.domain.Order;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.repository.OrderRepository;
import co.edu.uptc.restaurant.service.OrderService;

class OrderServiceTest {

	private OrderRepository orderRepository;
	private OrderService orderService;

	private Customer crearClienteValido() {
		return new Customer("Santiago", "Borda", 12345678, 3);
	}

	private Order crearPedidoValido() {
		return new Order(1, LocalDate.now(), 25000.0, "Bandeja Paisa", crearClienteValido());
	}

	@BeforeEach
	void setUp() {
		orderRepository = new OrderRepository();
		orderService = new OrderService(orderRepository);
	}

	@Test
	void addOrder_datosValidos_retornaExito() {
		ResultDTO result = orderService.addOrder(crearPedidoValido());
		assertTrue(result.isSuccessful());
		assertTrue(orderRepository.existById(1));
	}

	@Test
	void addOrder_idDuplicado_retornaFallo() {
		orderService.addOrder(crearPedidoValido());
		ResultDTO result = orderService.addOrder(crearPedidoValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addOrder_idNegativo_retornaFallo() {
		Order o = crearPedidoValido();
		o.setIdOrder(-1);
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addOrder_fechaNula_retornaFallo() {
		Order o = crearPedidoValido();
		o.setDate(null);
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addOrder_platoVacio_retornaFallo() {
		Order o = crearPedidoValido();
		o.setDish("");
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addOrder_costoNegativo_retornaFallo() {
		Order o = crearPedidoValido();
		o.setTotalCost(-1.0);
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addOrder_clienteNulo_retornaFallo() {
		Order o = crearPedidoValido();
		o.setCustomer(null);
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
	}

	@Test
	void findAll_repositorioVacio_retornaMapaVacio() {
		assertTrue(orderService.findAll().isEmpty());
	}

	@Test
	void findAll_conUnPedido_retornaUno() {
		orderService.addOrder(crearPedidoValido());
		assertEquals(1, orderService.findAll().size());
	}

	@Test
	void findById_existente_retornaPedido() {
		orderService.addOrder(crearPedidoValido());
		Order o = orderService.findById(1);
		assertNotNull(o);
		assertEquals("Bandeja Paisa", o.getDish());
	}

	@Test
	void findById_inexistente_retornaNull() {
		assertNull(orderService.findById(999));
	}

	@Test
	void updateOrder_existente_retornaExito() {
		orderService.addOrder(crearPedidoValido());
		Order actualizado = new Order(1, LocalDate.now(), 35000.0, "Ajiaco", crearClienteValido());
		ResultDTO result = orderService.updateOrder(actualizado);
		assertTrue(result.isSuccessful());
		assertEquals("Ajiaco", orderService.findById(1).getDish());
	}

	@Test
	void updateOrder_inexistente_retornaFallo() {
		ResultDTO result = orderService.updateOrder(crearPedidoValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void updateOrder_datosInvalidos_retornaFallo() {
		orderService.addOrder(crearPedidoValido());
		Order invalido = new Order(1, null, 25000.0, "Ajiaco", crearClienteValido());
		ResultDTO result = orderService.updateOrder(invalido);
		assertFalse(result.isSuccessful());
	}

	@Test
	void deleteOrder_existente_retornaExito() {
		orderService.addOrder(crearPedidoValido());
		ResultDTO result = orderService.deleteOrder(1);
		assertTrue(result.isSuccessful());
		assertFalse(orderRepository.existById(1));
	}

	@Test
	void deleteOrder_inexistente_retornaFallo() {
		ResultDTO result = orderService.deleteOrder(999);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void existById_existente_retornaTrue() {
		orderService.addOrder(crearPedidoValido());
		assertTrue(orderService.existById(1));
	}

	@Test
	void existById_inexistente_retornaFalse() {
		assertFalse(orderService.existById(999));
	}
}
