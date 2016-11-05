package com.store.servlets;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;

@Controller
public class UploadAction {

    private final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response){
	return "index";
    }

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model){
	List list = new ArrayList();
	Map<String, String> map = new HashMap<String, String>();
        String path = request.getSession().getServletContext().getRealPath("/upload");
	File dir = new File(path);
        if(!dir.exists()){
            return "index";
        }
	String[] paths = dir.list();
	for (String p : paths) {
	    String l = request.getContextPath()+"/upload/" + p;
	    list.add(l);
	    map.put(p, l);
	}
	final int size = list.size();
        model.addAttribute("filelist", (String[])list.toArray(new String[size]));
	model.addAttribute("map", map);
	return "list";
    }

    @RequestMapping(value = "/test.do", method = RequestMethod.GET)
    public void test(HttpServletRequest request, HttpServletResponse response){
        try {
	    response.getWriter().println("test");
        } catch (Exception e) {
            e.printStackTrace();
	}
    }

    @RequestMapping(value = "/download.do")
    public String download(HttpServletRequest request, HttpServletResponse response){
	String downloadfFileName = request.getParameter("filename");
        String path = request.getSession().getServletContext().getRealPath("/upload");
	log.debug("YYY " + path + "/" + downloadfFileName);
	File dir = new File(path + "/" + downloadfFileName);
        if(!dir.exists()){
            return "index";
	}
	try{
		response.reset();
		//response.setContentType("application/x-download");
		response.setContentType("application/octet-stream");
		//response.addHeader("Content-Disposition", "inline;filename = " +  java.net.URLEncoder.encode(downloadfFileName, "UTF-8"));
		response.addHeader("Content-Disposition", "attachment;filename = " +  java.net.URLEncoder.encode(downloadfFileName, "UTF-8"));
		response.addHeader("Content-Length", "" + dir.length());
	}catch(Exception e) {
		;
	}
	try{
		FileInputStream in = new FileInputStream(dir);
		OutputStream out = new BufferedOutputStream(response.getOutputStream());
		write(in, out);
	} catch(Exception e) {
            e.printStackTrace();
	}
	return "index";
    }

    public static void write(InputStream in, OutputStream out) throws IOException{
        try{
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } finally {
            try {
                in.close();
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/upload.do")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file,
		HttpServletRequest request, ModelMap model) {

        String path = request.getSession().getServletContext().getRealPath("/upload");
	File dir = new File(path);
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.equals(""))
		return "index";
        System.out.println(path + fileName);
        File targetFile = new File(path, fileName);
        if(!dir.exists() || !dir.isDirectory()){
            dir.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);

	return "result";
    }
}
