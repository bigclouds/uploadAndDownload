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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import java.util.Date;

import com.store.model.User;
import com.store.repo.UserRepository;
import com.store.auth.model.UserProxy;
import com.store.auth.service.UserService;
import com.store.auth.service.Mailer;
import com.store.auth.model.Mail;

@Controller
public class LoginServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userrepo;
    @Resource
    private Mailer mailer;

    @RequestMapping("/login")
    protected String doPost(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServletException, IOException {
        String email = req.getParameter("email");
	String name = req.getParameter("username");
        String password = req.getParameter("password");
        if (email != null && password != null) {
            if (userService.hasUser(new UserProxy(email, password))) {
		UserProxy u = userService.getUser(new UserProxy(email, password));
                HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
		logger.info(u);
		logger.info(email + " " + password);
                session = req.getSession(true);
                session.setAttribute("email", u.getEmail());
                session.setAttribute("isadmin", new Boolean(u.isAdmin()));
		session.setAttribute("name", u.getName());
		session.setAttribute("self", u);

		resp.sendRedirect("jsp/index.jsp");
		//return "redirect:index";
            }
        }
	model.addAttribute("message", "can not find " + email + " " + password);
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

   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public String register(@RequestParam(value="username",required = true) String username, @RequestParam(value="email",required = true) String email,
                          @RequestParam(value="password",required = true) String password,
                          @RequestParam(value="date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date)
   {
        User u = new User(username, email, password);
        if (date != null) {
                u.setBirthday(date);
        }
        logger.info("register user " + u.toString());
        userrepo.insert(u);
        return "login";
   }

   @RequestMapping(value = "/confirm")
   public String confirm(HttpServletRequest request, Model model) 
   {
	HttpSession session = request.getSession(false);
	if (session == null)	return "login";
	UserProxy u = (UserProxy) session.getAttribute("self");
	String email = u.getEmail();
	String name = u.getName();
	String msg = "please confirm email " + email;
	String content = msg + ", " +  request.getRequestURL() + "?token=" + email;
	Mail mail = new Mail();
	mail.setMailFrom("yalogr@163.com");
	mail.setMailTo(email);
	mail.setMailSubject("Subject - " + msg);
	mail.setTemplateName("emailtemplate.vm");
	mail.setMailContent(content);
	mailer.sendMail(mail);

	model.addAttribute("message", msg);
	return "msg";
   }

   @RequestMapping(value = "/confirm", params = "token")
   public String confirm(@RequestParam(value="token", required = true) String token, Model model) 
   {
	User u = userrepo.findListByEmail(token).get(0);
	u.setConfirmed(true);
	userrepo.save(u);
	model.addAttribute("message", u.getName() + " is verified. " + Boolean.valueOf(u.getConfirmed()));
	return "msg";
   }
}
