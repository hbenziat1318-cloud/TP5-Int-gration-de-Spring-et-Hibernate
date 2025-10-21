package com.example.dao;

import java.util.List;

public interface IDao<T> {
    void save(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}