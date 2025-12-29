package com.bank.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/admin/logout")
public class AdminLogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Prevent back-button access
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();   // 🔑 MOST IMPORTANT
        }

        // Redirect to admin login
        resp.sendRedirect(req.getContextPath()
                + "/admin-login.html?success=Logged out successfully");
    }
}
