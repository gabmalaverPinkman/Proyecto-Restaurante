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

	public ResultDTO addRestaurant(String id, String name, String qualification,
			String address, String phoneNumber, String numberTables) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateAlphanumericField("Validación id", id, "\\d+", resultDTO);
		validateAlphanumericField("Validación name", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		validateAlphanumericField("Validación qualification", qualification, "^\\d+(\\.\\d+)?$", resultDTO);
		validateAlphanumericField("Validación address", address, "^[a-zA-Z0-9ÁÉÍÓÚáéíóúÑñ #.,.-]+$", resultDTO);
		validateAlphanumericField("Validación phoneNumber", phoneNumber, "\\d+", resultDTO);
		validateAlphanumericField("Validación numberTables", numberTables, "\\d+", resultDTO);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Restaurant restaurant = new Restaurant(name, Float.parseFloat(qualification),
				address, Integer.parseInt(id), phoneNumber, Integer.parseInt(numberTables));
		return restaurantService.addRestaurant(restaurant);
	}

	public HashMap<Integer, Restaurant> findAll() {
		return restaurantService.findAll();
	}

	public ResultDTO findById(String id) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateAlphanumericField("Validación id", id, "\\d+", resultDTO);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Restaurant restaurant = restaurantService.findById(Integer.parseInt(id));
		if (restaurant == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("No existe un restaurante con ese ID.");
		} else {
			resultDTO.setRestaurant(restaurant);
		}
		return resultDTO;
	}

	public ResultDTO updateRestaurant(String id, String name, String qualification,
			String address, String phoneNumber, String numberTables) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateAlphanumericField("Validación id", id, "\\d+", resultDTO);
		validateAlphanumericField("Validación name", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		validateAlphanumericField("Validación qualification", qualification, "^\\d+(\\.\\d+)?$", resultDTO);
		validateAlphanumericField("Validación address", address, "^[a-zA-Z0-9ÁÉÍÓÚáéíóúÑñ #.,.-]+$", resultDTO);
		validateAlphanumericField("Validación phoneNumber", phoneNumber, "\\d+", resultDTO);
		validateAlphanumericField("Validación numberTables", numberTables, "\\d+", resultDTO);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Restaurant restaurant = new Restaurant(name, Float.parseFloat(qualification),
				address, Integer.parseInt(id), phoneNumber, Integer.parseInt(numberTables));
		return restaurantService.updateRestaurant(restaurant);
	}

	public ResultDTO deleteRestaurant(String id) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateAlphanumericField("Validación id", id, "\\d+", resultDTO);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		return restaurantService.deleteRestaurant(Integer.parseInt(id));
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

	private ResultDTO validateAlphanumericField(String nameValidation, String field,
			String pattern, ResultDTO resultDTO) {
		if (field == null || field.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El campo " + nameValidation + " no puede ser null ni vacío.");
			return resultDTO;
		}
		if (!field.matches(pattern)) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("Falló la validación: " + nameValidation);
		}
		return resultDTO;
	}
}
