package co.edu.uptc.restaurant.ui.view;

import java.util.HashMap;
import java.util.Scanner;

import co.edu.uptc.restaurant.domain.Restaurant;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.ui.controller.RestaurantController;

public class RestaurantView {

	private RestaurantController restaurantController;
	private Scanner scanner;

	public RestaurantView(RestaurantController restaurantController, Scanner scanner) {
		this.restaurantController = restaurantController;
		this.scanner = scanner;
	}

	public void mostrarMenu() {
		int opcion = -1;
		while (opcion != 0) {
			System.out.println("\n===== MENÚ RESTAURANTES =====");
			System.out.println("1. Agregar restaurante");
			System.out.println("2. Listar restaurantes");
			System.out.println("3. Buscar restaurante por ID");
			System.out.println("4. Actualizar restaurante");
			System.out.println("5. Eliminar restaurante");
			System.out.println("0. Volver");
			System.out.print("Seleccione una opción: ");
			opcion = leerEntero();
			switch (opcion) {
				case 1: agregarRestaurante(); break;
				case 2: listarRestaurantes(); break;
				case 3: buscarRestaurante(); break;
				case 4: actualizarRestaurante(); break;
				case 5: eliminarRestaurante(); break;
				case 0: break;
				default: System.out.println("Opción no válida.");
			}
		}
	}

	private void agregarRestaurante() {
		System.out.println("\n--- Agregar Restaurante ---");
		System.out.print("ID: ");
		int id = leerEntero();
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Calificación (ej: 4.5): ");
		float calificacion = leerFloat();
		System.out.print("Dirección: ");
		String direccion = scanner.nextLine();
		System.out.print("Teléfono: ");
		String telefono = scanner.nextLine();
		System.out.print("Número de mesas: ");
		int mesas = leerEntero();

		Restaurant restaurante = new Restaurant(nombre, calificacion, direccion, id, telefono, mesas);
		ResultDTO result = restaurantController.addRestaurant(restaurante);
		mostrarResultado(result);
	}

	private void listarRestaurantes() {
		System.out.println("\n--- Lista de Restaurantes ---");
		HashMap<Integer, Restaurant> restaurantes = restaurantController.findAll();
		if (restaurantes.isEmpty()) {
			System.out.println("No hay restaurantes registrados.");
			return;
		}
		for (Restaurant r : restaurantes.values()) {
			System.out.println(r);
		}
	}

	private void buscarRestaurante() {
		System.out.println("\n--- Buscar Restaurante ---");
		System.out.print("ID: ");
		int id = leerEntero();
		Restaurant r = restaurantController.findById(id);
		if (r == null) {
			System.out.println("No se encontró ningún restaurante con ese ID.");
		} else {
			System.out.println(r);
		}
	}

	private void actualizarRestaurante() {
		System.out.println("\n--- Actualizar Restaurante ---");
		System.out.print("ID del restaurante a actualizar: ");
		int id = leerEntero();
		System.out.print("Nuevo nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Nueva calificación (ej: 4.5): ");
		float calificacion = leerFloat();
		System.out.print("Nueva dirección: ");
		String direccion = scanner.nextLine();
		System.out.print("Nuevo teléfono: ");
		String telefono = scanner.nextLine();
		System.out.print("Nuevo número de mesas: ");
		int mesas = leerEntero();

		Restaurant restaurante = new Restaurant(nombre, calificacion, direccion, id, telefono, mesas);
		ResultDTO result = restaurantController.updateRestaurant(restaurante);
		mostrarResultado(result);
	}

	private void eliminarRestaurante() {
		System.out.println("\n--- Eliminar Restaurante ---");
		System.out.print("ID del restaurante a eliminar: ");
		int id = leerEntero();
		ResultDTO result = restaurantController.deleteRestaurant(id);
		mostrarResultado(result);
	}

	private void mostrarResultado(ResultDTO result) {
		if (result.isSuccessful()) {
			System.out.println(result.getMessage());
		} else {
			System.out.println("Operación fallida:");
			for (String error : result.getListMessageError()) {
				System.out.println("  - " + error);
			}
		}
	}

	private int leerEntero() {
		while (true) {
			try {
				return Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.print("Ingrese un número válido: ");
			}
		}
	}

	private float leerFloat() {
		while (true) {
			try {
				return Float.parseFloat(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.print("Ingrese un número válido: ");
			}
		}
	}
}