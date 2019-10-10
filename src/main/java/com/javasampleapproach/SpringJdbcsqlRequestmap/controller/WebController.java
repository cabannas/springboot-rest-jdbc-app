package com.javasampleapproach.SpringJdbcsqlRequestmap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.SpringJdbcsqlRequestmap.model.Customer;
import com.javasampleapproach.SpringJdbcsqlRequestmap.service.CustomerService;

/**
 * Clase que gestiona y maneja los metodos RequestMap
 * 
 * @author dcabanas
 * @version 30/08/19
 * @see <a href =
 *      "https://grokonez.com/spring-framework/spring-boot/spring-framework-4-3-new-feature-requestmapping-getmapping-postmapping-putmapping-deletemapping"
 *      /> Spring Framework 4.3 New Feature
 *      RequestMapping: @GetMapping, @PostMapping, @PutMapping, @DeleteMapping</a>
 */
@RestController
public class WebController {

	// Capa Service del cliente para gestionar la base de datos PostgreSQL
	@Autowired
	CustomerService customerService;

	// Pseudo-Base de datos local (para hacer pruebas sin usar PostgreSQL)
	Map<Integer, Customer> localCustomerDB = new HashMap<Integer, Customer>();

	// Post Construct para iniciar valores en la base de datos
	@PostConstruct
	public void initIt() throws Exception {

		Customer customer1 = new Customer(1, "Jack", 20);
		Customer customer2 = new Customer(2, "Peter", 25);

		localCustomerDB.put((int) customer1.getCustomerId(), customer1);
		localCustomerDB.put((int) customer2.getCustomerId(), customer2);

		customerService.insert(customer1);
		customerService.insert(customer2);

	}

	/**
	 * Metodo que mappea la funcion GET http para un solo cliente Customer
	 * 
	 * @param customerId - El identificador del cliente de tipo Integer
	 * @return El cliente que se esta buscando
	 */
	@GetMapping("/customers/{customerId}")
	public Customer getMethod(@PathVariable int customerId) {

		return customerService.getCustomerById(Integer.valueOf(customerId));

		// return localCustomerDB.get(customerId);
	}

	/**
	 * Metodo que mappea la funcion GET http para todos los clientes de la base de
	 * datos
	 * 
	 * @return Una lista de tipo List<Customer> con todos los clientes de la base de
	 *         datos
	 */
	@GetMapping("/customers")
	public List<Customer> getMethod() {

		return customerService.loadAllCustomer();

		// return localCustomerDB.get(customerId);
	}

	/**
	 * Metodo que mappea la funcion POST http para introducir clientes en la base de
	 * datos
	 * 
	 * @param customer - El cliente a introducir de tipo Customer
	 * @return El cliente introducido de tipo Customer
	 */
	@PostMapping("customers/post")
	public Customer postMethod(@RequestBody Customer customer) {

		// Control de errores del identificador del cliente
		Random r = new Random();
		if (customer.getCustomerId() <= 0) {
			customer.setCustomerId(r.nextInt());
		}

		// localCustomerDB
		// localCustomerDB.put((int) customer.getCustomerId(), customer);

		// JDBC
		customerService.insert(customer);

		// Log out localCustomerDB despues del post POST
		System.out.println("Customer Stores after POST:");
		localCustomerDB.forEach((id, cust) -> System.out.println(cust.toString()));

		return customer;
	}

	/**
	 * Metodo que mappea la funcion PUT http para actualizar la informacion de un
	 * cliente ya existente a traves de su identificador
	 * 
	 * @param customerId - El identificador del cliente de tipo Integer
	 * @param customer   - El cliente de tipo Customer con su nueva informacion
	 * @return El cliente de tipo Customer con su informacion ya actualizada
	 */
	@PutMapping("/customers/edit/{customerId}")
	public Customer putMethod(@PathVariable int customerId, @RequestBody Customer customer) {

		try {
			// PUT processing localCustomerDB
			localCustomerDB.remove(customerId);

			customer.setCustomerId(customerId);

			localCustomerDB.put(customerId, customer);
			// JDBC
			customerService.updateCustomerById(customer);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}

		// Log out localCustomerDB despues del PUT
		System.out.println("Customer Stores after PUT");
		localCustomerDB.forEach((id, cust) -> System.out.println(cust.toString()));

		return customer;
	}

	/**
	 * Metodo que mappea la funcion DELETE http al eliminar a un cliente de la base
	 * de datos a traves de su identificador
	 * 
	 * @param customerId - El identificador del cliente de tipo Integer
	 * @return Una cadena explicativa del estatus del proceso
	 */
	@DeleteMapping("/customers/delete/{customerId}")
	public String deleteMethod(@PathVariable int customerId) {
		try {
			// DELETE processing
			localCustomerDB.remove(customerId);
			// JBDC
			customerService.deleteCustomerById(customerId);
		} catch (Exception e) {
			return "Error";
		}

		// Log out localCustomerDB despues del DELETE
		System.out.println("Customer Stores after DELETE");
		localCustomerDB.forEach((id, cust) -> System.out.println(cust.toString()));

		return "Done";
	}

}
