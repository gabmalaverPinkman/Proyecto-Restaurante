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
	void addOrderTrue() {
		ResultDTO result = orderService.addOrder(crearPedidoValido());
		assertTrue(result.isSuccessful());
		assertTrue(orderRepository.existById(1));
	}

	@Test
	void addOrderIdDuplicatedFailed() {
		orderService.addOrder(crearPedidoValido());
		ResultDTO result = orderService.addOrder(crearPedidoValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addOrderIdNegativeFailed() {
		Order o = crearPedidoValido();
		o.setIdOrder(-1);
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addOrderFailedNoDate() {
		Order o = crearPedidoValido();
		o.setDate(null);
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addOrderFailedNoDish() {
		Order o = crearPedidoValido();
		o.setDish("");
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addOrderFailedNegativeCost() {
		Order o = crearPedidoValido();
		o.setTotalCost(-1.0);
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addOrderFailedNoClient() {
		Order o = crearPedidoValido();
		o.setCustomer(null);
		ResultDTO result = orderService.addOrder(o);
		assertFalse(result.isSuccessful());
	}


	@Test
	void findByTrue() {
		orderService.addOrder(crearPedidoValido());
		Order o = orderService.findById(1);
		assertNotNull(o);
		assertEquals("Bandeja Paisa", o.getDish());
	}

	@Test
	void findByFailed() {
		assertNull(orderService.findById(999));
	}

	@Test
	void updateOrderTrue() {
		orderService.addOrder(crearPedidoValido());
		Order actualizado = new Order(1, LocalDate.now(), 35000.0, "Ajiaco", crearClienteValido());
		ResultDTO result = orderService.updateOrder(actualizado);
		assertTrue(result.isSuccessful());
		assertEquals("Ajiaco", orderService.findById(1).getDish());
	}

	@Test
	void updateOrderFailedNoOrder() {
		ResultDTO result = orderService.updateOrder(crearPedidoValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void deleteOrderTrue() {
		orderService.addOrder(crearPedidoValido());
		ResultDTO result = orderService.deleteOrder(1);
		assertTrue(result.isSuccessful());
		assertFalse(orderRepository.existById(1));
	}

	@Test
	void deleteOrderFailedNoOrder() {
		ResultDTO result = orderService.deleteOrder(999);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void existByIdTrue() {
		orderService.addOrder(crearPedidoValido());
		assertTrue(orderService.existById(1));
	}

	@Test
	void existByIdFailed() {
		assertFalse(orderService.existById(999));
	}
}
