package com.bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bank.model.Account;
import com.bank.model.User;
import com.bank.service.AccountService;
import com.bank.service.impl.AccountServiceImpl;

@WebServlet("/balance")
public class BalanceServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("login.html?error=Please login first");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        // Fetch user accounts
        List<Account> accounts =
                accountService.getAccountsByUser(user.getUserid());

        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("balance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("login.html?error=Please login first");
            return;
        }

        long accountNumber =
                Long.parseLong(req.getParameter("accountNumber"));

        double balance =
                accountService.getBalance(accountNumber);

        // Re-fetch accounts for dropdown
        User user = (User) session.getAttribute("loggedInUser");
        List<Account> accounts =
                accountService.getAccountsByUser(user.getUserid());

        req.setAttribute("accounts", accounts);
        req.setAttribute("balance", balance);

        req.getRequestDispatcher("balance.jsp").forward(req, resp);
    }
}
