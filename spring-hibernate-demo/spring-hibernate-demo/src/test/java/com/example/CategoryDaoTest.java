package com.example;

import com.example.entities.Category;
import com.example.dao.CategoryDao;
import com.example.util.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HibernateConfig.class)
@Transactional
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void testSimpleCategoryOperations() {
        System.out.println("=== Test Simple Category ===");
        System.out.println("CategoryDao class: " + categoryDao.getClass().getName());

        // Test simple de création
        Category category = new Category("Test Simple", "Description simple");
        categoryDao.save(category);

        assertTrue(category.getId() > 0);
        System.out.println("Catégorie créée avec ID: " + category.getId());

        // Test de lecture
        Category found = categoryDao.findById(category.getId());
        assertNotNull(found);
        assertEquals("Test Simple", found.getName());

        System.out.println("Test réussi !");
    }

    @Test
    public void testFindAllCategories() {
        System.out.println("=== Test Find All Categories ===");

        Category cat1 = new Category("Catégorie 1", "Description 1");
        Category cat2 = new Category("Catégorie 2", "Description 2");

        categoryDao.save(cat1);
        categoryDao.save(cat2);

        var categories = categoryDao.findAll();
        assertFalse(categories.isEmpty());
        assertTrue(categories.size() >= 2);

        System.out.println("Nombre de catégories: " + categories.size());
        categories.forEach(cat -> System.out.println(" - " + cat.getName()));
    }
}