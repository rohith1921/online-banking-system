package com.bank.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import com.bank.dao.UserDAO;
import com.bank.dao.imp.AccountDAOImp;
import com.bank.dao.imp.TransactionDAOImp;
import com.bank.dao.imp.UserDAOImp;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImp();
    private AccountDAO accountDAO = new AccountDAOImp();
    private TransactionDAO transactionDAO = new TransactionDAOImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("adminLoggedIn") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin-login.html?error=Please login as admin");
            return;
        }

        // ---- Dashboard stats ----
        long totalUsers = userDAO.countUsers();
        long totalAccounts = accountDAO.countAccounts();
        long pendingAccounts = accountDAO.countPendingAccounts();
        long totalTransactions = transactionDAO.countTransactions();

        req.setAttribute("totalUsers", totalUsers);
        req.setAttribute("totalAccounts", totalAccounts);
        req.setAttribute("pendingAccounts", pendingAccounts);
        req.setAttribute("totalTransactions", totalTransactions);

        req.getRequestDispatcher("/admin/admin-dashboard.jsp")
           .forward(req, resp);
    }
}
