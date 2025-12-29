package com.bank.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import com.bank.dao.imp.AccountDAOImp;
import com.bank.dao.imp.TransactionDAOImp;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{
	private AccountDAO accountDAO = new AccountDAOImp();
	private TransactionDAO transactionDAO = new TransactionDAOImp();

	@Override
	public void deposit(Long accountNumber, double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be greater than 0");
		}
		
		Account account = accountDAO.findByAccountNumber(accountNumber);
		if(account == null) {
			throw new RuntimeException("Account not found.");
		}
		
		if(!"ACTIVE".equalsIgnoreCase(account.getStatus())) {
			throw new RuntimeException("Account is not active");
		}
		
		double newBalance = account.getBalance() + amount;
		account.setBalance(newBalance);
		
		accountDAO.update(account);
		
		Transaction txn = new Transaction();
		txn.setAccount(account);
		txn.setType("CREDIT");
		txn.setAmount(amount);
		txn.setClosingbalance(newBalance);
		txn.setTimestamp(LocalDateTime.now());
		
		transactionDAO.save(txn);
		
	}

	@Override
	public void withdraw(Long accountNumber, double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("withdrawal amount must be greater than 0");
		}
		
		Account acc = accountDAO.findByAccountNumber(accountNumber);
		if(acc==null) {
			throw new RuntimeException("Account not found");
		}
		
		if(!"ACTIVE".equalsIgnoreCase(acc.getStatus())) {
			throw new RuntimeException("Account is not active");
		}
		
		if(acc.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		
		double newBalance = acc.getBalance() - amount;
		acc.setBalance(newBalance);
		
		accountDAO.update(acc);
		
		Transaction txn = new Transaction();
		txn.setAccount(acc);
		txn.setType("DEBIT");
		txn.setAmount(amount);
		txn.setClosingbalance(newBalance);
		txn.setTimestamp(LocalDateTime.now());
		
		transactionDAO.save(txn);
	}

	@Override
	public void transfer(Long fromAccount, Long toAccount, double amount) {
		if(fromAccount.equals(toAccount)) {
			throw new IllegalArgumentException("Source and destination account must be different");
		}
		
		if(amount <= 0) {
			throw new IllegalArgumentException("Transfer amount must be greater than 0");
		}
		
		Account source = accountDAO.findByAccountNumber(fromAccount);
		Account target = accountDAO.findByAccountNumber(toAccount);
		
		if(source == null || target == null) {
			throw new RuntimeException("One or both accounts not found");
		}
		
		if(!"ACTIVE".equalsIgnoreCase(source.getStatus()) || !"ACTIVE".equalsIgnoreCase(target.getStatus())) {
			throw new RuntimeException("Both accounts must be active");
		}
		
		if(source.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance in source account");
		}
		
		source.setBalance(source.getBalance() - amount);
		accountDAO.update(source);
		
		target.setBalance(target.getBalance()+amount);
		accountDAO.update(target);
		
		Transaction debitTxn = new Transaction();
		debitTxn.setAccount(source);
		debitTxn.setType("DEBIT");
		debitTxn.setAmount(amount);
		debitTxn.setClosingbalance(source.getBalance());
		debitTxn.setTimestamp(LocalDateTime.now());
		transactionDAO.save(debitTxn);
		
		Transaction creditTxn = new Transaction();
        creditTxn.setAccount(target);
        creditTxn.setType("CREDIT");
        creditTxn.setAmount(amount);
        creditTxn.setClosingbalance(target.getBalance());
        creditTxn.setTimestamp(LocalDateTime.now());
        transactionDAO.save(creditTxn);
		
	}

	@Override
	public List<Transaction> getMiniStatement(Long accountNumber) {
		Account account = accountDAO.findByAccountNumber(accountNumber);
		if(account == null ) {
			throw new RuntimeException("Account not found");
		}
		
		if(!"ACTIVE".equalsIgnoreCase(account.getStatus())) {
			throw new RuntimeException("Account is not active.");
		}
		
		return transactionDAO.findRecentTransactions(accountNumber, 10);
	}

	@Override
	public List<Transaction> getFullStatement(Long accountNumber) {
		Account account = accountDAO.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found.");
        }

        return transactionDAO.findByAccountNumber(accountNumber);
	}

	@Override
	public List<Transaction> getStatementByDateRange(Long accountNumber, LocalDateTime start, LocalDateTime end) {
		if (start == null || end == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Invalid date range.");
        }

        Account account = accountDAO.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found.");
        }

        return transactionDAO.findByDateRange(accountNumber, start, end);
    }

}
