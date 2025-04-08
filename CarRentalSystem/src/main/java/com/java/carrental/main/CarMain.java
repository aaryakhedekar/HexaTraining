package com.java.carrental.main;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.java.carrental.dao.CarManagementDao;
import com.java.carrental.dao.CarManagementDaoImpl;
import com.java.carrental.dao.CustomerManagementDao;
import com.java.carrental.dao.CustomerManagementDaoImpl;
import com.java.carrental.dao.LeaseManagementDao;
import com.java.carrental.dao.LeaseManagementDaoImpl;
import com.java.carrental.model.Customer;
import com.java.carrental.model.Lease;
import com.java.carrental.model.LeaseType;
import com.java.carrental.model.Vehicle;

public class CarMain {
	
	static CarManagementDao carmdao;
	static CustomerManagementDao custdao;
	static LeaseManagementDao leasedao;
	static Scanner scanner;
	
	static {
		carmdao = new CarManagementDaoImpl();
		custdao = new CustomerManagementDaoImpl();
		leasedao = new LeaseManagementDaoImpl();
		scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		
		int choice;
		do {
			System.out.println("O P T I O N S ");
			System.out.println("--------------");
			System.out.println("1. Add Car");
			System.out.println("2. Remove Car");
			System.out.println("3. Available car list");
			System.out.println("4. Rented car list");
			System.out.println("5. Find car by ID : ");
			System.out.println("6. Add customer : ");
			System.out.println("7. Remove customer : ");
			System.out.println("8. Customer List : ");
			System.out.println("9. Find customer by ID : ");
			System.out.println("10. Create a Lease");
			System.out.println("11. Find car by lease ID:");
			System.out.println("12. List Active lease : ");
			System.out.println("15. Exit");
			
			System.out.println("Enter your choice : ");
			choice = scanner.nextInt();
			
			switch (choice) {
			case 1:
				AddCar();
				break;
				
			case 2 :
				RemoveCar();
				break;
				
			case 3:
				AvailableCar();
				break;
				
			case 4:
				RentedCar();
				break;
				
			case 5:
				FindCarById();
				break;
				
			case 6 :
				AddCustomer();
				break;
				
			case 7 : 
				RemoveCustomer();
				break;
				
			case 8 : 
				CustList();
				break;
				
			case 9 : 
				FindCustById();
				break;
				
			case 10 :
				try {
					CreateLease();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			case 11:
				FindByLease();
				break;
				
			case 12 : 

				ListActiveLease();
				break;
				
			case 15:
				return;
				
				
			default:
				break;
			}
			
		} while (choice !=15);
	}
	
	public static void AddCar() {
		Vehicle vehicle = new Vehicle();
		
//		System.out.println("Enter Vehicle Id: ");
//		vehicle.setVehicleId(scanner.nextInt());
		System.out.println("Enter make : ");
		vehicle.setMake(scanner.next());
		System.out.println("Enter model : ");
		vehicle.setModel(scanner.next());
		System.out.println("Enter year : ");
		vehicle.setYear(scanner.nextInt());
		System.out.println("Enter daily rate : ");
		vehicle.setRate(scanner.nextDouble());
		System.out.println("Enter status : ");
		vehicle.setStatus(scanner.next());
		System.out.println("Enter passenger capacity : ");
		vehicle.setPassangercap(scanner.nextInt());
		System.out.println("Enter engine capacity : ");
		vehicle.setEnginecap(scanner.nextDouble());
		
		try {
			System.out.println(carmdao.addCar(vehicle));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public static void RemoveCar() {
		
		Vehicle vehicle = new Vehicle();
		System.out.println("Enter Vehicle ID:");
		int vehiID = scanner.nextInt();
		try {
			carmdao.removeCar(vehiID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void AvailableCar() {
		
		try {
			List<Vehicle> carList = carmdao.listOfAvailCar();
			for (Vehicle vehicle : carList) {
				System.out.println(vehicle);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void RentedCar() {
		try {
			List<Vehicle> carList = carmdao.rentedCar();
			for (Vehicle vehicle : carList) {
				System.out.println(vehicle);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void FindCarById() {
		
		System.out.println("Enter Car ID : ");
		int vehicleId = scanner.nextInt();
		
		try {
			Vehicle car = carmdao.findCarById(vehicleId);
			if(car != null) {
				System.out.println(car);
			}else {
				System.out.println("Record not found..");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void AddCustomer() {
		Customer customer = new Customer();
		
//		System.out.println("Enter customer ID : ");
//		customer.setCustId(scanner.nextInt());
		System.out.println("Enter first name : ");
		customer.setFirstname(scanner.next());
		System.out.println("Enter last name : ");
		customer.setLastname(scanner.next());
		System.out.println("Enter email : ");
		customer.setEmail(scanner.next());
		System.out.println("Enter phone no  :");
		customer.setPhno(scanner.next());
		
		try {
			System.out.println(custdao.addCustomer(customer));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void RemoveCustomer() {
		
		System.out.println("Enter customer id : ");
		int custId = scanner.nextInt();
		
		try {
			custdao.removeCustomer(custId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void CustList() {
		
		try {
			List<Customer> CustList = custdao.listCustomer();
			for (Customer customer : CustList) {
				System.out.println(customer);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void FindCustById() {
		System.out.println("Enter ID : ");
		int custId = scanner.nextInt();
		
		try {
			Customer cust = custdao.customer(custId);
			if(cust != null) {
				System.out.println(cust);
			}else {
				System.out.println("Customer not found..!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Date convertSql(java.util.Date utilDate) {
		return new Date(utilDate.getTime());
	}
	
	public static void CreateLease () throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Lease lease = new Lease();
		System.out.println("Enter vehicle id : ");
		lease.setVehicleId(scanner.nextInt());
		System.out.println("Enter customer id : ");
		lease.setCustId(scanner.nextInt());
		
		System.out.println("Enter Start date (yyyy-MM-dd): ");
		String date1 = scanner.next();
		java.util.Date utilDate1 = sdf.parse(date1);
		lease.setStartDate(convertSql(utilDate1));
		
		System.out.println("Enter end date (yyyy-MM-dd): ");
		String date2 = scanner.next();
		java.util.Date utilDate2 = sdf.parse(date2);
		lease.setEndDate(convertSql(utilDate2));
		
		System.out.println("Enter Lease type : ");
		lease.setLeasetype(LeaseType.valueOf(scanner.next()));
		
		try {
			System.out.println(leasedao.CreateLease(lease));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void FindByLease() {
		
		System.out.println("Enter Lease Id");
		int leaseId = scanner.nextInt();
		try {
			Vehicle car = leasedao.findByLeaseId(leaseId);
			if(car != null) {
				System.out.println(car);
			}else {
				System.out.println("Record not found..");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void ListActiveLease() {
		
		try {
			List<Lease>leaseList = leasedao.listActiveLease();
			for (Lease lease : leaseList) {
				System.out.println(lease);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
