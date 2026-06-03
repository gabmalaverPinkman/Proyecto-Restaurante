package co.edu.uptc.restaurant.ui.controller;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Order;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.service.OrderService;

public class OrderController {

	private OrderService orderService;

	public OrderController() {
		super();
	}

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	public ResultDTO addOrder(Order order) {
		return orderService.addOrder(order);
	}

	public HashMap<Integer, Order> findAll() {
		return orderService.findAll();
	}

	public Order findById(Integer idOrder) {
		return orderService.findById(idOrder);
	}

	public ResultDTO updateOrder(Order order) {
		return orderService.updateOrder(order);
	}

	public ResultDTO deleteOrder(Integer idOrder) {
		return orderService.deleteOrder(idOrder);
	}

	public boolean existById(Integer idOrder) {
		return orderService.existById(idOrder);
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}
