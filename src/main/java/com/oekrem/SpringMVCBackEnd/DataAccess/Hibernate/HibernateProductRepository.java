package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.ProductRepository;
import com.oekrem.SpringMVCBackEnd.Models.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateProductRepository implements ProductRepository {

    private EntityManager entityManager;

    @Autowired
    public HibernateProductRepository(EntityManager entityManager) {this.entityManager = entityManager;}

    @Override
    @Transactional
    public List<Product> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Product", Product.class).list();
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(product);
        return product;
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(product);
        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Product productToDelete = session.get(Product.class, id);
        session.remove(productToDelete);
    }

    @Override
    @Transactional
    public Optional<Product> getProductById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Product.class, id));
    }

    @Override
    @Transactional
    public List<Product> getProductsByCategoryId(Long categoryId) {
        Session session = entityManager.unwrap(Session.class);
        List<Product> products = session.createQuery("Select u from Product u where u.category.id = :id", Product.class)
                .setParameter("id", categoryId).list();
        return products;
    }
}
