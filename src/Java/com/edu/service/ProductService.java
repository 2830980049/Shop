package com.edu.service;

import com.edu.domain.PageBean;
import com.edu.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save_data(Product product);

    Product edit_data(Integer pid);

    void update(Product product);

    void delete_data(Integer pid);

    PageBean<Product> findByPage(int page);
}
