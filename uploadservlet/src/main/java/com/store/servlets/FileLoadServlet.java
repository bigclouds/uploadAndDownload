package com.store.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import java.util.Enumeration; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class FileLoadServlet {
	private static final long serialVersionUID = 1302377908285976972L;
	private static Logger logger = Logger.getLogger(FileLoadServlet.class);

	@RequestMapping(value = "/fileUploadone", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String params = request.getQueryString();  
		//String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String savePath = "/letv/upload";
		File file = new File(savePath);
            	System.out.println("upload test " +  savePath);
		if (!file.exists() && !file.isDirectory()) {
            		System.out.println(savePath+"create upload directory");
                        file.mkdir();
                }
		logger.info("本次请求类型及表单域分隔符：" + request.getContentType());
		logger.info("\nparams: " + params);
		try {
			Enumeration pNames=request.getParameterNames();
			while(pNames.hasMoreElements()){
			    String name=(String)pNames.nextElement();
			    String value=request.getParameter(name);
			    logger.info(name + "=" + value);
			}
		}catch (Exception e){
		}
		if (request.getContentLength() > 0) {
	           	InputStream inputStream = null;
	           	FileOutputStream outputStream = null;
			try {
				inputStream = request.getInputStream();
				// 给新文件拼上时间毫秒，防止重名
				long now = System.currentTimeMillis();
				file = new File(savePath, "file-" + now + ".txt");
				file.createNewFile();
				
				outputStream = new FileOutputStream(file);
				
				byte temp[] = new byte[1024];
				int size = -1;
				while ((size = inputStream.read(temp)) != -1) {
					outputStream.write(temp, 0, size);
				}
			} catch (IOException e) {
				request.getRequestDispatcher("uploadone.jsp").forward(request, response);
			} finally {
				outputStream.close();
				inputStream.close();
			}
		}
		request.getRequestDispatcher("/jsp/uploadone.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response)  
            throws ServletException,IOException{
		response.getWriter().write("doGet, jetty server start ok.");
	}
}
