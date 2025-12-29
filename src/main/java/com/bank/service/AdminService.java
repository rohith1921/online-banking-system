package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.User;

public interface AdminService {
	boolean adminLogin(String username, String password);
	List<User> getAllUsers();
	List<Account> getAllAccounts();
	List<Transaction> getAllTransactions();
	void closeAccount(Long accountNumber);
	void activateAccount(Long accountNumber);
	
	void approveAccount(Long accountNumber);
	
	List<Transaction> getTransactions(Long accountNumber, LocalDateTime from, LocalDateTime to);
	
}
