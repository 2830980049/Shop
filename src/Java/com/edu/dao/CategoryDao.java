package com.edu.dao;

import com.edu.domain.Category;

import java.sql.Connection;
import java.util.List;

public interface CategoryDao {
    List<Category> findAll();

    void save_data(Category category);

    Category edit_data(String cid);

    void update_data(Category category);

    void delete_data(Integer cid);

    void delete_data(Connection con, Integer cid);
}
