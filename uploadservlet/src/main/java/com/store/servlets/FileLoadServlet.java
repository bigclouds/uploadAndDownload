package com.store.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class FileLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1302377908285976972L;
	private static Logger logger = Logger.getLogger(FileLoadServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String params = request.getQueryString();  
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);
            	System.out.println("upload test " +  savePath);
		if (!file.exists() && !file.isDirectory()) {
            		System.out.println(savePath+"create upload directory");
                        file.mkdir();
                }
		logger.info("本次请求类型及表单域分隔符：" + request.getContentType());
		logger.info("------------ FileLoadServlet ------------%s" +  params);
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
		request.getRequestDispatcher("uploadone.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response)  
            throws ServletException,IOException{
		response.getWriter().write("doGet, jetty server start ok.");
	}
}
