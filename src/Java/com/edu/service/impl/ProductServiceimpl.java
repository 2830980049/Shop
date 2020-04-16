package com.edu.service.impl;

import com.edu.dao.ProductDao;
import com.edu.dao.impl.ProductDaoImpl;
import com.edu.domain.PageBean;
import com.edu.domain.Product;
import com.edu.service.ProductService;

import java.util.List;

public class ProductServiceimpl implements ProductService {
    @Override
    public List<Product> findAll() {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findAll();
    }

    @Override
    public void save_data(Product product) {
        ProductDao productDao = new ProductDaoImpl();
        productDao.save_data(product);
    }

    @Override
    public Product edit_data(Integer pid) {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.edit_data(pid);
    }

    @Override
    public void update(Product product) {
        ProductDao productDao = new ProductDaoImpl();
        productDao.update_data(product);
    }

    @Override
    public void delete_data(Integer pid) {
        ProductDao productDao = new ProductDaoImpl();
        productDao.delete_data(pid);
    }

    @Override
    public PageBean<Product> findByPage(int page) {
        PageBean<Product> pageBean = new PageBean<Product>();
        // 封装当前页数
        pageBean.setPage(page);
        // 封装每页显示的记录数
        Integer limit = 6;
        pageBean.setLimit(limit);
        // 封装总的记录数
        ProductDao productDao = new ProductDaoImpl();
        Integer All_counts = productDao.findCount();
        pageBean.setAllnum(All_counts);
        // 封装总页数(根据总记录数进行计算)
        Integer All_page = 0;
        if (All_counts % limit == 0)
            All_page = All_counts / limit;
        else All_page = All_counts / limit + 1;
        pageBean.setAllpage(All_page);
        // 封装每页显示数据的集合 select * from xx limit begin,limit;
        Integer begin = (page - 1) * limit;
        List<Product> list = productDao.findByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }


}
