package com.bank.dao;

import java.util.List;

import com.bank.model.User;

public interface UserDAO {
	void save(User user);
	void update(User user);
	User findById(Integer userId);
	User findByEmail(String email);
	User findByMobile(long mobilenumber);
	void delete(User user);
	List<User> findAllUsers();
	long countUsers();
}
