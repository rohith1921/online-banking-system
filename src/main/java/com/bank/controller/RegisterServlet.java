package com.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.model.User;
import com.bank.service.UserService;
import com.bank.service.impl.UserServiceImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/register.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		long mobileNumber = Long.parseLong(mobile);
		String password = req.getParameter("password");
		
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setMobilenumber(mobileNumber);
		user.setPassword(password);
		
		resp.setContentType("text/html");
		
		try {
			userService.registerUser(user);
			resp.sendRedirect("login.html?success=Register Successful!");
		} catch(Exception e) {
			resp.sendRedirect("register.html?error style='color:red'>"+e.getMessage());
		}
	}

}
