package com.java.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.bank.model.Accounts;
import com.java.bank.util.ConnectionHelper;

public class BankDaoImpl implements BankDao{
	
	Connection connection;
	PreparedStatement pst;
	
	
	public int generateAccountNo() throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
				
		String cmd = "select case when max(accountno) is null then 1 else " +
						"max(accountno)+1 end accno from accounts";
		
		pst = connection.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int accno = rs.getInt("accno");
		return accno;
		
	}

	@Override
	public String createAccount(Accounts account) throws ClassNotFoundException, SQLException {
		
		int accno = generateAccountNo();
		connection = ConnectionHelper.getConnection();
		account.setAccountNo(accno);
		String cmd = "Insert into Accounts (AccountNo,firstName,lastname,city,state,amount,CheqFacil,accountType )"+
						"values(?,?,?,?,?,?,?,?)";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, accno);
		pst.setString(2, account.getFirstname());
		pst.setString(3, account.getLastname());
		pst.setString(4, account.getCity());
		pst.setString(5, account.getState());
		pst.setDouble(6, account.getAmount());
		pst.setString(7, account.getCheqFacil());
		pst.setString(8, account.getAccountType());
		
		pst.executeUpdate();
		return "Account is created with account no : "+accno;		
		
	}

	@Override
	public Accounts searchAccount(int accountNo) throws ClassNotFoundException, SQLException {
		
		String cmd = "select * from Accounts where accountNo = ?";
		connection = ConnectionHelper.getConnection();
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, accountNo);
		ResultSet rs = pst.executeQuery();
		Accounts account = null;
		if(rs.next()) {
			account = new Accounts();
			account.setAccountNo(rs.getInt("accountNo"));
			account.setFirstname(rs.getString("firstName"));
			account.setLastname(rs.getString("LastName"));
			account.setCity(rs.getString("city"));
			account.setState(rs.getString("state"));
			account.setAmount(rs.getDouble("amount"));
			account.setCheqFacil(rs.getString("cheqfacil"));
			account.setAccountType(rs.getString("accounttype"));
		}
		return account;
	}

	@Override
	public String depositeAccount(int accountNo, double depositeAmount) throws ClassNotFoundException, SQLException {
		
		connection = ConnectionHelper.getConnection();
		String cmd = "Update Accounts set amount = amount + ? where accountNo = ?";
		pst = connection.prepareStatement(cmd);
		pst.setDouble(1, depositeAmount);
		pst.setInt(2, accountNo);
		pst.executeUpdate();
		
		cmd = "Insert into Trans (accountNo,transAmount,transtype) values (?,?,?)";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, accountNo);
		pst.setDouble(2, depositeAmount);
		pst.setString(3, "C");
		
		pst.executeUpdate();
		
		return "Amount credited to Account no : "+accountNo;
		
	}

	@Override
	public String withdrawAccount(int accountNo, double withdrawAmount) throws ClassNotFoundException, SQLException {
		
		
		Accounts account = searchAccount(accountNo);
		if(account!=null) {
			double balance = account.getAmount()-withdrawAmount;
			if(balance > 0) {
				connection = ConnectionHelper.getConnection();
				String cmd = "Update Accounts set amount = amount - ? where accountNo = ?";
				pst = connection.prepareStatement(cmd);
				pst.setDouble(1, withdrawAmount);
				pst.setInt(2, accountNo);
				pst.executeUpdate();
				
				cmd = "Insert into trans (AccountNo, transAmount, transType)" +
						"values (?,?,?)";
				pst = connection.prepareStatement(cmd);
				pst.setInt(1, accountNo);
				pst.setDouble(2, withdrawAmount);
				pst.setString(3, "D");
				pst.executeUpdate();
				return "AccountNo "+accountNo + " debited with "+withdrawAmount;
			}else {
				return "Insufficient Funds..";
			}
		}
		
		return "Account not found..";
	}

}
