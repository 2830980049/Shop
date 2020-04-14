package com.edu.web.action;

import com.edu.domain.Category;
import com.edu.service.CategoryService;
import com.edu.service.impl.CategoryServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet.do")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        if ("findAll".equals(methodName)){
            //查询所有分类
            findAll(req,resp);
        }
            else if ("save_page".equals(methodName))
                //页面跳转
                save_page(req,resp);
    }

    /**
     * 后台分类管理查询所有分类方法
     * @param req
     * @param resp
     */
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用业务层处理数据
        CategoryService categoryService = new CategoryServiceimpl();
        List<Category> list = categoryService.findAll();

        //页面跳转
        req.setAttribute("list",list);
        req.getRequestDispatcher("/admin/category_list.jsp").forward(req,resp);
    }

    private void save_page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/category_add.jsp").forward(req,resp);
    }






    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
