package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.ProductRepository;
import com.oekrem.SpringMVCBackEnd.models.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateProductRepository implements ProductRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Page<Product> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        List<Product> products = session.createQuery("FROM Product", Product.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();

        Long totalOrders = session.createQuery("SELECT COUNT(o) FROM Product o ", Long.class)
                .getSingleResult();

        return new PageImpl<>(products, pageable, totalOrders);
    }

    @Override
    public Page<Product> findByCategoryId(Pageable pageable, Long categoryId) {
        Session session = entityManager.unwrap(Session.class);
        List<Product> products = session.createQuery("FROM Product o WHERE o.category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();

        Long totalOrders = session.createQuery("SELECT COUNT(o) FROM Product o WHERE o.category.id = :categoryId", Long.class)
                .setParameter("categoryId", categoryId)
                .getSingleResult();

        return new PageImpl<>(products, pageable, totalOrders);
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
}
