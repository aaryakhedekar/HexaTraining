package com.java.carrental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.carrental.model.Customer;
import com.java.carrental.util.ConnectionHelper;

public class CustomerManagementDaoImpl implements CustomerManagementDao{

	Connection connection;
	PreparedStatement pst; 
	
	@Override
	public String addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "Insert into customer(CustomerId, firstname, lastname, email, phonenumber) "
					  + "values (?,?,?,?,?)";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, customer.getCustId());
		pst.setString(2, customer.getFirstname());
		pst.setString(3, customer.getLastname());
		pst.setString(4, customer.getEmail());
		pst.setString(5,customer.getPhno());
		pst.executeUpdate();
		
		return "Customer record inserted..";
	}

	@Override
	public String removeCustomer(int custId) throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "delete from customer where customerId = ?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, custId);
		pst.executeUpdate();
		return "Record deleted..";
				
	}

	@Override
	public List<Customer> listCustomer() throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from customer";
		pst = connection.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		Customer cust = null;
		List<Customer> custList = new ArrayList<Customer>();
		while(rs.next()) {
			cust = new Customer();
			cust.setCustId(rs.getInt("CustomerId"));
			cust.setFirstname(rs.getString("firstname"));
			cust.setLastname(rs.getString("lastname"));
			cust.setEmail(rs.getString("email"));
			cust.setPhno(rs.getString("phonenumber"));
			custList.add(cust);
		}
		
		return custList;
		
	}

	@Override
	public Customer customer(int custId) throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from customer where custId =?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, custId);
		ResultSet rs = pst.executeQuery();
		Customer cust = null;
		while(rs.next()) {
			cust = new Customer();
			cust.setCustId(rs.getInt("CustomerId"));
			cust.setFirstname(rs.getString("firstname"));
			cust.setLastname(rs.getString("lastname"));
			cust.setEmail(rs.getString("email"));
			cust.setPhno(rs.getString("phonenumber"));
			
		}
		return cust;
	}

}
