package com.java.carrental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.carrental.model.Vehicle;
import com.java.carrental.util.ConnectionHelper;

public class CarManagementDaoImpl implements CarManagementDao{
	
	Connection connection;
	PreparedStatement pst;

	
	@Override
	public String addCar(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "Insert into vehicle (VehicleId, make, model, year, dailyRate, status, passengerCapcity, engineCapacity )" +
					"values (?,?,?,?,?,?,?,?)";
		
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, vehicle.getVehicleId());
		pst.setString(2, vehicle.getMake());
		pst.setString(3, vehicle.getModel());
		pst.setInt(4, vehicle.getYear());
		pst.setDouble(5, vehicle.getRate());
		pst.setString(6, vehicle.getStatus());
		pst.setInt(7, vehicle.getPassangercap());
		pst.setDouble(8, vehicle.getEnginecap());
		pst.executeUpdate();
		return "Record updated";
				
				
	}


	@Override
	public String removeCar(int vehicleId) throws SQLException, ClassNotFoundException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "delete from vehicle where VehicleId = ?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, vehicleId);
		pst.executeUpdate();
		return "Record deleted..";
		
	}


	@Override
	public List<Vehicle> listOfAvailCar() throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from vehicle where status = 'Available'";
		pst = connection.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		List<Vehicle> carlist = new ArrayList<Vehicle>();
		Vehicle car = null;
		while(rs.next()) {
			car = new Vehicle();
			car.setVehicleId(rs.getInt("VehicleId"));
			car.setMake(rs.getString("make"));
			car.setModel(rs.getString("model"));
			car.setYear(rs.getInt("year"));
			car.setRate(rs.getDouble("dailyRate"));
			car.setStatus(rs.getString("status"));
			car.setPassangercap(rs.getInt("passengerCapcity"));
			car.setEnginecap(rs.getDouble("engineCapacity"));
			carlist.add(car);
			
		}
		return carlist;
	}


	@Override
	public List<Vehicle> rentedCar() throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "select v.vehicleid,make,model "
				+ "from vehicle v inner join lease l "
				+ "where v.vehicleid = l.vehicleid";
		pst = connection.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		List<Vehicle> carlist = new ArrayList<Vehicle>();
		Vehicle car = null;
		while(rs.next()) {
			car = new Vehicle();
			car.setVehicleId(rs.getInt("vehicleid"));
			car.setMake(rs.getString("make"));
			car.setModel(rs.getString("model"));
			carlist.add(car);
		}
		
		return carlist;
	}


	@Override
	public Vehicle findCarById(int vehicleId) throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from vehicle where vehicleId = ?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, vehicleId);
		ResultSet rs = pst.executeQuery();
		Vehicle car = null;
		while(rs.next()) {
			car = new Vehicle();
			car.setVehicleId(rs.getInt("vehicleId"));
			car.setMake(rs.getString("make"));
			car.setModel(rs.getString("model"));
			car.setYear(rs.getInt("year"));
			car.setRate(rs.getDouble("dailyRate"));
			car.setStatus(rs.getString("status"));
			car.setPassangercap(rs.getInt("passengerCapcity"));
			car.setEnginecap(rs.getDouble("engineCapacity"));
		}
		return car;
		
	}
	
	

}
