package com.store.auth.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import java.util.ArrayList;  
import java.util.List; 
import org.apache.log4j.Logger;

public class SessionFilter implements Filter {
    private FilterConfig config;
    private List<String> exactMatchExcludedURLs = new ArrayList<String>();
    private List<String> approximateMatchExcludedURLs = new ArrayList<String>();
    private static Logger logger = Logger.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
	this.config = config;
        this.loadConfiguration(config);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
	boolean passed = false;
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;

	String requestURI = ((HttpServletRequest) req).getRequestURI();
	logger.info("requestURI = " + requestURI);
	if (!this.exactMatchExcludedURLs.isEmpty()) {
            for (String exactMatchExcludedURL : this.exactMatchExcludedURLs) {
                if (requestURI.equals(exactMatchExcludedURL)) {
                    passed = true;
                    break;
                }
            }
        }
        if (!this.approximateMatchExcludedURLs.isEmpty() && !passed) {
            for (String approximateMatchExcludedURL : this.approximateMatchExcludedURLs) {
                if (requestURI.indexOf(approximateMatchExcludedURL) != -1) {
                    passed = true;
                    break;
                }
            }
        }

	if (passed) {
	    chain.doFilter(req, resp);
	} else {
            HttpSession session = httpReq.getSession(false);
            if (session != null) {
                String email = (String) session.getAttribute("email");
                if (email != null) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
            httpResp.sendRedirect("login.jsp");
	}
    }

    @Override
    public void destroy() {
    }

    /**
     * Load configuration from web.xml.
     * @param config
     */
    private void loadConfiguration(FilterConfig config) {
        String exactMatchedURLString = config.getInitParameter("exactMatchExcludedURLs");
        String approximateMatchedURLString = config.getInitParameter("approximateMatchExcludedURLs");
        if(null != exactMatchedURLString) {
            String[] tmps = exactMatchedURLString.split(",");
            for (String tmp : tmps) {
                tmp = tmp.trim();
                if(tmp.length() > 0) {
                    this.exactMatchExcludedURLs.add(tmp);
                }
            }
        }
        if(null != approximateMatchedURLString) {
            String[] tmps = approximateMatchedURLString.split(",");
            for (String tmp : tmps) {
                tmp = tmp.trim();
                if(tmp.length() > 0) {
                    this.approximateMatchExcludedURLs.add(tmp);
                }
            }
        }
	logger.info(this.exactMatchExcludedURLs);
	logger.info(this.approximateMatchExcludedURLs);
    }
}
