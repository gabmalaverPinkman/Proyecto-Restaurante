package co.edu.uptc.restaurant.ui.controller;

import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Restaurant;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.service.RestaurantService;

public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        super();
        this.restaurantService = restaurantService;
    }

    public ResultDTO addRestaurant(String id, String name, String qualification,
            String address, String phoneNumber, String numberTables) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("Validación id", id, "\\d+", resultDTO);
        validateAlphanumericField("Validación name", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
        validateAlphanumericField("Validación qualification", qualification, "^\\d+(\\.\\d+)?$", resultDTO);
        validateAlphanumericField("Validación address", address, "^[a-zA-Z0-9ÁÉÍÓÚáéíóúÑñ #.-]+$", resultDTO);
        validateAlphanumericField("Validación phoneNumber", phoneNumber, "\\d+", resultDTO);
        validateAlphanumericField("Validación numberTables", numberTables, "\\d+", resultDTO);

        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }

        boolean result = restaurantService.addRestaurant(new Restaurant(
                name,
                Float.parseFloat(qualification),
                address,
                Integer.parseInt(id),
                phoneNumber,
                Integer.parseInt(numberTables)
        ));
        if (!result) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("Ya existe un restaurante con ese ID");
        }
        return resultDTO;
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
        resultDTO.setRestaurant(restaurantService.findById(Integer.parseInt(id)));
        return resultDTO;
    }

    public ResultDTO updateRestaurant(String id, String name, String qualification,
            String address, String phoneNumber, String numberTables) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("Validación id", id, "\\d+", resultDTO);
        validateAlphanumericField("Validación name", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
        validateAlphanumericField("Validación qualification", qualification, "^\\d+(\\.\\d+)?$", resultDTO);
        validateAlphanumericField("Validación address", address, "^[a-zA-Z0-9ÁÉÍÓÚáéíóúÑñ #.-]+$", resultDTO);
        validateAlphanumericField("Validación phoneNumber", phoneNumber, "\\d+", resultDTO);
        validateAlphanumericField("Validación numberTables", numberTables, "\\d+", resultDTO);

        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }

        boolean result = restaurantService.updateRestaurant(new Restaurant(
                name,
                Float.parseFloat(qualification),
                address,
                Integer.parseInt(id),
                phoneNumber,
                Integer.parseInt(numberTables)
        ));
        if (!result) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("El restaurante no fue encontrado.");
        } else {
            resultDTO.setMessage("Se actualizó el registro del restaurante");
        }
        return resultDTO;
    }

    public ResultDTO deleteRestaurant(String id) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("Validación id", id, "\\d+", resultDTO);
        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }

        boolean result = this.restaurantService.deleteRestaurant(Integer.parseInt(id));
        if (!result) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("El registro no se pudo eliminar");
            return resultDTO;
        }
        resultDTO.setMessage("El registro fue eliminado");
        return resultDTO;
    }

    private ResultDTO validateAlphanumericField(String nameValidation, String field,
            String pattern, ResultDTO resultDTO) {
        if (field == null || field.trim().isEmpty()) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("El campo " + nameValidation + " no puede ser null ni vacío");
            return resultDTO;
        }
        boolean result = field.matches(pattern);
        if (!result) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("Falló la validación: " + nameValidation);
        }
        return resultDTO;
    }
}