package com.store.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/test2")
public class MySedServlet extends HttpServlet {

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
			out.println("</body>");
			out.println("</html>");
			} finally {
				out.close();
			}	
	}

	protected void doPost(HttpServletRequest request,
		    HttpServletResponse response) throws ServletException, IOException {
		//Do some other work
		doGet(request, response);
	}

	@RequestMapping(params = "method=info1",method = RequestMethod.GET)
	public void getServletInfo1(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("MyFirstServlet method = info1 GET");
		} finally {
			out.close();
		}	
	}

	@RequestMapping(params = "method=info2",method = RequestMethod.POST)
	public void getServletInfo2(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("MyFirstServlet method = info2 POST");
		} finally {
			out.close();
		}	
	}


	@RequestMapping(params = "method=info3",method = RequestMethod.GET)
	public void getServletInfo3(HttpServletRequest request,
                HttpServletResponse response, String name, int id) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("MyFirstServlet method = info3 GET");
			out.println("name = " + name);
			out.println("id = " + Integer.toString(id));
		} finally {
			out.close();
		}	
	}


	@RequestMapping(params = "method=info4")
	public void getServletInfo4(HttpServletRequest request,
                HttpServletResponse response, @RequestParam("name") String name, @RequestParam("id") int id) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("MyFirstServlet method = info4");
			out.println("name = " + name);
			out.println("id = " + Integer.toString(id));
		} finally {
			out.close();
		}	
	}

	@RequestMapping(params = "method=info5")
	public String getServletInfo5(@RequestParam("name") String name, @RequestParam("id") int id)
	{
		return "msg";
	}

}
