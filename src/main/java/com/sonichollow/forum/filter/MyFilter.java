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
//        System.out.println(uri);
        boolean flag=false;
        boolean haveQuality=true;
        boolean rpLogin=false;
        String username = null;
        String password = null;
        if(uri.contains(".css") || uri.contains(".js") || uri.contains(".png")|| uri.contains(".jpg")){
            //如果发现是css或者js文件，直接放行
//            System.out.println("!!!!!!!!!!!!!");
            filterChain.doFilter(request, response);
        }
        //在else中放对网页过滤的代码
        else{
            //1.先从session中找用户，如果session中找到用户，说明已经登录，直接使用此用户对象
            HttpSession session = request.getSession(false);
            if(session!=null &&session.getAttribute("username")!=null) {
                flag=true;
            }
            //2.如果session中找不到，再从cookie中找用户信息(用户名和密码)，如果cookie中有，则查询数据库，再生成相应的session
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
            //如未登录 这些网页不能浏览
            if (uri.contains("myInfo") || uri.contains("postList") || uri.contains("home")) {
                haveQuality = false;
            }
            //如登录，则登录页面不能浏览
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