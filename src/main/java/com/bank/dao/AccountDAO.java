package com.bank.dao;

import java.util.List;

import com.bank.model.Account;
import com.bank.model.AccountType;

public interface AccountDAO {
	void save(Account account);
	void update(Account account);
	Account findByAccountNumber(Long accountNumber);
	List<Account> findAccountByUserId(Integer userId);
	List<Account> findAllAccounts();
	boolean existsByUserAndType(Integer userId, AccountType accountType);
	List<Account> findPendingAccounts();
	long countAccounts();
	long countPendingAccounts();

}
