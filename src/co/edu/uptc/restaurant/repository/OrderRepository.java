package co.edu.uptc.restaurant.repository;

import java.util.HashMap;
import co.edu.uptc.restaurant.domain.Order;

public class OrderRepository {

	private HashMap<Integer, Order> orders;

	public OrderRepository() {
		super();
	}

	public OrderRepository(HashMap<Integer, Order> orders) {
		super();
		this.orders = orders;
	}

	public boolean addOrder(Order order) {
		orders.put(order.getIdOrder(), order);
		return true;
	}

	public HashMap<Integer, Order> findAll() {
		return orders;
	}

	public Order findById(Integer idOrder) {
		return this.orders.get(idOrder);
	}

	public boolean updateOrder(Order order) {
		this.orders.put(order.getIdOrder(), order);
		return true;
	}

	public boolean deleteOrder(Integer idOrder) {
		this.orders.remove(idOrder);
		return true;
	}

	public boolean existById(Integer idOrder) {
		return orders.containsKey(idOrder);
	}

	public HashMap<Integer, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Integer, Order> orders) {
		this.orders = orders;
	}

}

