package com.tmall_backend.bysj.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    private static final String SESSION_KEY = "current_customer_session";
    private static final String COOKIE_KEY = "current_customer_cookie";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Doing Filter");
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute(SESSION_KEY) != null) {
            chain.doFilter(request, response);
        } else if (req.getRequestURI().contains("login") || req.getRequestURI().contains("register")) {
            chain.doFilter(request, response);
        } else {
            boolean isLogin = false;
            Cookie[] cs =  req.getCookies();
            if(cs != null && cs.length > 0) {
                for(Cookie c : cs) {
                    if(COOKIE_KEY.equals(c.getName())) {
                        req.getSession().setAttribute("user", c.getValue());
                        isLogin = true;
                        chain.doFilter(request, response);
                    }
                }
            }
            if (!isLogin) {
                HttpServletResponse res = (HttpServletResponse) response;
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}
