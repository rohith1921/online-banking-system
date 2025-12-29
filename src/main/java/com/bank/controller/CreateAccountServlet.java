package com.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.AccountType;
import com.bank.model.User;
import com.bank.service.AccountService;
import com.bank.service.impl.AccountServiceImpl;

@WebServlet("/create-account")
public class CreateAccountServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("login.html?error=Please login first");
            return;
        }

        req.getRequestDispatcher("create-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("login.html?error=Please login first");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        try {
            AccountType accountType =
                    AccountType.valueOf(req.getParameter("accountType"));

            int pin = Integer.parseInt(req.getParameter("pin"));
            double initialAmount =
                    Double.parseDouble(req.getParameter("initialAmount"));

            accountService.createAccount(user, accountType, pin, initialAmount);

            resp.sendRedirect("create-account?success=Account created successfully");

        } catch (Exception e) {
            resp.sendRedirect("create-account?error=" + e.getMessage());
        }
    }
}
