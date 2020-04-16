package com.edu.service.impl;

import com.edu.dao.CategoryDao;
import com.edu.dao.ProductDao;
import com.edu.dao.impl.CategoryDaoImpl;
import com.edu.dao.impl.ProductDaoImpl;
import com.edu.domain.Category;
import com.edu.domain.Product;
import com.edu.service.CategoryService;
import com.edu.utils.JDBC_untils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceimpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.findAll();
    }

    @Override
    public void save_data(Category category) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.save_data(category);
    }

    @Override
    public Category edit_data(String cid) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.edit_data(cid);
    }

    @Override
    public void update_data(Category category) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.update_data(category);
    }

    @Override
    public void delete_data(Integer cid) {
        /**
         * 事务管理：在业务层统一创建连接对象，保证多个DAO使用同一连接：
         * 1. 创建连接之后，将连接对象传递给DAO
         * 2. 创建一个连接对象，将连接绑定到当前线程(ThredLocal)
         */
        Connection con = null;
        try {
            con = JDBC_untils.getConnection();
            con.setAutoCommit(false);
            //删除分类之前，先将所属分类商品处理
            ProductDao productDao = new ProductDaoImpl();
            List<Product> list = productDao.findCid(cid);
            for (Product product : list) {
                product.getCategory().setCid(null);
                productDao.update_data(con,product);
            }
            CategoryDao categoryDao = new CategoryDaoImpl();
            categoryDao.delete_data(con,cid);
            //提交事务
            con.commit();
        }
        catch (Exception e){
            //回滚事务
            try {
                con.rollback();
            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            if(con != null){
                try {
                    con.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                con = null;
            }
        }
    }
}
