package co.edu.uptc.restaurant.ui.view;

import java.util.HashMap;
import java.util.Scanner;

import co.edu.uptc.restaurant.domain.Order;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.ui.controller.OrderController;

public class OrderView {

	private OrderController orderController;
	private Scanner scanner;

	public OrderView(OrderController orderController, Scanner scanner) {
		this.orderController = orderController;
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
		String id = scanner.nextLine().trim();
		System.out.print("Fecha (AAAA-MM-DD): ");
		String fecha = scanner.nextLine().trim();
		System.out.print("Plato: ");
		String plato = scanner.nextLine();
		System.out.print("Costo total: ");
		String costo = scanner.nextLine().trim();
		System.out.print("DNI del cliente: ");
		String dni = scanner.nextLine().trim();

		ResultDTO result = orderController.addOrder(id, fecha, costo, plato, dni);
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
		String id = scanner.nextLine().trim();
		ResultDTO result = orderController.findById(id);
		if (result.isSuccessful()) {
			System.out.println(result.getOrder());
		} else {
			mostrarResultado(result);
		}
	}

	private void actualizarPedido() {
		System.out.println("\n--- Actualizar Pedido ---");
		System.out.print("ID del pedido a actualizar: ");
		String id = scanner.nextLine().trim();
		System.out.print("Nueva fecha (AAAA-MM-DD): ");
		String fecha = scanner.nextLine().trim();
		System.out.print("Nuevo plato: ");
		String plato = scanner.nextLine();
		System.out.print("Nuevo costo total: ");
		String costo = scanner.nextLine().trim();
		System.out.print("DNI del cliente: ");
		String dni = scanner.nextLine().trim();

		ResultDTO result = orderController.updateOrder(id, fecha, costo, plato, dni);
		mostrarResultado(result);
	}

	private void eliminarPedido() {
		System.out.println("\n--- Eliminar Pedido ---");
		System.out.print("ID del pedido a eliminar: ");
		String id = scanner.nextLine().trim();
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
}
