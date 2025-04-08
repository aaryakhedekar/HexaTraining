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

public class Payment {
	
	private int paymentid;
	private int leaseId;
	private Date paymentDate;
	private double amount;
}
