package com.java.carrental.model;

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

public class Vehicle {
	
	private int vehicleId;
	private String make;
	private String model;
	private int year;
	private double rate;
	private String status;
	private int passangercap;
	private double enginecap;
}
