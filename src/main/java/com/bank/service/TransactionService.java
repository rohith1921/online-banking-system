package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.model.Transaction;

public interface TransactionService {
	void deposit(Long accountNumber, double amount);
	void withdraw(Long accountNumber, double amount);
	void transfer(Long fromAccount, Long toAccount, double amount);
	List<Transaction> getMiniStatement(Long accountNumber);
	List<Transaction> getFullStatement(Long accountNumber);
	List<Transaction> getStatementByDateRange(Long accountNumber, LocalDateTime start, LocalDateTime end);


}
