package com.edu.web.action;

import com.edu.domain.User;
import com.edu.service.UserService;
import com.edu.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet.do")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        if ("login".equals(methodName)){
            login(req,resp);
        }
        else if("logout".equals(methodName))
            logout(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    /**
     * 登出
     * @param req
     * @param resp
     */
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 将Sessio销毁
        req.getSession().invalidate();
        // 跳转页面
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }

    /**
     * UserServlet 登录
     * @param req
     * @param reps
     */
    private void login(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+" "+password);

        //数据封装
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //处理数据
        UserService userService = new UserServiceimpl();
        boolean flag = userService.login(user);
        if (flag){
            //登录成功
            //将用户信息进行保存，跳转
            req.getSession().setAttribute("user",user);
            reps.sendRedirect(req.getContextPath()+"/CategoryServlet.do?method=findAll");
        }
        else{
            //登录失败
            req.setAttribute("msg","用户名或者密码错误！");
            req.getRequestDispatcher("/login.jsp").forward(req,reps);
        }
    }
}
