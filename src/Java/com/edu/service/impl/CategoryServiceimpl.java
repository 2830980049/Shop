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

    @Override
    public void save_data(Category category) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.save_data(category);
    }

    @Override
    public Category edit_data(String cid) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.edit_data(cid);
    }

    @Override
    public void update_data(Category category) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.update_data(category);
    }

    @Override
    public void delete_data(Integer cid) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.delete_data(cid);
    }
}
