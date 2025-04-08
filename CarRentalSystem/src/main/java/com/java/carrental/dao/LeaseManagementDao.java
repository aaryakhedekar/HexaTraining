package com.java.carrental.dao;


import java.sql.SQLException;
import java.util.List;

import com.java.carrental.model.Lease;
import com.java.carrental.model.Vehicle;

public interface LeaseManagementDao {
	
	String CreateLease(Lease lease) throws ClassNotFoundException, SQLException;
	Vehicle findByLeaseId (int leaseId) throws ClassNotFoundException, SQLException;
	List<Lease> listActiveLease() throws ClassNotFoundException, SQLException;
	
}
