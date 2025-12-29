package com.bank.service;

import java.util.List;

import com.bank.model.Account;
import com.bank.model.AccountType;
import com.bank.model.User;

public interface AccountService {
	Account createAccount(User user, AccountType accountType, int pin, double initialAmount);
	Account getAccountByNumber(Long accountNumber);
	double getBalance(Long accountNumber);
	void changePin(Long accountNumber, int oldPin, int newPin);
	void closeAccount(Long accountNumber);
	List<Account> getAccountsByUser(Integer userId);

}
