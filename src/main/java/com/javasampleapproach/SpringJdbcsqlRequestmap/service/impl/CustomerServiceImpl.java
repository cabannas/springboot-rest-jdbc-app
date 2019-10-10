package com.javasampleapproach.SpringJdbcsqlRequestmap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javasampleapproach.SpringJdbcsqlRequestmap.dao.CustomerDao;
import com.javasampleapproach.SpringJdbcsqlRequestmap.model.Customer;
import com.javasampleapproach.SpringJdbcsqlRequestmap.service.CustomerService;

/**
 * Clase de implementacion de la interfaz de la capa Service para el modelo
 * Customer
 * 
 * @author dcabanas
 * @version 30/08/19
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public void insert(Customer customer) {
		customerDao.insert(customer);
	}

	@Override
	public void insertBatch(List<Customer> customers) {
		customerDao.inserBatch(customers);
	}

	@Override
	public List<Customer> loadAllCustomer() {
		List<Customer> listCust = customerDao.loadAllCustomer();
		for (Customer customer : listCust) {
			System.out.println(customer.toString());
		}
		return listCust;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Customer customer = customerDao.findCustomerById(customerId);
		System.out.println(customer);
		return customer;
	}

	@Override
	public String getCustomerNameById(int customerId) {
		String name = customerDao.findNameById(customerId);
		System.out.println("Customer's name = " + name);
		return name;
	}

	@Override
	public void getTotalNumerCustomer() {
		int totalNumberCustomer = customerDao.getTotalNumberCustomer();
		System.out.println("Total Number Customer is: " + totalNumberCustomer);
	}

	@Override
	public void deleteCustomerById(int customerId) {
		customerDao.deleteCustomerById(customerId);

	}

	@Override
	public void updateCustomerById(Customer customer) {
		customerDao.updateCustomerById(customer);

	}

}
