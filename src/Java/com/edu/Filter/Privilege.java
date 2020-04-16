package com.edu.Filter;

import com.edu.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Privilege implements Filter {
    public static final List<String> login_page = new ArrayList<String>();
    static {
        login_page.add("/login.jsp");
        login_page.add("/Front_page.jsp");
        login_page.add("/UserServlet.do");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse reps = (HttpServletResponse)servletResponse;
        String url = req.getRequestURI();
        String path = req.getContextPath();
        //取得首页名称
        String targetURL = url.substring(path.length());
        User user = (User)req.getSession().getAttribute("user");
        System.out.println("user "+user);
        boolean flag = false;
        System.out.println(targetURL);
        for (String list:login_page){
            if(targetURL.contains(list) || targetURL.equals(list)){
                System.out.println("targetURL"+targetURL);
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        if (user == null){
            if(!targetURL.equals("/"))
                req.setAttribute("msg","你还没有登录，请先登录");
            req.getRequestDispatcher("/login.jsp").forward(req,reps);
            return;
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
    }
}
