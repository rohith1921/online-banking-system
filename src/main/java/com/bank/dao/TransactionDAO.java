package com.bank.dao;

import java.util.List;

import com.bank.model.Transaction;

public interface TransactionDAO {
	void save(Transaction txn);
	List<Transaction> findRecentTransactions(Long accountNumber, int limit);
	List<Transaction> findByAccountNumber(Long accountNumber);
	List<Transaction> findByDateRange(Long accountNumber, java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
	List<Transaction> findAllTransactions();
	long countTransactions();
}
