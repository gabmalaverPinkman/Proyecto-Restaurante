package co.edu.uptc.restaurant.ui.controller;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Restaurant;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.service.RestaurantService;

public class RestaurantController {

	private RestaurantService restaurantService;

	public RestaurantController() {
		super();
	}

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	public ResultDTO addRestaurant(Restaurant restaurant) {
		return restaurantService.addRestaurant(restaurant);
	}

	public HashMap<Integer, Restaurant> findAll() {
		return restaurantService.findAll();
	}

	public Restaurant findById(Integer id) {
		return restaurantService.findById(id);
	}

	public ResultDTO updateRestaurant(Restaurant restaurant) {
		return restaurantService.updateRestaurant(restaurant);
	}

	public ResultDTO deleteRestaurant(Integer id) {
		return restaurantService.deleteRestaurant(id);
	}

	public boolean existById(Integer id) {
		return restaurantService.existById(id);
	}

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
}
