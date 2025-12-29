package com.bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bank.model.Account;
import com.bank.model.User;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.TransactionServiceImpl;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {

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

        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("withdraw.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            long accountNumber =
                    Long.parseLong(req.getParameter("accountNumber"));
            double amount =
                    Double.parseDouble(req.getParameter("amount"));

            transactionService.withdraw(accountNumber, amount);

            resp.sendRedirect("withdraw?success=Amount withdrawn successfully");

        } catch (Exception e) {
            resp.sendRedirect("withdraw?error=" + e.getMessage());
        }
    }
}
