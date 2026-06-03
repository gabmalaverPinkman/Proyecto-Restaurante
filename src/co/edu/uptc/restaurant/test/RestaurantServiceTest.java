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
	void addRestaurant_datosValidos_retornaExito() {
		ResultDTO result = restaurantService.addRestaurant(crearRestauranteValido());
		assertTrue(result.isSuccessful());
		assertTrue(restaurantRepository.existById(1));
	}

	@Test
	void addRestaurant_idDuplicado_retornaFallo() {
		restaurantService.addRestaurant(crearRestauranteValido());
		ResultDTO result = restaurantService.addRestaurant(crearRestauranteValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addRestaurant_idNegativo_retornaFallo() {
		Restaurant r = crearRestauranteValido();
		r.setId(-1);
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addRestaurant_nombreVacio_retornaFallo() {
		Restaurant r = crearRestauranteValido();
		r.setName("");
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void addRestaurant_direccionVacia_retornaFallo() {
		Restaurant r = crearRestauranteValido();
		r.setAddress("");
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addRestaurant_telefonoVacio_retornaFallo() {
		Restaurant r = crearRestauranteValido();
		r.setPhoneNumber("");
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
	}

	@Test
	void addRestaurant_mesasNegativas_retornaFallo() {
		Restaurant r = crearRestauranteValido();
		r.setNumberTables(-1);
		ResultDTO result = restaurantService.addRestaurant(r);
		assertFalse(result.isSuccessful());
	}

	@Test
	void findAll_repositorioVacio_retornaMapaVacio() {
		assertTrue(restaurantService.findAll().isEmpty());
	}

	@Test
	void findAll_conUnRestaurante_retornaUno() {
		restaurantService.addRestaurant(crearRestauranteValido());
		assertEquals(1, restaurantService.findAll().size());
	}

	@Test
	void findById_existente_retornaRestaurante() {
		restaurantService.addRestaurant(crearRestauranteValido());
		Restaurant r = restaurantService.findById(1);
		assertNotNull(r);
		assertEquals("El Corral", r.getName());
	}

	@Test
	void findById_inexistente_retornaNull() {
		assertNull(restaurantService.findById(999));
	}

	@Test
	void updateRestaurant_existente_retornaExito() {
		restaurantService.addRestaurant(crearRestauranteValido());
		Restaurant actualizado = new Restaurant("El Corral Actualizado", 4.8f, "Cra 7 # 32-10", 1, "3001234567", 15);
		ResultDTO result = restaurantService.updateRestaurant(actualizado);
		assertTrue(result.isSuccessful());
		assertEquals("El Corral Actualizado", restaurantService.findById(1).getName());
	}

	@Test
	void updateRestaurant_inexistente_retornaFallo() {
		ResultDTO result = restaurantService.updateRestaurant(crearRestauranteValido());
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void updateRestaurant_datosInvalidos_retornaFallo() {
		restaurantService.addRestaurant(crearRestauranteValido());
		Restaurant invalido = new Restaurant("", 4.5f, "Cra 7 # 32-10", 1, "3001234567", 10);
		ResultDTO result = restaurantService.updateRestaurant(invalido);
		assertFalse(result.isSuccessful());
	}

	@Test
	void deleteRestaurant_existente_retornaExito() {
		restaurantService.addRestaurant(crearRestauranteValido());
		ResultDTO result = restaurantService.deleteRestaurant(1);
		assertTrue(result.isSuccessful());
		assertFalse(restaurantRepository.existById(1));
	}

	@Test
	void deleteRestaurant_inexistente_retornaFallo() {
		ResultDTO result = restaurantService.deleteRestaurant(999);
		assertFalse(result.isSuccessful());
		assertFalse(result.getListMessageError().isEmpty());
	}

	@Test
	void existById_existente_retornaTrue() {
		restaurantService.addRestaurant(crearRestauranteValido());
		assertTrue(restaurantService.existById(1));
	}

	@Test
	void existById_inexistente_retornaFalse() {
		assertFalse(restaurantService.existById(999));
	}
}
