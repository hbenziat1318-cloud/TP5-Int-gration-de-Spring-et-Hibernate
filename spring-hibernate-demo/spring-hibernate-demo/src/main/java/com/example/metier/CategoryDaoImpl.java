package com.example.metier;

import com.example.dao.CategoryDao;
import com.example.entities.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(int id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Category", Category.class)
                .list();
    }

    @Override
    public void update(Category category) {
        sessionFactory.getCurrentSession().update(category);
    }

    @Override
    public void delete(int id) {
        Category category = findById(id);
        if (category != null) {
            sessionFactory.getCurrentSession().delete(category);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByName(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Category WHERE name = :name", Category.class)
                .setParameter("name", name)
                .uniqueResult();
    }
}