package com.edu.dao;

import com.edu.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();

    void save_data(Category category);

    Category edit_data(String cid);

    void update_data(Category category);

    void delete_data(Integer cid);
}
