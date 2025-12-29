package com.bank.controller.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bank.model.Transaction;
import com.bank.service.AdminService;
import com.bank.service.impl.AdminServiceImpl;

@WebServlet("/admin/transactions")
public class AdminTransactionsServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("adminLoggedIn") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin-login.html");
            return;
        }

        String accParam = req.getParameter("accountNumber");
        String fromParam = req.getParameter("from");
        String toParam = req.getParameter("to");

        Long accountNumber = null;
        LocalDateTime from = null;
        LocalDateTime to = null;

        try {
            if (accParam != null && !accParam.isEmpty()) {
                accountNumber = Long.parseLong(accParam);
            }

            if (fromParam != null && !fromParam.isEmpty()) {
                from = LocalDate.parse(fromParam).atStartOfDay();
            }

            if (toParam != null && !toParam.isEmpty()) {
                to = LocalDate.parse(toParam).atTime(23, 59, 59);
            }
        } catch (Exception ignored) {}

        List<Transaction> transactions =
                adminService.getTransactions(accountNumber, from, to);

        req.setAttribute("transactions", transactions);
        req.setAttribute("accountNumber", accParam);
        req.setAttribute("from", fromParam);
        req.setAttribute("to", toParam);

        req.getRequestDispatcher("/admin/admin-transactions.jsp")
           .forward(req, resp);
    }
}
