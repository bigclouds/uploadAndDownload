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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.store.auth.model.UserProxy;

@Controller
public class UserServlet {

    @RequestMapping(value = "/userinfo")
    protected String doGet(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServletException, IOException {
	HttpSession session = req.getSession(false);
	String message = (String) session.getAttribute("email");
	String name = (String) session.getAttribute("name");
	String isadmin = Boolean.valueOf((Boolean)session.getAttribute("isadmin"))?" ADMIN":" No ADMIN";
	UserProxy u = (UserProxy) session.getAttribute("self");
	//req.setAttribute("message",message + " " + name + isadmin);
        //req.getRequestDispatcher("jsp/user.jsp").forward(req, resp);
        //ModelAndView modelAndView = new ModelAndView("jsp/user.jsp");
        model.addAttribute("message",message + "," + name + "," + isadmin + "," + u.toString());
	
	return "user";
    }
}
