package co.edu.uptc.restaurant.ui.view;

import co.edu.uptc.restaurant.ui.controller.RestaurantController;

public class RestaurantView {

	private RestaurantController restaurantController;

	public RestaurantView() {
		super();
	}

	public RestaurantView(RestaurantController restaurantController) {
		super();
		this.restaurantController = restaurantController;
	}

	public RestaurantController getRestaurantController() {
		return restaurantController;
	}

	public void setRestaurantController(RestaurantController restaurantController) {
		this.restaurantController = restaurantController;
	}

}
