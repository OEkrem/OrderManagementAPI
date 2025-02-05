package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.ProductRepository;
import com.oekrem.SpringMVCBackEnd.Models.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void addProduct(Product product) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Product productToDelete = session.get(Product.class, id);
        session.delete(productToDelete);
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Product.class, id);
    }
}
