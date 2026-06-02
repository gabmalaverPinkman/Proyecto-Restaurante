package co.edu.uptc.restaurant.ui.controller;

import java.time.LocalDate;
import java.util.HashMap;

import co.edu.uptc.restaurant.domain.Customer;
import co.edu.uptc.restaurant.domain.Order;
import co.edu.uptc.restaurant.dto.ResultDTO;
import co.edu.uptc.restaurant.service.CustomerService;
import co.edu.uptc.restaurant.service.OrderService;

public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        super();
        this.orderService = orderService;
        this.customerService = customerService;
    }

    public ResultDTO addOrder(String idOrder, String date, String totalCost, String dish, String dniCustomer) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("Validación idOrder", idOrder, "\\d+", resultDTO);
        validateAlphanumericField("Validación date", date, "^\\d{4}-\\d{2}-\\d{2}$", resultDTO);
        validateAlphanumericField("Validación totalCost", totalCost, "^\\d+(\\.\\d+)?$", resultDTO);
        validateAlphanumericField("Validación dish", dish, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
        validateAlphanumericField("Validación dniCustomer", dniCustomer, "\\d+", resultDTO);

        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }

        Customer customer = customerService.findByDni(Integer.parseInt(dniCustomer));
        if (customer == null) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("No existe un cliente con ese DNI");
            return resultDTO;
        }

        boolean result = orderService.addOrder(new Order(Integer.parseInt(idOrder), LocalDate.parse(date), Double.parseDouble(totalCost), dish, customer
        ));
        if (!result) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("Ya existe una orden con ese ID");
        }
        return resultDTO;
    }

    public HashMap<Integer, Order> findAll() {
        return orderService.findAll();
    }

    public ResultDTO findById(String idOrder) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("Validación idOrder", idOrder, "\\d+", resultDTO);
        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }
        resultDTO.setOrder(orderService.findById(Integer.parseInt(idOrder)));
        return resultDTO;
    }

    public ResultDTO updateOrder(String idOrder, String date, String totalCost, String dish, String dniCustomer) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("Validación idOrder", idOrder, "\\d+", resultDTO);
        validateAlphanumericField("Validación date", date, "^\\d{4}-\\d{2}-\\d{2}$", resultDTO);
        validateAlphanumericField("Validación totalCost", totalCost, "^\\d+(\\.\\d+)?$", resultDTO);
        validateAlphanumericField("Validación dish", dish, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
        validateAlphanumericField("Validación dniCustomer", dniCustomer, "\\d+", resultDTO);

        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }

        Customer customer = customerService.findByDni(Integer.parseInt(dniCustomer));
        if (customer == null) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("No existe un cliente con ese DNI");
            return resultDTO;
        }

        boolean result = orderService.updateOrder(new Order(
                Integer.parseInt(idOrder),
                LocalDate.parse(date),
                Double.parseDouble(totalCost),
                dish,
                customer
        ));
        if (!result) {
            resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("La orden no fue encontrada.");
        } else {
            resultDTO.setMessage("Se actualizó el registro de la orden");
        }
        return resultDTO;
    }

    public ResultDTO deleteOrder(String idOrder) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccessful(true);

        validateAlphanumericField("Validación idOrder", idOrder, "\\d+", resultDTO);
        if (!resultDTO.isSuccessful()) {
            return resultDTO;
        }

        boolean result = this.orderService.deleteOrder(Integer.parseInt(idOrder));
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
