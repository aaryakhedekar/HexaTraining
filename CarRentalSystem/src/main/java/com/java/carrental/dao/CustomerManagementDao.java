package com.java.carrental.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.carrental.model.Customer;

public interface CustomerManagementDao {
	
	String addCustomer(Customer customer) throws ClassNotFoundException, SQLException;
	String removeCustomer(int custId) throws ClassNotFoundException, SQLException;
	List<Customer> listCustomer() throws ClassNotFoundException, SQLException;
	Customer customer(int custId) throws ClassNotFoundException, SQLException;

}
