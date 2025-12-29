package com.bank.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bank.model.Account;
import com.bank.service.AdminService;
import com.bank.service.impl.AdminServiceImpl;

@WebServlet("/admin/accounts")
public class AdminAccountsServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("adminLoggedIn") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin-login.html?error=Please login");
            return;
        }

        List<Account> accounts = adminService.getAllAccounts();
        req.setAttribute("accounts", accounts);

        req.getRequestDispatcher("/admin/admin-accounts.jsp")
           .forward(req, resp);
    }
}
