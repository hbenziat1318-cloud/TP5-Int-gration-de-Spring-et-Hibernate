package com.example.dao;

import com.example.entities.Product;
import java.util.List;

public interface ProductDao extends IDao<Product> {
    List<Product> findByCategoryId(int categoryId);
    List<Product> findByName(String name);
}