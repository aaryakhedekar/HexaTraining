package com.java.bank.dao;

import java.sql.SQLException;

import com.java.bank.model.Accounts;

public interface BankDao {
	String createAccount (Accounts account) throws ClassNotFoundException, SQLException;
	Accounts searchAccount (int accountNo) throws ClassNotFoundException, SQLException;
	String depositeAccount (int accountNo, double depositeAmount) throws ClassNotFoundException, SQLException;
	String withdrawAccount (int accountNo, double withdrawAmount) throws ClassNotFoundException, SQLException;
}
