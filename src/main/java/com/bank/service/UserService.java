package com.bank.service;

import com.bank.model.User;

public interface UserService {
	void registerUser(User user);
	User login(String email, String password);
	User getUserById(Integer id);
	void updateUser(User user);
}
