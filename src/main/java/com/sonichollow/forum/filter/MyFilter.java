package com.sonichollow.forum.filter;

import com.sonichollow.forum.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MyFilter implements Filter {
    @Autowired
    private UserServiceImpl userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        boolean flag=false;
        boolean haveQuality=true;
        boolean rpLogin=false;
        String username = null;
        String password = null;
        if(uri.contains(".css") || uri.contains(".js") || uri.contains(".png")|| uri.contains(".jpg")){
            // Exclude css, js and image files
            filterChain.doFilter(request, response);
        }
        // Start filtering
        else{
            // 1. Find the username in the session. If the username is found in the session, mark the flag as true
            HttpSession session = request.getSession(false);
            if(session!=null &&session.getAttribute("username")!=null) {
                flag=true;
            }
            // 2. If the username is not found, find username and password in cookies.
            // If exists, query the database and get new session
            if(!flag) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie c : cookies) {
                        if ("username".equals(c.getName())) {
                            username = c.getValue();
                        }
                        if ("password".equals(c.getName())) {
                            password = c.getValue();
                        }
                    }
                    if (username != null && password != null) {
                        if ( userService.isUser(username, password)) {
                            session = request.getSession(true);
                            session.setAttribute("username", username);
                            flag = true;
                        }
                    }
                }
            }
            // Set the permission to these pages if the user hasn't logged in
            if (uri.contains("myInfo") || uri.contains("postList") || uri.contains("home")) {
                haveQuality = false;
            }
            // Set the permission to these pages if the user has already logged in
            if(flag){
                if (!uri.contains("login_success") && uri.contains("login")) {
                    rpLogin = true;
                }
            }
            if(rpLogin){
                request.setAttribute("msg","You have already logged in");
                request.getRequestDispatcher("remind").forward(request,response);
            }
            else if (flag || haveQuality) {
                filterChain.doFilter(request, response);
            }
            else {
                request.setAttribute("msg","No permission to this page! Please login first");
                request.getRequestDispatcher("remind").forward(request,response);
            }

        }

    }

    @Override
    public void destroy() {

    }
}