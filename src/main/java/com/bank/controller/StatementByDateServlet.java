package com.bank.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

@WebServlet("/statement-by-date")
public class StatementByDateServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();
    private TransactionService transactionService = new TransactionServiceImpl();

    // ✅ GET → Show page + account dropdown
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
        req.getRequestDispatcher("statement-by-date.jsp").forward(req, resp);
    }

    // ✅ POST → Fetch statement by date range
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("login.html?error=Please login first");
            return;
        }

        try {
            long accountNumber =
                    Long.parseLong(req.getParameter("accountNumber"));

            LocalDateTime start =
                    LocalDate.parse(req.getParameter("startDate")).atStartOfDay();

            LocalDateTime end =
                    LocalDate.parse(req.getParameter("endDate"))
                             .atTime(23, 59, 59);

            List<Transaction> transactions =
                    transactionService.getStatementByDateRange(
                            accountNumber, start, end);

            // Reload accounts for dropdown
            User user = (User) session.getAttribute("loggedInUser");
            List<Account> accounts =
                    accountService.getAccountsByUser(user.getUserid());

            req.setAttribute("accounts", accounts);
            req.setAttribute("transactions", transactions);

            req.getRequestDispatcher("statement-by-date.jsp").forward(req, resp);

        } catch (Exception e) {
            resp.sendRedirect("statement-by-date?error=" + e.getMessage());
        }
    }
}
