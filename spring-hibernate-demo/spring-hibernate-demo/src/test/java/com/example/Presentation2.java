package com.example;

import com.example.entities.Product;
import com.example.entities.Category;
import com.example.dao.ProductDao;
import com.example.dao.CategoryDao;
import com.example.util.HibernateConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Presentation2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class);

        ProductDao productDao = context.getBean(ProductDao.class);
        CategoryDao categoryDao = context.getBean(CategoryDao.class);

        System.out.println("=== Démonstration Spring Hibernate ===");

        // Créer une catégorie
        Category category = new Category("Informatique", "Produits informatiques");
        categoryDao.save(category);
        System.out.println("✓ Catégorie créée: " + category.getName());

        // Créer des produits
        Product product1 = new Product("Souris Gaming", 45.99, category);
        Product product2 = new Product("Clavier Mécanique", 89.99, category);

        productDao.save(product1);
        productDao.save(product2);
        System.out.println("✓ Produits créés avec IDs: " + product1.getId() + ", " + product2.getId());

        // Afficher tous les produits
        System.out.println("\n=== Liste des produits ===");
        productDao.findAll().forEach(System.out::println);

        // Afficher toutes les catégories
        System.out.println("\n=== Liste des catégories ===");
        categoryDao.findAll().forEach(System.out::println);

        // Recherche par nom
        System.out.println("\n=== Recherche 'Souris' ===");
        productDao.findByName("Souris").forEach(System.out::println);

        context.close();
        System.out.println("\n=== Démonstration terminée ===");
    }
}