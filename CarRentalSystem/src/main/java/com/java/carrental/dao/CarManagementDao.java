package com.java.carrental.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.carrental.model.Vehicle;

public interface CarManagementDao {
	
	String addCar(Vehicle vehicle) throws ClassNotFoundException, SQLException;
	String removeCar(int vehicleId) throws SQLException, ClassNotFoundException;
	List<Vehicle> listOfAvailCar() throws ClassNotFoundException, SQLException;
	List<Vehicle> rentedCar() throws ClassNotFoundException, SQLException;
	Vehicle findCarById(int vehicleId) throws ClassNotFoundException, SQLException;
}
