package com.javasampleapproach.SpringJdbcsqlRequestmap.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.SpringJdbcsqlRequestmap.dao.CustomerDao;
import com.javasampleapproach.SpringJdbcsqlRequestmap.model.Customer;

/**
 * Clase de implementacion de la interfaz de la capa DAO para el modelo de
 * Customer
 * 
 * @author dcabanas
 * @version 29/08/19
 */
@Repository
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao {

	@Autowired
	DataSource dataSource; // Datos de inicializacion especificados en applicaction.properties

	/**
	 * Metodo que se utiliza para establecer los datos de inicializacion de la base
	 * de datos PostgreSQL
	 */
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public void insert(Customer customer) {
		// SQL
		String sql = "INSERT INTO customer " + "(customerId, NAME, AGE) VALUES (?, ?, ?)";

		// JdbcTemplate
		getJdbcTemplate().update(sql, new Object[] { customer.getCustomerId(), customer.getName(), customer.getAge() });
	}

	@Override
	public void inserBatch(List<Customer> customers) {
		// SQL
		String sql = "INSERT INTO customer " + "(customerId, NAME, AGE) VALUES (?, ?, ?)";
		// JdbcTemplate
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

			// setValues establece los atributos de cada cliente
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Customer customer = customers.get(i);
				ps.setInt(1, customer.getCustomerId());
				ps.setString(2, customer.getName());
				ps.setInt(3, customer.getAge());
			}

			// getBatchSize devuelve el tamanio de la coleccion de clientes
			public int getBatchSize() {
				return customers.size();
			}
		});

	}

	@Override
	public List<Customer> loadAllCustomer() {
		// SQL
		String sql = "SELECT * FROM customer";
		// JdbcTemplate
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Customer> result = new ArrayList<Customer>();
		// bucle para establecer los atributos de los clientes dentro de la lista
		for (Map<String, Object> row : rows) {
			Customer customer = new Customer();
			customer.setCustomerId((Integer) row.get("customerId"));
			customer.setName((String) row.get("name"));
			customer.setAge((Integer) row.get("age"));
			result.add(customer);
		}
		return result;
	}

	@Override
	public Customer findCustomerById(int customerId) {
		// SQL
		String sql = "SELECT * FROM customer WHERE customerId = ?";
		// JdbcTemplate
		return (Customer) getJdbcTemplate().queryForObject(sql, new Object[] { customerId }, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("customerId"));
				customer.setName(rs.getString("name"));
				customer.setAge(rs.getInt("age"));
				return customer;
			}
		});
	}

	@Override
	public String findNameById(int customerId) {
		// SQL
		String sql = "SELECT name FROM customer WHERE customerId = ?";
		// JdbcTemplate
		return getJdbcTemplate().queryForObject(sql, new Object[] { customerId }, String.class);
	}

	@Override
	public int getTotalNumberCustomer() {
		// SQL
		String sql = "SELECT Count(*) FROM customer";
		// JdbcTemplate
		int total = getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public void deleteCustomerById(int customerId) {
		// SQL
		String sql = "DELETE FROM customer WHERE customerId = ?";
		// JdbcTemplate
		getJdbcTemplate().update(sql, customerId);

	}

	@Override
	public void updateCustomerById(Customer customer) {
		// SQL
		String sql = "UPDATE customer SET name = ?, age = ? WHERE customerId = ?";
		// JdbcTemplate
		getJdbcTemplate().update(sql, customer.getName(), customer.getAge(), customer.getCustomerId());

	}

}
