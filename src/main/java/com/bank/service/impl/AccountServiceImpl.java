package com.bank.service.impl;

import java.util.List;

import com.bank.dao.AccountDAO;
import com.bank.dao.imp.AccountDAOImp;
import com.bank.dao.imp.TransactionDAOImp;
import com.bank.model.Account;
import com.bank.model.AccountType;
import com.bank.model.User;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;

public class AccountServiceImpl implements AccountService { 
	private AccountDAO accountDAO = new AccountDAOImp();
	private TransactionService transactionService = new TransactionServiceImpl(); 

	@Override
	public Account createAccount(User user, AccountType accountType, int pin, double initialAmount) {
		if (accountType == AccountType.SALARY || accountType == AccountType.CURRENT) {

		    boolean exists = accountDAO.existsByUserAndType(
		            user.getUserid(), accountType
		    );

		    if (exists) {
		        throw new RuntimeException(
		            "As per Our policy, user can have only ONE " + accountType + " account is allowed"
		        );
		    }
		}

		if(user==null) {
			throw new IllegalArgumentException("Valid user required to create an account.");
		}
		
		boolean isValidPin = (pin >= 1000 && pin <=9999) ||
							 (pin >= 100000 && pin <= 999999);
		
		if(!isValidPin) {
			throw new IllegalArgumentException("Pin must be 4 or 6 digit.");
		}
		
		long accountNumber = System.currentTimeMillis();
		
		Account account = new Account();
		account.setAccountType(accountType);
		account.setAccountnumber(accountNumber); 
		account.setUser(user);
		account.setBalance(0.0);
		account.setPin(pin);
		account.setStatus("ACTIVE");
		
		accountDAO.save(account);
		
		if(initialAmount > 0) {
			transactionService.deposit(accountNumber, initialAmount);
		}
		
		return account;
		
		
	}

	@Override
	public Account getAccountByNumber(Long accountNumber) {
		if(accountNumber == null) {
			throw new IllegalArgumentException("Acount number cannot be empty.");
		}
		
		Account acc = accountDAO.findByAccountNumber(accountNumber);
		if(acc==null) {
			throw new RuntimeException("Account not found.");
		}
		
		return acc;
	}

	@Override
	public double getBalance(Long accountNumber) {
		Account acc = getAccountByNumber(accountNumber);
		if(!"ACTIVE".equalsIgnoreCase(acc.getStatus())) {
			throw new RuntimeException("Account is not active.");
		}
		
		return acc.getBalance();
	}

	@Override
	public void changePin(Long accountNumber, int oldPin, int newPin) {
		Account acc = getAccountByNumber(accountNumber);
		if(acc.getPin() != oldPin) {
			throw new IllegalArgumentException("Old PIN is incorrect.");
		}
		
		boolean isValidNewPin =
	            (newPin >= 1000 && newPin <= 9999) ||
	            (newPin >= 100000 && newPin <= 999999);

	    if (!isValidNewPin) {
	        throw new IllegalArgumentException("New PIN must be 4 or 6 digits.");
	    }

	    if (oldPin == newPin) {
	        throw new IllegalArgumentException("New PIN cannot be same as old PIN.");
	    }

	    acc.setPin(newPin);
	    accountDAO.update(acc);
		
	}

	@Override
	public void closeAccount(Long accountNumber) {
		Account acc = getAccountByNumber(accountNumber);
		if("CLOSED".equalsIgnoreCase(acc.getStatus())) {
			throw new RuntimeException("Account is already closed");
		}
		
		if(acc.getBalance() < 0) {
			throw new RuntimeException("Account cannot be closed with negative balance");
		}
		
		acc.setStatus("CLOSED");
		
		accountDAO.update(acc);
		
	}

	@Override
	public List<Account> getAccountsByUser(Integer userId) {
		if(userId == null) {
			throw new IllegalArgumentException("User ID cannot be null");
		}
		
		List<Account> accountList = accountDAO.findAccountByUserId(userId);
		
		if(accountList == null || accountList.isEmpty()) {
			throw new RuntimeException("No accounts found for this user");
		}
		
		return accountList;
	}

}
