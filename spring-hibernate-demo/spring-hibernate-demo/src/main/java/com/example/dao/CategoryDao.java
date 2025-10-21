package com.example.dao;

import com.example.entities.Category;

public interface CategoryDao extends IDao<Category> {
    Category findByName(String name);
}