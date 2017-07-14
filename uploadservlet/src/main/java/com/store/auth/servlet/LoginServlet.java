package com.store.auth.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;

import com.store.auth.model.User;
import com.store.auth.service.UserService;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email != null && password != null) {
            if (UserService.hasUser(new User(email, password))) {
                HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                session = req.getSession(true);
                session.setAttribute("email", email);
                resp.sendRedirect("index.jsp");
                return;
            }
        }
        resp.sendRedirect("login.jsp");
    }
}
