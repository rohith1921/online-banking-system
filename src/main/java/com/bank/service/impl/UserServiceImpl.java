package com.bank.service.impl;

import com.bank.dao.UserDAO;
import com.bank.dao.imp.UserDAOImp;
import com.bank.model.User;
import com.bank.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDAO userDAO = new UserDAOImp();
	private String[] emailDomain = {"@gmail.com","@yahoo.com","@example.com"};

	@Override
	public void registerUser(User user) {
		if(user==null) {
			throw new IllegalArgumentException("User cannot be null.");
		}
		
		if(user.getEmail()==null || user.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty.");
		}
		
		boolean isValid = false;
		for(String u:emailDomain) {
			if(user.getEmail().endsWith(u)) {
				isValid = true;
				break;
			}
		}
		
		if(!isValid) {
			throw new IllegalArgumentException("Invalid Email format.");
		}
		
		if(user.getMobilenumber()==0) {
			throw new IllegalArgumentException("Mobile number cannot be empty.");
		}
		
		if (!(user.getMobilenumber() > 6000000000L) || !(user.getMobilenumber() < 9999999999L)) {
	        throw new IllegalArgumentException("Invalid mobile number");
	    }
		
		if(user.getPassword()==null || user.getPassword().isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
		
		String passwordRegex =
	            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

	    if (!user.getPassword().matches(passwordRegex)) {
	        throw new IllegalArgumentException(
	            "Password must be at least 8 characters long, contain uppercase, lowercase, digit, and special character."
	        );
	    }
		
		User existingEmailUser = userDAO.findByEmail(user.getEmail());
		if(existingEmailUser!=null) {
			throw new RuntimeException("Email already exists. Please use different email.");
		}
		
		User existingMobileUser = userDAO.findByMobile(user.getMobilenumber());
		if(existingMobileUser!=null) {
			throw new RuntimeException("Mobile number already exists.");
		}
		
		userDAO.save(user);
		
	}

	@Override
	public User login(String email, String password) {
		User user = userDAO.findByEmail(email);
		if(user==null) {
			return null; 
		} else if(user.getPassword().equals(password)) {
			return user; 
		}
		return null; 
	}

	@Override
	public User getUserById(Integer id) {
		User user = userDAO.findById(id);
		if(user==null) {
			return null;
		} else {
			return user; 
		}
	}

	@Override
	public void updateUser(User user) {
		if(user==null) {
			throw new IllegalArgumentException("User cannot be empty.");
		}
		
		User existingUser = userDAO.findById(user.getUserid());
	    if (existingUser == null) {
	        throw new RuntimeException("User not found.");
	    }
	    
	    if (user.getEmail() == null || user.getEmail().isEmpty()) {
	        throw new IllegalArgumentException("Email cannot be empty.");
	    }
	    
	    for(String u:emailDomain) {
			if(!user.getEmail().endsWith(u)) {
				throw new IllegalArgumentException("Invalid email format.");
			}
		}
	    
	    User emailOwner = userDAO.findByEmail(user.getEmail());
	    if (emailOwner != null && !(emailOwner.getUserid()==user.getUserid())) {
	        throw new RuntimeException("Email is already used by another user.");
	    }
	    
	    if (user.getMobilenumber() == 0 ) {
	        throw new IllegalArgumentException("Mobile number cannot be empty.");
	    }
	    
	    if (!(user.getMobilenumber() > 6000000000L) || !(user.getMobilenumber() < 9999999999L)) {
	        throw new IllegalArgumentException("Invalid mobile number");
	    }
	    
	    User mobileOwner = userDAO.findByMobile(user.getMobilenumber());
	    if (mobileOwner != null && !(mobileOwner.getUserid()==user.getUserid())) {
	        throw new RuntimeException("Mobile number is already used by another user.");
	    } 
	    
	    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	        String passwordRegex =
	                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

	        if (!user.getPassword().matches(passwordRegex)) {
	            throw new IllegalArgumentException(
	                "Password must contain minimum 8 characters, including uppercase, lowercase, digit, and special character."
	            );
	        }
	    } else {
	        // If password is empty → keep old password
	        user.setPassword(existingUser.getPassword());
	    }

	    userDAO.update(user);
	}
	

}
