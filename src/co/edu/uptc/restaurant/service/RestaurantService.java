package co.edu.uptc.restaurant.service;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Restaurant;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.repository.RestaurantRepository;

public class RestaurantService {

	private RestaurantRepository restaurantRepository;

	public RestaurantService() {
		super();
	}

	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	public ResultDTO validate(Restaurant restaurant) {
		ResultDTO result = new ResultDTO();
		if (restaurant.getId() == null || restaurant.getId() <= 0) {
			result.getListMessageError().add("El ID del restaurante debe ser un número positivo.");
		}
		if (restaurant.getName() == null || restaurant.getName().isBlank()) {
			result.getListMessageError().add("El nombre no puede estar vacío.");
		}
		if (restaurant.getAddress() == null || restaurant.getAddress().isBlank()) {
			result.getListMessageError().add("La dirección no puede estar vacía.");
		}
		if (restaurant.getPhoneNumber() == null || restaurant.getPhoneNumber().isBlank()) {
			result.getListMessageError().add("El teléfono no puede estar vacío.");
		}
		if (restaurant.getNumberTables() <= 0) {
			result.getListMessageError().add("El número de mesas debe ser mayor a cero.");
		}
		result.setSuccessful(result.getListMessageError().isEmpty());
		return result;
	}

	public ResultDTO addRestaurant(Restaurant restaurant) {
		ResultDTO result = validate(restaurant);
		if (!result.isSuccessful()) {
			return result;
		}
		if (restaurantRepository.existById(restaurant.getId())) {
			result.setSuccessful(false);
			result.getListMessageError().add("Ya existe un restaurante con ese ID");
			return result;
		}
		restaurantRepository.addRestaurant(restaurant);
		result.setSuccessful(true);
		result.setMessage("Restaurante agregado exitosamente");
		return result;
	}

	public HashMap<Integer, Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	public Restaurant findById(Integer id) {
		return restaurantRepository.findById(id);
	}

	public ResultDTO updateRestaurant(Restaurant restaurant) {
		ResultDTO result = validate(restaurant);
		if (!result.isSuccessful()) {
			return result;
		}
		if (!restaurantRepository.existById(restaurant.getId())) {
			result.setSuccessful(false);
			result.getListMessageError().add("No existe un restaurante con ese ID");
			return result;
		}
		restaurantRepository.updateRestaurant(restaurant);
		result.setSuccessful(true);
		result.setMessage("Restaurante actualizado exitosamente");
		return result;
	}

	public ResultDTO deleteRestaurant(Integer id) {
		ResultDTO result = new ResultDTO();
		if (!restaurantRepository.existById(id)) {
			result.setSuccessful(false);
			result.getListMessageError().add("No existe un restaurante con ese ID");
			return result;
		}
		restaurantRepository.deleteRestaurant(id);
		result.setSuccessful(true);
		result.setMessage("Restaurante eliminado exitosamente");
		return result;
	}

	public boolean existById(Integer id) {
		return restaurantRepository.existById(id);
	}

	public RestaurantRepository getRestaurantRepository() {
		return restaurantRepository;
	}

	public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}
}
