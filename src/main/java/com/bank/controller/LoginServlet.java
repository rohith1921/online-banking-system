package com.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.User;
import com.bank.service.UserService;
import com.bank.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = userService.login(email, password);
		 
		resp.setContentType("text/html");
		
		if(user!=null) {
//			resp.getWriter().println("<h3>Login Successful</h3>");
//			resp.getWriter().println("<p>Welcome "+user.getName()+"</p>");
			HttpSession session = req.getSession();
			session.setAttribute("loggedInUser", user);
			resp.sendRedirect("dashboard?success=Login successful");
		} else {
			resp.sendRedirect("login.html?error=Invalid credentials");
		}
	}

}
