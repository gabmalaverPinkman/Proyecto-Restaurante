package co.edu.uptc.restaurant.ui.view;

import java.util.HashMap;
import java.util.Scanner;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.ui.controller.CustomerController;

public class CustomerView {

	private CustomerController customerController;
	private Scanner scanner;

	public CustomerView(CustomerController customerController, Scanner scanner) {
		this.customerController = customerController;
		this.scanner = scanner;
	}

	public void mostrarMenu() {
		int opcion = -1;
		while (opcion != 0) {
			System.out.println("\n===== MENÚ CLIENTES =====");
			System.out.println("1. Agregar cliente");
			System.out.println("2. Listar clientes");
			System.out.println("3. Buscar cliente por DNI");
			System.out.println("4. Actualizar cliente");
			System.out.println("5. Eliminar cliente");
			System.out.println("0. Volver");
			System.out.print("Seleccione una opción: ");
			opcion = leerEntero();
			switch (opcion) {
				case 1: agregarCliente(); break;
				case 2: listarClientes(); break;
				case 3: buscarCliente(); break;
				case 4: actualizarCliente(); break;
				case 5: eliminarCliente(); break;
				case 0: break;
				default: System.out.println("Opción no válida.");
			}
		}
	}

	private void agregarCliente() {
		System.out.println("\n--- Agregar Cliente ---");
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Apellido: ");
		String apellido = scanner.nextLine();
		System.out.print("DNI: ");
		int dni = leerEntero();
		System.out.print("Mesa asignada: ");
		int mesa = leerEntero();

		Customer cliente = new Customer(nombre, apellido, dni, mesa);
		ResultDTO result = customerController.addCustomer(cliente);
		mostrarResultado(result);
	}

	private void listarClientes() {
		System.out.println("\n--- Lista de Clientes ---");
		HashMap<Integer, Customer> clientes = customerController.findAll();
		if (clientes.isEmpty()) {
			System.out.println("No hay clientes registrados.");
			return;
		}
		for (Customer c : clientes.values()) {
			System.out.println(c);
		}
	}

	private void buscarCliente() {
		System.out.println("\n--- Buscar Cliente ---");
		System.out.print("DNI: ");
		int dni = leerEntero();
		Customer c = customerController.findByDni(dni);
		if (c == null) {
			System.out.println("No se encontró ningún cliente con ese DNI.");
		} else {
			System.out.println(c);
		}
	}

	private void actualizarCliente() {
		System.out.println("\n--- Actualizar Cliente ---");
		System.out.print("DNI del cliente a actualizar: ");
		int dni = leerEntero();
		System.out.print("Nuevo nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Nuevo apellido: ");
		String apellido = scanner.nextLine();
		System.out.print("Nueva mesa asignada: ");
		int mesa = leerEntero();

		Customer cliente = new Customer(nombre, apellido, dni, mesa);
		ResultDTO result = customerController.updateCustomer(cliente);
		mostrarResultado(result);
	}

	private void eliminarCliente() {
		System.out.println("\n--- Eliminar Cliente ---");
		System.out.print("DNI del cliente a eliminar: ");
		int dni = leerEntero();
		ResultDTO result = customerController.deleteCustomer(dni);
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
				int valor = Integer.parseInt(scanner.nextLine().trim());
				return valor;
			} catch (NumberFormatException e) {
				System.out.print("Ingrese un número válido: ");
			}
		}
	}
}