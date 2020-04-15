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
                else if("save_data".equals(methodName))
                    save_data(req,resp);
                    else if("edit".equals(methodName))
                        edit_data(req,resp);
                        else if("update".equals(methodName))
                            update_data(req,resp);
                            else if ("delete".equals(methodName))
                                delete_data(req,resp);
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

    /**
     * 页面保存页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void save_page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/category_add.jsp").forward(req,resp);
    }

    /**
     * 后台分类保存方法
     * @param req
     * @param resp
     */
    private void save_data(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryService categoryService = new CategoryServiceimpl();
        Category category = new Category();
        String cname = req.getParameter("cname");
        String cdesc = req.getParameter("cdesc");
        System.out.println(cdesc+ " " +cname);
        category.setCname(cname);
        category.setCdesc(cdesc);
        categoryService.save_data(category);
        // 页面跳转
        resp.sendRedirect(req.getContextPath()+"/CategoryServlet.do?method=findAll");
    }

    /**
     *后台数据编辑
     * @param req
     * @param resp
     */
    private void edit_data(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryService categoryService = new CategoryServiceimpl();
        String cid = req.getParameter("cid");
        Category category = categoryService.edit_data(cid);
        req.setAttribute("category",category);
        req.getRequestDispatcher("/admin/category_update.jsp").forward(req,resp);
    }

    /**
     * 后台数据更新
     * @param req
     * @param resp
     */
    private void update_data(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryService categoryService = new CategoryServiceimpl();
        String cname = req.getParameter("cname");
        String cdesc = req.getParameter("cdesc");
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        Category category = new Category();
        category.setCdesc(cdesc);
        category.setCname(cname);
        category.setCid(cid);
        categoryService.update_data(category);
        resp.sendRedirect(req.getContextPath()+"/CategoryServlet.do?method=findAll");
    }

    /**
     * 后台数据删除
     * @param req
     * @param resp
     */
    private void delete_data(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryService categoryService = new CategoryServiceimpl();
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        categoryService.delete_data(cid);
        resp.sendRedirect(req.getContextPath()+"/CategoryServlet.do?method=findAll");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
