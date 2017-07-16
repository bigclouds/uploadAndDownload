package com.store.env;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@Controller
public class Env {

	@Resource(name = "configpf")
	private Properties env;

	@Value("${uploadtype}")
	private String uploadtype;

	@Value("${uploadPrefix}")
	private String uploadPrefix;

	@Value("${hdfsUrl}")
	private String hdfsUrl;

	//public void setProperties(Properties p) {
	//	env = p;
	//}

	public String get(String key) {
		return env.getProperty(key);
	}

	public Properties getProperties() {
		return env;
	}

	@RequestMapping(value = "/env")
	public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
		try {
			out.println(get("uploadtype"));
			out.println("<br>");
			out.println(uploadtype);
			out.println("<br>");
			out.println(get("uploadPrefix"));
			out.println("<br>");
			out.println(uploadPrefix);
			out.println("<br>");
			out.println(get("hdfsUrl"));
			out.println("<br>");
			out.println(hdfsUrl);
			out.println("<br>");
			//out.println(this.uploadtype);
			//out.println(this.uploadPrefix);
			//out.println(this.hdfsUrl);
			out.println(get("MQ_HOST"));
			out.println("<br>");
			out.println(get("MQ_PORT"));
			out.println("<br>");
			out.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		
	}
}
