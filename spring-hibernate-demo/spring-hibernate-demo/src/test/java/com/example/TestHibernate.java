package com.example;

import com.example.entities.Product;
import com.example.entities.Category;
import com.example.dao.ProductDao;
import com.example.dao.CategoryDao;
import com.example.util.HibernateConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestHibernate {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class);

        // Utiliser les interfaces au lieu des implémentations
        ProductDao productDao = context.getBean(ProductDao.class);
        CategoryDao categoryDao = context.getBean(CategoryDao.class);

        System.out.println("=== Test Hibernate Basic Operations ===");

        // Test des catégories
        System.out.println("\n1. Création des catégories...");
        Category electronics = new Category("Électronique", "Produits électroniques");
        Category clothing = new Category("Vêtements", "Vêtements et accessoires");

        categoryDao.save(electronics);
        categoryDao.save(clothing);

        System.out.println("Catégories créées: " + electronics.getName() + ", " + clothing.getName());

        // Test des produits
        System.out.println("\n2. Création des produits...");
        Product laptop = new Product("Ordinateur Portable", 1200.0, electronics);
        Product mouse = new Product("Souris USB", 25.50, electronics);
        Product tshirt = new Product("T-Shirt", 29.99, clothing);

        productDao.save(laptop);
        productDao.save(mouse);
        productDao.save(tshirt);

        System.out.println("Produits créés avec IDs: " +
                laptop.getId() + ", " + mouse.getId() + ", " + tshirt.getId());

        // Test de recherche
        System.out.println("\n3. Recherche de produits...");
        Product foundProduct = productDao.findById(laptop.getId());
        System.out.println("Produit trouvé par ID: " + foundProduct);

        // Test de recherche par nom
        System.out.println("\n4. Recherche par nom 'Souris'...");
        productDao.findByName("Souris").forEach(System.out::println);

        // Test de mise à jour
        System.out.println("\n5. Test de mise à jour...");
        laptop.setPrice(1150.0);
        productDao.update(laptop);
        Product updatedProduct = productDao.findById(laptop.getId());
        System.out.println("Prix mis à jour: " + updatedProduct.getPrice());

        // Test de récupération de tous les produits
        System.out.println("\n6. Tous les produits...");
        productDao.findAll().forEach(System.out::println);

        // Test de récupération de toutes les catégories
        System.out.println("\n7. Toutes les catégories...");
        categoryDao.findAll().forEach(System.out::println);

        // Test de suppression
        System.out.println("\n8. Test de suppression...");
        int initialCount = productDao.findAll().size();
        productDao.delete(mouse.getId());
        int finalCount = productDao.findAll().size();
        System.out.println("Produits avant suppression: " + initialCount);
        System.out.println("Produits après suppression: " + finalCount);

        context.close();
        System.out.println("\n=== Test Hibernate terminé avec succès ===");
    }
}