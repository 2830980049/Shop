package com.edu.service.impl;

import com.edu.dao.ProductDao;
import com.edu.dao.impl.ProductDaoImpl;
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
}
