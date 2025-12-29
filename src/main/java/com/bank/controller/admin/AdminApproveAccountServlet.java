package com.bank.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bank.service.AdminService;
import com.bank.service.impl.AdminServiceImpl;

@WebServlet("/admin/account-action")
public class AdminApproveAccountServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("adminLoggedIn") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin-login.html");
            return;
        }

        long accountNumber = Long.parseLong(req.getParameter("accountNumber"));
        String action = req.getParameter("action");

        if ("approve".equals(action)) {
            adminService.approveAccount(accountNumber);
        } else if ("close".equals(action)) {
            adminService.closeAccount(accountNumber);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/accounts?success=Action completed");
    }
}
