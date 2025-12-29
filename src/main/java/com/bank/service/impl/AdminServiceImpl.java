package com.bank.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import com.bank.dao.UserDAO;
import com.bank.dao.imp.AccountDAOImp;
import com.bank.dao.imp.TransactionDAOImp;
import com.bank.dao.imp.UserDAOImp;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.service.AdminService;

public class AdminServiceImpl implements AdminService{
	private UserDAO userDAO = new UserDAOImp();
	private AccountDAO accountDAO = new AccountDAOImp();
	private TransactionDAO transactionDAO = new TransactionDAOImp();
	
	private static final String ADMIN_USERNAME = "admin@gmail.com";
	private static final String ADMIN_PASSWORD = "Admin@123";

	@Override
	public boolean adminLogin(String username, String password) {
		return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.findAllUsers();
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountDAO.findAllAccounts(); 
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionDAO.findAllTransactions();
	}

	@Override
	public void closeAccount(Long accountNumber) {
		Account account = accountDAO.findByAccountNumber(accountNumber);
		if(account == null ) {
			throw new RuntimeException("Account not found");
		}
		
		account.setStatus("CLOSED");
		accountDAO.update(account);
	}

	@Override
	public void activateAccount(Long accountNumber) {
		Account account = accountDAO.findByAccountNumber(accountNumber);
		if(account==null) {
			throw new RuntimeException("Account not found");
		}
		
		account.setStatus("ACTIVE");
		accountDAO.update(account);
	}

	@Override
	public void approveAccount(Long accountNumber) {
		Account account = accountDAO.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found.");
        }

        if (!"PENDING".equalsIgnoreCase(account.getStatus())) {
            throw new RuntimeException("Account is not in PENDING state.");
        }

        account.setStatus("ACTIVE");
        accountDAO.update(account);
	}
	
	@Override
	public List<Transaction> getTransactions(
	        Long accountNumber,
	        LocalDateTime from,
	        LocalDateTime to) {

	    if (accountNumber != null && from != null && to != null) {
	        return transactionDAO.findByDateRange(accountNumber, from, to);
	    }

	    if (accountNumber != null) {
	        return transactionDAO.findByAccountNumber(accountNumber);
	    }

	    if (from != null && to != null) {
	        return transactionDAO.findByDateRange(accountNumber, from, to);
	    }

	    return transactionDAO.findAllTransactions();
	}

}
