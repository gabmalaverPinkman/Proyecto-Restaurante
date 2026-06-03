package co.edu.uptc.restaurant.ui;

import java.util.Scanner;

import co.edu.uptc.restaurant.repository.CustomerRepository;
import co.edu.uptc.restaurant.repository.OrderRepository;
import co.edu.uptc.restaurant.repository.RestaurantRepository;
import co.edu.uptc.restaurant.service.CustomerService;
import co.edu.uptc.restaurant.service.OrderService;
import co.edu.uptc.restaurant.service.RestaurantService;
import co.edu.uptc.restaurant.ui.controller.CustomerController;
import co.edu.uptc.restaurant.ui.controller.OrderController;
import co.edu.uptc.restaurant.ui.controller.RestaurantController;
import co.edu.uptc.restaurant.ui.view.CustomerView;
import co.edu.uptc.restaurant.ui.view.OrderView;
import co.edu.uptc.restaurant.ui.view.RestaurantView;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		CustomerRepository customerRepository = new CustomerRepository();
		OrderRepository orderRepository = new OrderRepository();
		RestaurantRepository restaurantRepository = new RestaurantRepository();

		CustomerService customerService = new CustomerService(customerRepository);
		OrderService orderService = new OrderService(orderRepository);
		RestaurantService restaurantService = new RestaurantService(restaurantRepository);

		CustomerController customerController = new CustomerController(customerService);
		OrderController orderController = new OrderController(orderService);
		RestaurantController restaurantController = new RestaurantController(restaurantService);

		CustomerView customerView = new CustomerView(customerController, scanner);
		OrderView orderView = new OrderView(orderController, customerController, scanner);
		RestaurantView restaurantView = new RestaurantView(restaurantController, scanner);

		int opcion = -1;
		while (opcion != 0) {
			System.out.println("\n=============================");
			System.out.println("   SISTEMA DE RESTAURANTE    ");
			System.out.println("=============================");
			System.out.println("1. Gestionar clientes");
			System.out.println("2. Gestionar pedidos");
			System.out.println("3. Gestionar restaurantes");
			System.out.println("0. Salir");
			System.out.print("Seleccione una opción: ");
			try {
				opcion = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				opcion = -1;
			}
			switch (opcion) {
				case 1: customerView.mostrarMenu(); break;
				case 2: orderView.mostrarMenu(); break;
				case 3: restaurantView.mostrarMenu(); break;
				case 0: System.out.println("Hasta luego"); break;
				default: System.out.println("Opción invalida");
			}
		}

		scanner.close();
	}
}