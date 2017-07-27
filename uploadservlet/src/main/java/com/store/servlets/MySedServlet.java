package com.store.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import com.store.env.Env;
import com.store.env.EnvUtil;
import com.store.model.User;
import com.store.repo.UserRepository;

@Controller
//@RequestMapping(value = "/test2")
public class MySedServlet extends HttpServlet {
	@Autowired
	//@Qualifier(value = "Env")
	private Env env;

	@Autowired
	//@Qualifier(value = "Env")
	private EnvUtil envutil;

	@Autowired
	private UserRepository userrepo;

	@RequestMapping(value = "/test2")
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// Write some content
			out.println("<html>");
			out.println("<head>");
			out.println("<title>MyFirstServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h2>Servlet MyFirstServlet at " + request.getContextPath() + "</h2>");
			out.println("<br>env : " + env.get("uploadPrefix") + ", " + env.get("hdfsUrl") );
			out.println("<br>env : " + envutil.get("uploadPrefix") + ", " + envutil.get("hdfsUrl") );
			out.println("<br>" + userrepo.test());
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
                        e.printStackTrace();
                } finally {
			out.close();
		}	
	}

	@RequestMapping(value = "/test2db")
	public void addUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//User user = new User("testname", "test@163.com", "test");
		//user.setBirthday(new Date("2012/02/02 15:30:09"));
		//userrepo.insert(user); 
		User user = userrepo.findOne("test@163.com", "test");
		try {
			out.println(user.toString());
		} catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        out.close();
                }
	}

	protected void doPost(HttpServletRequest request,
		    HttpServletResponse response) throws ServletException, IOException {
		//Do some other work
		doGet(request, response);
	}

	@RequestMapping(value = "/test2", params = "method=info1",method = RequestMethod.GET)
	public void getServletInfo1(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("MyFirstServlet method = info1 GET");
			out.println("env : " + env.get("uploadPrefix") + ", " + env.get("hdfsUrl") );
			out.println("env : " + envutil.get("uploadPrefix") + ", " + envutil.get("hdfsUrl") );
		} catch (Exception e) {
                        e.printStackTrace();
                } finally {
			out.close();
		}	
	}

	@RequestMapping(value = "/test2",params = "method=info2",method = RequestMethod.POST)
	public void getServletInfo2(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("MyFirstServlet method = info2 POST");
			out.println("env : " + env.get("uploadPrefix") + ", " + env.get("hdfsUrl") );
			out.println("env : " + envutil.get("uploadPrefix") + ", " + envutil.get("hdfsUrl") );
		} catch (Exception e) {
                        e.printStackTrace();
                } finally {
			out.close();
		}	
	}


	@RequestMapping(value = "/test2",params = "method=info3",method = RequestMethod.GET)
	public void getServletInfo3(HttpServletRequest request,
                HttpServletResponse response, String name, int id) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("MyFirstServlet method = info3 GET");
			out.println("name = " + name);
			out.println("id = " + Integer.toString(id));
			out.println("env : " + env.get("uploadPrefix") + ", " + env.get("hdfsUrl") );
			out.println("env : " + envutil.get("uploadPrefix") + ", " + envutil.get("hdfsUrl") );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}	
	}


	@RequestMapping(value = "/test2",params = "method=info4")
	public void getServletInfo4(HttpServletRequest request,
                HttpServletResponse response, @RequestParam("name") String name, @RequestParam("id") int id) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("MyFirstServlet method = info4");
			out.println("name = " + name);
			out.println("id = " + Integer.toString(id));
			out.println("env : " + env.get("uploadPrefix") + ", " + env.get("hdfsUrl") );
			out.println("env : " + envutil.get("uploadPrefix") + ", " + envutil.get("hdfsUrl") );
		} catch (Exception e) {
                        e.printStackTrace();
                } finally {
			out.close();
		}	
	}

	@RequestMapping(value = "/test2",params = "method=info5")
	public String getServletInfo5(@RequestParam("name") String name, @RequestParam("id") int id)
	{
		return "msg";
	}

}
