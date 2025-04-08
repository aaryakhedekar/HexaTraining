package com.java.carrental.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Lease {
	
	private int leaseId;
	private int vehicleId;
	private int custId;
	private Date startDate;
	private Date endDate;
	private LeaseType leasetype;
}
