package com.homework.servletFilter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private Logger logger = Logger.getLogger(AuthenticationFilter.class);

    public void init(FilterConfig filterConfig) {
        logger.info("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;

        String uri = httpRequest.getRequestURI();
        logger.info("Requested Resource::" + uri);

        HttpSession httpSession = httpRequest.getSession(false);

        if ((httpSession == null ) && !(uri.endsWith("html") || uri.endsWith("Login")
                                                             || uri.endsWith("Register"))) {
            logger.info("Unauthorized access request");
            httpResponse.sendRedirect("login.html");
        } else {
            filterChain.doFilter(httpRequest, httpResponse);
        }
    }

    public void destroy() {
    }
}
