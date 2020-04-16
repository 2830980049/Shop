package com.edu.web.action;

import com.edu.domain.Category;
import com.edu.domain.Product;
import com.edu.service.CategoryService;
import com.edu.service.ProductService;
import com.edu.service.impl.CategoryServiceimpl;
import com.edu.service.impl.ProductServiceimpl;
import com.edu.utils.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/ProductServlet.do")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        if ("findAll".equals(methodName))
            findAll(req,resp);
            else if("save_page".equals(methodName))
                save_page(req,resp);
                else if("save_data".equals(methodName))
                    save_data(req,resp);
                    else if("edit".equals(methodName))
                        edict_data(req,resp);
                        else if("update".equals(methodName))
                            update_data(req,resp);
                            else if("delete".equals(methodName))
                                delete_data(req,resp);
    }


    /**
     * 查询所有商品
     * @param req
     * @param resp
     */
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductServiceimpl();
        List<Product> product = productService.findAll();
        req.setAttribute("product",product);
        req.getRequestDispatcher("/admin/product_list.jsp").forward(req,resp);
    }

    /**
     * 添加商品页面
     * @param req
     * @param resp
     */
    private void save_page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryService categoryService = new CategoryServiceimpl();
        List<Category> list = categoryService.findAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("admin/product_add.jsp").forward(req,resp);
    }

    /**
     * 后台商品保存
     * @param req
     * @param resp
     */
    private void save_data(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductServiceimpl();
        //文件上传
        Map<String,String> map = FileUpload.uploadFile(req);
        //将数据封装
        Product product = new Product();
        product.setPname(map.get("pname"));
        product.setPrice(Double.parseDouble(map.get("price")));
        product.setPath(map.get("path"));
        product.setFilename(map.get("filename"));
        product.setAuthor(map.get("author"));
        product.setDescription(map.get("description"));
        product.getCategory().setCid(Integer.parseInt(map.get("cid")));
        //处理数据
        productService.save_data(product);
        resp.sendRedirect(req.getContextPath()+"/ProductServlet.do?method=findAll");
    }

    /**
     *后台商品编辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void edict_data(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        ProductService productService = new ProductServiceimpl();
        Product product = productService.edit_data(pid);
        System.out.println(product);
        CategoryService categoryService = new CategoryServiceimpl();
        List<Category> categories = categoryService.findAll();
        req.setAttribute("product",product);
        req.setAttribute("categoryList",categories);
        req.getRequestDispatcher("/admin/product_update.jsp").forward(req,resp);
    }

    /**
     *后台商品更新
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void update_data(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,String> map = FileUpload.uploadFile(req);
        Product product = new Product();
        product.setPid(Integer.parseInt(map.get("pid")));
        product.setPname(map.get("pname"));
        product.setPrice(Double.parseDouble(map.get("price")));
        product.setPath(map.get("path"));
        product.setFilename(map.get("filename"));
        product.setAuthor(map.get("author"));
        product.setDescription(map.get("description"));
        product.getCategory().setCid(Integer.parseInt(map.get("cid")));
        ProductService productService = new ProductServiceimpl();
        productService.update(product);
        resp.sendRedirect(req.getContextPath()+"/ProductServlet.do?method=findAll");
    }

    /**
     * 后台数据删除
     * @param req
     * @param resp
     */
    private void delete_data(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = new ProductServiceimpl();
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        //删除图片
        Product product = productService.edit_data(pid);
        if(product.getPath() != null && !"".equals(product.getPath())){
            File file = new File(product.getPath());
            if (file.exists())
                file.delete();
        }

        productService.delete_data(pid);
        System.out.println(product+" "+pid);
        resp.sendRedirect(req.getContextPath()+"/ProductServlet.do?method=findAll");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
