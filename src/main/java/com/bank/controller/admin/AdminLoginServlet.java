package com.bank.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bank.service.AdminService;
import com.bank.service.impl.AdminServiceImpl;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean ok = adminService.adminLogin(email, password);

        if (ok) {
            HttpSession session = req.getSession(true);
            session.setAttribute("adminLoggedIn", true);
            // redirect to admin dashboard (JSP or HTML)
            resp.sendRedirect(req.getContextPath() + "/admin/admin-dashboard.jsp?success=Welcome");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin/admin-login.html?error=Invalid credentials");
        }
    }
}
