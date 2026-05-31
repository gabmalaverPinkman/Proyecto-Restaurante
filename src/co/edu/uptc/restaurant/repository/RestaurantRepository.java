package co.edu.uptc.restaurant.repository;

import java.util.HashMap;
import co.edu.uptc.restaurant.domain.Restaurant;

public class RestaurantRepository {

	private HashMap<Integer, Restaurant> restaurants = new HashMap<>();
	public RestaurantRepository(HashMap<Integer, Restaurant> restaurants) {
		super();
		this.restaurants = restaurants;
	}

	public boolean addRestaurant(Restaurant restaurant) {
		restaurants.put(restaurant.getId(), restaurant);
		return true;
	}

	public HashMap<Integer, Restaurant> findAll() {
		return restaurants;
	}

	public Restaurant findById(Integer id) {
		return this.restaurants.get(id);
	}

	public boolean updateRestaurant(Restaurant restaurant) {
		this.restaurants.put(restaurant.getId(), restaurant);
		return true;
	}

	public boolean deleteRestaurant(Integer id) {
		this.restaurants.remove(id);
		return true;
	}

	public boolean existById(Integer id) {
		return restaurants.containsKey(id);
	}

	public HashMap<Integer, Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(HashMap<Integer, Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

}
