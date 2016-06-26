package com.gft.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/fileUpload")
public class SecurityFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().length() == 0) {
            throw new ServletException("user id is mandatory");
        }
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.addHeader("securityFilter","OK");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
