package com.edu.service;

import com.edu.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save_data(Product product);
}
