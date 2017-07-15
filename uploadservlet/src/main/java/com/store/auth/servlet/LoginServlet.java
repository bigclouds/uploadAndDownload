package com.store.auth.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;

import com.store.auth.model.User;
import com.store.auth.service.UserService;
import org.apache.log4j.Logger;

public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email != null && password != null) {
            if (UserService.hasUser(new User(email, password))) {
		User u = UserService.getUser(new User(email, password));
                HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
		logger.info(u);
		logger.info(email + " " + password);
                session = req.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("isadmin", new Boolean(u.isAdmin()));
                resp.sendRedirect("index.jsp");
                return;
            }
        }
        resp.sendRedirect("login.jsp");
    }
}
