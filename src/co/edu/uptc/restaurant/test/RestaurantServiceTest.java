package co.edu.uptc.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.restaurant.domain.Restaurant;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.repository.RestaurantRepository;
import co.edu.uptc.restaurant.service.RestaurantService;

class RestaurantServiceTest {

	private RestaurantRepository restaurantRepository;
	private RestaurantService restaurantService;

	private Restaurant crearRestauranteValido() {
		return new Restaurant("El Corral", 4.5f, "Cra 7 # 32-10", 1, "3001234567", 10);
	}

	@BeforeEach
	void setUp() {
		restaurantRepository = new RestaurantRepository();
		restaurantService = new RestaurantService(restaurantRepository);
	}

	@Test
	void addRestaurantTrue() {
		ResultDTO result = restaurantService.addRestaurant(crearRestauranteValido());
		assertTrue(result.isSuccessful());
		assertTrue(restaurantRepository.existById(1));
	}

	@Test
	void addRestaurantFailedDuplicatedId() {
		restaurantService.addRestaurant(crearRestauranteValido());
		ResultDTO result = restaurantService.addRestaurant(crearRestauranteValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addRestaurantFailedNegativeId() {
		Restaurant r = crearRestauranteValido();
		r.setId(-1);
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addRestaurantFailedNoName() {
		Restaurant r = crearRestauranteValido();
		r.setName("");
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addRestaurantFailedNoAddress() {
		Restaurant r = crearRestauranteValido();
		r.setAddress("");
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addRestaurantFailedNoPhoneNumber() {
		Restaurant r = crearRestauranteValido();
		r.setPhoneNumber("");
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addRestaurantFailedNegatieTables() {
		Restaurant r = crearRestauranteValido();
		r.setNumberTables(-1);
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
	}

	@Test
	void findByIdTrue() {
		restaurantService.addRestaurant(crearRestauranteValido());
		Restaurant r = restaurantService.findById(1);
		assertNotNull(r);
		assertEquals("El Corral", r.getName());
	}

	@Test
	void findByIdFailed() {
		assertNull(restaurantService.findById(999));
	}

	@Test
	void updateRestaurantTrue() {
		restaurantService.addRestaurant(crearRestauranteValido());
		Restaurant actualizado = new Restaurant("El Corral Actualizado", 4.8f, "Cra 7 # 32-10", 1, "3001234567", 15);
		ResultDTO result = restaurantService.updateRestaurant(actualizado);
		assertTrue(result.isSuccessful());
		assertEquals("El Corral Actualizado", restaurantService.findById(1).getName());
	}

	@Test
	void updateRestaurantFailed() {
		ResultDTO result = restaurantService.updateRestaurant(crearRestauranteValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void updateRestauratFailedInvalidData() {
		restaurantService.addRestaurant(crearRestauranteValido());
		Restaurant invalido = new Restaurant("", 4.5f, "Cra 7 # 32-10", 1, "3001234567", 10);
		ResultDTO result = restaurantService.updateRestaurant(invalido);
		assertFalse(result.isSuccessful());
	}

	@Test
	void deleteRestaurantTrue() {
		restaurantService.addRestaurant(crearRestauranteValido());
		ResultDTO result = restaurantService.deleteRestaurant(1);
		assertTrue(result.isSuccessful());
		assertFalse(restaurantRepository.existById(1));
	}

	@Test
	void deleteRestaurantFailed() {
		ResultDTO result = restaurantService.deleteRestaurant(999);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void existByIdTrue() {
		restaurantService.addRestaurant(crearRestauranteValido());
		assertTrue(restaurantService.existById(1));
	}

	@Test
	void existByIdFailed() {
		assertFalse(restaurantService.existById(999));
	}
}
