package com.bank.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity 
@Table(name="users") 
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increament in DB
	private int userid;  
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private long mobilenumber;
	@Column(nullable = false) 
	private String password;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	//one user - Many accounts
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
	private List<Account> accounts;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userid, String name, String email, long mobilenumber, String password, LocalDateTime createdAt,
			List<Account> accounts) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.mobilenumber = mobilenumber;
		this.password = password;
		this.createdAt = createdAt;
		this.accounts = accounts;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreated_at(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", mobilenumber=" + mobilenumber
				+ ", password=" + password + ", created_at=" + createdAt + "]";
	}
}
