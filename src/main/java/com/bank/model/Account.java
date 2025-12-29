package com.bank.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "accounts")
public class Account {
	@Id
	private long accountnumber;
	
	private double balance;
	@Column(nullable = false)
	private int pin;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "userid", nullable = false) //FK column in DB
	private User user; 
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Transaction> transaction;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountType accountType;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(long accountnumber, double balance, int pin, String status, User user,
			List<Transaction> transaction) {
		super();
		this.accountnumber = accountnumber;
		this.balance = balance;
		this.pin = pin;
		this.status = status;
		this.user = user;
		this.transaction = transaction;
	}

	public long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [accountnumber=" + accountnumber + ", balance=" + balance + ", pin=" + pin + ", status="
				+ status + ", user=" + user + "]";
	}
}
