package co.edu.uptc.restaurant.service;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Restaurant;
import co.edu.uptc.restaurant.repository.RestaurantRepository;

public class RestaurantService {
	
	private RestaurantRepository restaurantRepository;

	public RestaurantService(RestaurantRepository restaurantRepository) {
		super();
		this.restaurantRepository = restaurantRepository;
	}
	
	public boolean validate(Restaurant restaurant) {
        if (restaurant.getId() <= 0) {
            return false;
        }
        if (restaurant.getName() == null || restaurant.getName().isBlank()) {
            return false;
        }
        if (restaurant.getAddress() == null || restaurant.getAddress().isBlank()) {
            return false;
        }
        if (restaurant.getPhoneNumber() == null || restaurant.getPhoneNumber().isBlank()) {
            return false;
        }
        if (restaurant.getNumberTables() <= 0) {
            return false;
        }
        return true;
    }
	
	public boolean addRestaurant(Restaurant restaurant) {
		if(this.restaurantRepository.existById(restaurant.getId())) {
			return false;
		}
		if(!validate(restaurant)) {
			return false;
		}
		return this.restaurantRepository.addRestaurant(restaurant);
	}
	
	public HashMap<Integer, Restaurant> findAll(){
		return this.restaurantRepository.findAll();
	}
	
	public Restaurant findById(Integer id) {
		return this.restaurantRepository.findById(id);
	}
	
	public boolean updateRestaurant(Restaurant restaurant) {
		if(!this.restaurantRepository.existById(restaurant.getId())) {
			return false;
		}
		if(!validate(restaurant)) {
			return false;
		}
		return this.restaurantRepository.updateRestaurant(restaurant);
	}
	
	public boolean deleteRestaurant(Integer id) {
		return this.restaurantRepository.deleteRestaurant(id);
	}
	
	public boolean existById(Integer id) {
		return this.restaurantRepository.existById(id);
	}

	public RestaurantRepository getRestaurantRepository() {
		return restaurantRepository;
	}

	public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

}
