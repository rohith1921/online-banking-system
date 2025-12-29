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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();
    private TransactionService transactionService = new TransactionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1️⃣ Session validation
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("login.html?error=Please login first");
            return;
        }

        // 2️⃣ Get logged-in user
        User user = (User) session.getAttribute("loggedInUser");

        // 3️⃣ Fetch all accounts of the user
        List<Account> accounts =
                accountService.getAccountsByUser(user.getUserid());

        // 4️⃣ Fetch recent transactions (optional, safe)
        List<Transaction> recentTransactions = null;

        if (accounts != null && !accounts.isEmpty()) {
            // Use first account ONLY for preview
            Long accountNumber = accounts.get(0).getAccountnumber();
            recentTransactions =
                    transactionService.getMiniStatement(accountNumber);
        }

        // 5️⃣ Set attributes for JSP
        req.setAttribute("user", user);
        req.setAttribute("accounts", accounts);
        req.setAttribute("recentTransactions", recentTransactions);

        // Optional success/error message pass-through
        req.setAttribute("success", req.getParameter("success"));
        req.setAttribute("error", req.getParameter("error"));

        // 6️⃣ Forward to dashboard.jsp
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}
