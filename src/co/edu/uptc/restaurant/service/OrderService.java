package co.edu.uptc.restaurant.service;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Order;
import co.edu.uptc.restaurant.repository.OrderRepository;

public class OrderService {

	private OrderRepository orderRepository;

	public OrderService() {
		super();
	}

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	public boolean validate(Order order) {
		if (order.getIdOrder() <= 0) {
			return false;
		}
		if (order.getDate() == null) {
			return false;
		}
		if (order.getDish() == null || order.getDish().isBlank()) {
			return false;
		}
		if (order.getTotalCost() <= 0) {
			return false;
		}
		if (order.getCustomer() == null) {
			return false;
		}
		return true;
	}

	public boolean addOrder(Order order) {
		if (validate(order)) {
			return this.orderRepository.addOrder(order);
		}
		return false;
	}

	public HashMap<Integer, Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Integer idOrder) {
		return this.orderRepository.findById(idOrder);
	}

	public boolean updateOrder(Order order) {
		if (!validate(order)) {
			return false;
		}
		if (orderRepository.existById(order.getIdOrder())) {
			return false;
		}
		return orderRepository.updateOrder(order);
	}

	public boolean deleteOrder(Integer idOrder) {
		return orderRepository.deleteOrder(idOrder);
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