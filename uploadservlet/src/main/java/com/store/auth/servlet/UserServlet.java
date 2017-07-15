package com.store.auth.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession(false);
	String message = (String) session.getAttribute("email");
	String isadmin = Boolean.valueOf((Boolean)session.getAttribute("isadmin"))?" ADMIN":" No ADMIN";
	req.setAttribute("message",message + isadmin);
        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}
