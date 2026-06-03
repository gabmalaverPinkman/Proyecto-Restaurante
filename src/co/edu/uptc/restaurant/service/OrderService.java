package co.edu.uptc.restaurant.service;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Order;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.repository.OrderRepository;

public class OrderService {

	private OrderRepository orderRepository;

	public OrderService() {
		super();
	}

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public ResultDTO validate(Order order) {
		ResultDTO result = new ResultDTO();
		if (order.getIdOrder() <= 0) {
			result.getListMessageError().add("El ID del pedido debe ser un número positivo.");
		}
		if (order.getDate() == null) {
			result.getListMessageError().add("La fecha no puede ser nula.");
		}
		if (order.getDish() == null || order.getDish().isBlank()) {
			result.getListMessageError().add("El plato no puede estar vacío.");
		}
		if (order.getTotalCost() <= 0) {
			result.getListMessageError().add("El costo total debe ser mayor a cero.");
		}
		if (order.getCustomer() == null) {
			result.getListMessageError().add("El pedido debe tener un cliente asociado.");
		}
		result.setSuccessful(result.getListMessageError().isEmpty());
		return result;
	}

	public ResultDTO addOrder(Order order) {
		ResultDTO result = validate(order);
		if (!result.isSuccessful()) {
			return result;
		}
		if (orderRepository.existById(order.getIdOrder())) {
			result.setSuccessful(false);
			result.getListMessageError().add("Ya existe un pedido con ese ID.");
			return result;
		}
		orderRepository.addOrder(order);
		result.setSuccessful(true);
		result.setMessage("Pedido agregado exitosamente.");
		return result;
	}

	public HashMap<Integer, Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Integer idOrder) {
		return orderRepository.findById(idOrder);
	}

	public ResultDTO updateOrder(Order order) {
		ResultDTO result = validate(order);
		if (!result.isSuccessful()) {
			return result;
		}
		if (!orderRepository.existById(order.getIdOrder())) {
			result.setSuccessful(false);
			result.getListMessageError().add("No existe un pedido con ese ID.");
			return result;
		}
		orderRepository.updateOrder(order);
		result.setSuccessful(true);
		result.setMessage("Pedido actualizado exitosamente.");
		return result;
	}

	public ResultDTO deleteOrder(Integer idOrder) {
		ResultDTO result = new ResultDTO();
		if (!orderRepository.existById(idOrder)) {
			result.setSuccessful(false);
			result.getListMessageError().add("No existe un pedido con ese ID.");
			return result;
		}
		orderRepository.deleteOrder(idOrder);
		result.setSuccessful(true);
		result.setMessage("Pedido eliminado exitosamente.");
		return result;
	}

	public boolean existById(Integer idOrder) {
		return orderRepository.existById(idOrder);
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
}
