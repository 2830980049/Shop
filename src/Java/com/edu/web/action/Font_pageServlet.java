package com.edu.web.action;

import com.edu.domain.Category;
import com.edu.domain.PageBean;
import com.edu.domain.Product;
import com.edu.service.CategoryService;
import com.edu.service.ProductService;
import com.edu.service.impl.CategoryServiceimpl;
import com.edu.service.impl.ProductServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Front_pageServlet.do")
public class Font_pageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 0;
        String curr_page = req.getParameter("page");
        if(curr_page == null)
            page = 1;
        else
            page = Integer.parseInt(curr_page);
        //查询操作
        //查询所有分类
        CategoryService categoryService = new CategoryServiceimpl();
        List<Category> categoryList = categoryService.findAll();

        //分页查询商品数据:
        ProductService productService = new ProductServiceimpl();
        PageBean<Product> pageBean = productService.findByPage(page);
        System.out.println(pageBean);
        System.out.println(pageBean.getAllpage());
        req.setAttribute("pageBean",pageBean);
        req.setAttribute("categoryList",categoryList);
        req.getRequestDispatcher("Front_page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
