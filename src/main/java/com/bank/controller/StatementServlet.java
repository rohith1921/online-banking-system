package com.bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.TransactionServiceImpl;

@WebServlet("/statement")
public class StatementServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();
    private TransactionService transactionService = new TransactionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("login.html?error=Please login first");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        List<Account> accounts =
                accountService.getAccountsByUser(user.getUserid());

        Long accountNumber = req.getParameter("accountNumber") != null
                ? Long.parseLong(req.getParameter("accountNumber"))
                : null;

        List<Transaction> transactions = null;

        if (accountNumber != null) {
            transactions =
                    transactionService.getFullStatement(accountNumber);
        }

        req.setAttribute("accounts", accounts);
        req.setAttribute("transactions", transactions);

        req.getRequestDispatcher("statement.jsp").forward(req, resp);
    }
}
