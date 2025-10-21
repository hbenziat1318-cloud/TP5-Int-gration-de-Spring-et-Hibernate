package com.example;

import com.example.entities.Product;
import com.example.entities.Category;
import com.example.dao.ProductDao;    // ← Utiliser l'interface
import com.example.dao.CategoryDao;  // ← Utiliser l'interface
import com.example.util.HibernateConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestWithCategory {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class);

        // Utiliser les interfaces au lieu des implémentations
        ProductDao productDao = context.getBean(ProductDao.class);
        CategoryDao categoryDao = context.getBean(CategoryDao.class);

        // Créer des catégories
        Category electronics = new Category("Électronique", "Produits électroniques");
        Category clothing = new Category("Vêtements", "Vêtements et accessoires");

        categoryDao.save(electronics);
        categoryDao.save(clothing);

        // Créer des produits avec catégories
        Product laptop = new Product("Laptop", 999.99, electronics);
        Product smartphone = new Product("Smartphone", 699.99, electronics);
        Product tshirt = new Product("T-Shirt", 29.99, clothing);

        productDao.save(laptop);
        productDao.save(smartphone);
        productDao.save(tshirt);

        // Afficher tous les produits
        System.out.println("=== Tous les produits ===");
        productDao.findAll().forEach(System.out::println);

        // Afficher toutes les catégories
        System.out.println("\n=== Toutes les catégories ===");
        categoryDao.findAll().forEach(System.out::println);

        // Test des relations
        System.out.println("\n=== Test des relations ===");
        System.out.println("Produits dans la catégorie Électronique:");
        productDao.findByCategoryId(electronics.getId()).forEach(p ->
                System.out.println(" - " + p.getName() + " (" + p.getPrice() + "€)")
        );

        context.close();
    }
}