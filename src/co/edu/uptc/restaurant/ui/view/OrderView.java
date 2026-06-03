package co.edu.uptc.restaurant.ui.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.domain.Order;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.ui.controller.CustomerController;
import co.edu.uptc.restaurant.ui.controller.OrderController;

public class OrderView {

	private OrderController orderController;
	private CustomerController customerController;
	private Scanner scanner;

	public OrderView(OrderController orderController, CustomerController customerController, Scanner scanner) {
		this.orderController = orderController;
		this.customerController = customerController;
		this.scanner = scanner;
	}

	public void mostrarMenu() {
		int opcion = -1;
		while (opcion != 0) {
			System.out.println("\n===== MENÚ PEDIDOS =====");
			System.out.println("1. Agregar pedido");
			System.out.println("2. Listar pedidos");
			System.out.println("3. Buscar pedido por ID");
			System.out.println("4. Actualizar pedido");
			System.out.println("5. Eliminar pedido");
			System.out.println("0. Volver");
			System.out.print("Seleccione una opción: ");
			opcion = leerEntero();
			switch (opcion) {
				case 1: agregarPedido(); break;
				case 2: listarPedidos(); break;
				case 3: buscarPedido(); break;
				case 4: actualizarPedido(); break;
				case 5: eliminarPedido(); break;
				case 0: break;
				default: System.out.println("Opción no válida.");
			}
		}
	}

	private void agregarPedido() {
		System.out.println("\n--- Agregar Pedido ---");
		System.out.print("ID del pedido: ");
		int id = leerEntero();
		System.out.print("Fecha (AAAA-MM-DD): ");
		LocalDate fecha = leerFecha();
		System.out.print("Plato: ");
		String plato = scanner.nextLine();
		System.out.print("Costo total: ");
		double costo = leerDouble();
		System.out.print("DNI del cliente: ");
		int dni = leerEntero();

		Customer cliente = customerController.findByDni(dni);
		if (cliente == null) {
			System.out.println("✗ No existe un cliente con ese DNI.");
			return;
		}

		Order pedido = new Order(id, fecha, costo, plato, cliente);
		ResultDTO result = orderController.addOrder(pedido);
		mostrarResultado(result);
	}

	private void listarPedidos() {
		System.out.println("\n--- Lista de Pedidos ---");
		HashMap<Integer, Order> pedidos = orderController.findAll();
		if (pedidos.isEmpty()) {
			System.out.println("No hay pedidos registrados.");
			return;
		}
		for (Order o : pedidos.values()) {
			System.out.println(o);
		}
	}

	private void buscarPedido() {
		System.out.println("\n--- Buscar Pedido ---");
		System.out.print("ID del pedido: ");
		int id = leerEntero();
		Order o = orderController.findById(id);
		if (o == null) {
			System.out.println("No se encontró ningún pedido con ese ID.");
		} else {
			System.out.println(o);
		}
	}

	private void actualizarPedido() {
		System.out.println("\n--- Actualizar Pedido ---");
		System.out.print("ID del pedido a actualizar: ");
		int id = leerEntero();
		System.out.print("Nueva fecha (AAAA-MM-DD): ");
		LocalDate fecha = leerFecha();
		System.out.print("Nuevo plato: ");
		String plato = scanner.nextLine();
		System.out.print("Nuevo costo total: ");
		double costo = leerDouble();
		System.out.print("DNI del cliente: ");
		int dni = leerEntero();

		Customer cliente = customerController.findByDni(dni);
		if (cliente == null) {
			System.out.println("✗ No existe un cliente con ese DNI.");
			return;
		}

		Order pedido = new Order(id, fecha, costo, plato, cliente);
		ResultDTO result = orderController.updateOrder(pedido);
		mostrarResultado(result);
	}

	private void eliminarPedido() {
		System.out.println("\n--- Eliminar Pedido ---");
		System.out.print("ID del pedido a eliminar: ");
		int id = leerEntero();
		ResultDTO result = orderController.deleteOrder(id);
		mostrarResultado(result);
	}

	private void mostrarResultado(ResultDTO result) {
		if (result.isSuccessful()) {
			System.out.println("✓ " + result.getMessage());
		} else {
			System.out.println("✗ Operación fallida:");
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

	private double leerDouble() {
		while (true) {
			try {
				return Double.parseDouble(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.print("Ingrese un número válido: ");
			}
		}
	}

	private LocalDate leerFecha() {
		while (true) {
			try {
				return LocalDate.parse(scanner.nextLine().trim());
			} catch (DateTimeParseException e) {
				System.out.print("Formato inválido, use AAAA-MM-DD: ");
			}
		}
	}
}