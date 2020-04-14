package com.edu.dao;

import com.edu.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
}
