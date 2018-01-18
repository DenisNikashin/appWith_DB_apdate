package com.homework.servletFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Formattable;

public class AuthenticationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;

        String uri = httpRequest.getRequestURI();
        HttpSession httpSession = httpRequest.getSession(false);

        if ((httpSession == null ) && !(uri.endsWith("html") || uri.endsWith("Login")
                                                             || uri.endsWith("Register"))) {
            httpResponse.sendRedirect("login.html");
        } else {
            filterChain.doFilter(httpRequest, httpResponse);
        }
    }

    public void destroy() {
    }
}
