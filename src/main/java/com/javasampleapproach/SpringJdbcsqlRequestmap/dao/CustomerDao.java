package com.javasampleapproach.SpringJdbcsqlRequestmap.dao;

import java.util.List;
import com.javasampleapproach.SpringJdbcsqlRequestmap.model.Customer;

/**
 * Interfaz de la capa DAO para el modelo de Customer
 * 
 * @author dcabanas
 * @version 29/08/19
 * @see <a href =
 *      "https://grokonez.com/spring-framework/spring-boot/how-to-use-jdbc-template-with-spring-boot-for-postgres-database"
 *      /> How to use Spring JDBC Template with Spring Boot for Postgres
 *      DataBase </a>
 */
public interface CustomerDao {

	/**
	 * Metodo para insertar un cliente Customer en la base de datos PostgreSQL
	 * 
	 * @param customer - Cliente de tipo Customer
	 */
	void insert(Customer customer);

	/**
	 * Metodo para insertar un bloque (coleccion) de cliente Customer en la base de
	 * datos PostgreSQL
	 * 
	 * @param customers - Lista de clientes de tipo List<Customer>
	 */
	void inserBatch(List<Customer> customers);

	/**
	 * Metodo que devuelve todos los clientes Customer de la base de datos
	 * PostgreSQL
	 * 
	 * @return Una Lista (java.utils) de tipo List<Customer> con todos los clientes
	 *         de la base de datos PostgreSQL
	 */
	List<Customer> loadAllCustomer();

	/**
	 * Metodo que devuelve un cliente Customer a partir de su identificador
	 * customerId
	 * 
	 * @param customerId - Identificador del cliente de tipo Integer
	 * @return Cliente a devolver de tipo Customer
	 */
	Customer findCustomerById(int customerId);

	/**
	 * Metodo que devuelve el nombre String de un cliente Customer a partir de su
	 * identificador customerId
	 * 
	 * @param customerId - Identificador de cliente de tipo Integer
	 * @return String - Nombre del cliente de tipo String
	 */
	String findNameById(int customerId);

	/**
	 * Metodo que devuelve el numero Integer total de clientes en la base de datos
	 * PostgreSQL
	 * 
	 * @return El numero total de clientes de tipo Integer
	 */
	int getTotalNumberCustomer();

	/**
	 * Metodo que elimina un cliente Customer de la base de datos PostgreSQL a
	 * partir de su identificador customerId
	 * 
	 * @param customerId - Identificador del cliente Customer de tipo Integer
	 */
	void deleteCustomerById(int customerId);

	/**
	 * Metodo que actualiza la informacion un cliente Customer ya existente en la
	 * base de datos PostgreSQL
	 * 
	 * @param customer - Cliente con la informacion a ser actualizada de tipo
	 *                 Customer
	 */
	void updateCustomerById(Customer customer);

}
