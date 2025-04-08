package com.java.carrental.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.carrental.model.Lease;
import com.java.carrental.model.LeaseType;
import com.java.carrental.model.Vehicle;
import com.java.carrental.util.ConnectionHelper;

public class LeaseManagementDaoImpl implements LeaseManagementDao{

	Connection connection;
	PreparedStatement pst;
	
	public int dateDiff(Date startDate, Date endDate) {
		long ms = endDate.getTime() - startDate.getTime();
		long days = (ms / (1000 * 60 * 60 * 24));
		
		return (int)days;
	}
	
	@Override
	public String CreateLease(Lease lease) throws ClassNotFoundException, SQLException {
		
		Date today = new Date();
		System.out.println(today);
		Date utilDate1 = new Date(lease.getStartDate().getTime());
		Date utilDate2 = new Date(lease.getEndDate().getTime());
		
		if(dateDiff(today,utilDate1) < 0) {
			return "Lease start date can't be yesterday";
		}else if(dateDiff(today, utilDate2) < 0) {
			return "Lease end date can't be yesterday";
		}else if(dateDiff(utilDate1, utilDate2)<0) {
			return "Leave start date can't be greater than leave end date";
		}else {
			
			connection = ConnectionHelper.getConnection();
			String cmd = "insert into lease(LeaseId, vehicleId, customerId, startdate, enddate, type)"+
						"values(?,?,?,?,?,?)";
			pst = connection.prepareStatement(cmd);
			pst.setInt(1, lease.getLeaseId());
			pst.setInt(2, lease.getVehicleId());
			pst.setInt(3, lease.getCustId());
			pst.setDate(4, lease.getStartDate());
			pst.setDate(5, lease.getEndDate());
			pst.setString(6, lease.getLeasetype().toString());
			pst.executeUpdate();
			return "Record inserted..";
			
		}
		

		
	}

	@Override
	public Vehicle findByLeaseId(int leaseId) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "select v.make,v.model "
				+ "from lease l inner join vehicle v "
				+ "on l.vehicleId = v.vehicleId "
				+ "where leaseId = ?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, leaseId);
		ResultSet rs = pst.executeQuery();
		
		Vehicle car = null;
		while(rs.next()) {
			car = new Vehicle();
			//car.setVehicleId(rs.getInt("vehicleId"));
			car.setMake(rs.getString("make"));
			car.setModel(rs.getString("model"));
//			car.setYear(rs.getInt("year"));
//			car.setRate(rs.getDouble("rate"));
//			car.setStatus(rs.getString("status"));
//			car.setPassangercap(rs.getInt("passangercap"));
//			car.setEnginecap(rs.getDouble("enginecap"));
		}
		
		return car;
		
	}

	@Override
	public List<Lease> listActiveLease() throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from lease "
				+ "where enddate > curdate() && startdate <= curdate()";
		pst = connection.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		Lease lease = null;
		List<Lease>leaseList = new ArrayList<Lease>();
		while(rs.next()) {
			lease = new Lease();
			lease.setLeaseId(rs.getInt("LeaseId"));
			lease.setVehicleId(rs.getInt("vehicleId"));
			lease.setCustId(rs.getInt("customerId"));
			lease.setStartDate(rs.getDate("startdate"));
			lease.setEndDate(rs.getDate("enddate"));
			lease.setLeasetype(LeaseType.valueOf(rs.getString("type")));
			leaseList.add(lease);
		}
		return leaseList;
		
	}



}
