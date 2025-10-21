package com.example.metier;

import com.example.dao.ProductDao;
import com.example.entities.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(int id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Product", Product.class)
                .list();
    }

    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void delete(int id) {
        Product product = findById(id);
        if (product != null) {
            sessionFactory.getCurrentSession().delete(product);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategoryId(int categoryId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Product WHERE category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByName(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Product WHERE name LIKE :name", Product.class)
                .setParameter("name", "%" + name + "%")
                .list();
    }
}