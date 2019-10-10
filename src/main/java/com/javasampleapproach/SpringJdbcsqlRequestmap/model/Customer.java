package com.javasampleapproach.SpringJdbcsqlRequestmap.model;

import java.io.Serializable;

/**
 * Clase modelo para representar a un cliente generico Customer
 * 
 * @author dcabanas
 * @version 29/08/19
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	int customerId;
	String name;
	int age;

	/**
	 * Primer constructor de la clase, sin valores
	 */
	public Customer() {
	}

	/**
	 * Segundo constructor de la clase
	 * 
	 * @param customerId - Identificador del cliente de tipo Integer
	 * @param name       - Nombre del cliente de tipo String
	 * @param age        - Edad del cliente de tipo Integer
	 */
	public Customer(int customerId, String name, int age) {
		this.customerId = customerId;
		this.name = name;
		this.age = age;
	}

	/**
	 * Getter del identificador del cliente
	 * 
	 * @return El identificador del cliente de tipo Integer
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * Setter del identificador del cliente
	 * 
	 * @param customerId - El identificador del cliente de tipo Integer
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * Getter del nombre del cliente
	 * 
	 * @return El nombre del cliente de tipo String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter del nombre del cliente
	 * 
	 * @param name - El nombre del cliente de tipo String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter de la edad del cliente
	 * 
	 * @return La edad del cliente de tipo Integer
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter de la edad del cliente
	 * 
	 * @param age - La edad del cliente de tipo Integer
	 */
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [age=" + age + ", customerId=" + customerId + ", name=" + name + "]";
	}
}