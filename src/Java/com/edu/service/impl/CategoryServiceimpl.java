package com.edu.service.impl;

import com.edu.dao.CategoryDao;
import com.edu.dao.impl.CategoryDaoImpl;
import com.edu.domain.Category;
import com.edu.service.CategoryService;

import java.util.List;

public class CategoryServiceimpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.findAll();
    }
}
