package com.edu.dao;

import com.edu.domain.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    void save_data(Product product);
}
