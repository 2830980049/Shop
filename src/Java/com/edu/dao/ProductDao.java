package com.edu.dao;

import com.edu.domain.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    void save_data(Product product);

    Product edit_data(Integer pid);

    void update_data(Product product);

    void delete_data(Integer pid);

    List<Product> findCid(Integer cid);

    void update_data(Connection con, Product product);

    Integer findCount();

    List<Product> findByPage(Integer begin, Integer limit);
}
