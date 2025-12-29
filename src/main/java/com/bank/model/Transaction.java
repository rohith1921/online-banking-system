package com.bank.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long txnid;
	private String type;
	private double amount;
	private double closingbalance;
	
	@CreationTimestamp
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JoinColumn(name = "accountnumber", nullable = false)
	private Account account;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(long txnid, String type, double amount, double closingbalance, LocalDateTime timestamp,
			Account account) {
		super();
		this.txnid = txnid;
		this.type = type;
		this.amount = amount;
		this.closingbalance = closingbalance;
		this.timestamp = timestamp;
		this.account = account;
	}

	public long getTxnid() {
		return txnid;
	}

	public void setTxnid(long txnid) {
		this.txnid = txnid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getClosingbalance() {
		return closingbalance;
	}

	public void setClosingbalance(double closingbalance) {
		this.closingbalance = closingbalance;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Account getAccount() {
		return account; 
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getDescription() {
	    switch (type) {
	        case "CREDIT":
	            return "Deposit";
	        case "DEBIT":
	            return "Withdrawal";
	        case "TRANSFER":
	            return "Transfer";
	        default:
	            return "Transaction";
	    }
	}

	@Override
	public String toString() {
	    return "Transaction{" +
	            "txnid=" + txnid +
	            ", type='" + type + '\'' +
	            ", amount=" + amount +
	            ", closingBalance=" + closingbalance +
	            ", timestamp=" + timestamp +
	            ", accountNumber=" + (account != null ? account.getAccountnumber() : null) +
	            '}';
	}

}
