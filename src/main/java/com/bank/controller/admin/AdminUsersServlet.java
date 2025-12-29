package com.bank.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bank.model.User;
import com.bank.service.AdminService;
import com.bank.service.impl.AdminServiceImpl;

@WebServlet("/admin/users")
public class AdminUsersServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("adminLoggedIn") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin-login.html?error=Please login");
            return;
        }

        List<User> users = adminService.getAllUsers();
        req.setAttribute("users", users);

        req.getRequestDispatcher("/admin/admin-users.jsp")
           .forward(req, resp);
    }
}
