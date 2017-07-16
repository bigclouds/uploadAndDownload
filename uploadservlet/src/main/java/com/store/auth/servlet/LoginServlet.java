package com.store.auth.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.auth.model.User;
import com.store.auth.service.UserService;
import org.apache.log4j.Logger;

@Controller
public class LoginServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @RequestMapping("/login")
    protected String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
	String name = req.getParameter("username");
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
		session.setAttribute("name", u.getName());

		resp.sendRedirect("jsp/index.jsp");
		//return "redirect:index";
            }
        }
	return "login";
    }

    @RequestMapping("/logout")
    protected String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession(false);
	if (session != null) {
             session.invalidate();
        }
	return "login";
    }
}
