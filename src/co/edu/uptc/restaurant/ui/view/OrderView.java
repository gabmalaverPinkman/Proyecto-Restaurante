package co.edu.uptc.restaurant.ui.view;

import co.edu.uptc.restaurant.ui.controller.OrderController;

public class OrderView {

	private OrderController orderController;

	public OrderView() {
		super();
	}

	public OrderView(OrderController orderController) {
		super();
		this.orderController = orderController;
	}

	public OrderController getOrderController() {
		return orderController;
	}

	public void setOrderController(OrderController orderController) {
		this.orderController = orderController;
	}

}