package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.Models.Category;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateCategoryRepository implements CategoryRepository {

    private EntityManager entityManager;

    @Autowired
    public HibernateCategoryRepository(EntityManager entityManager) {this.entityManager = entityManager;}

    @Override
    @Transactional
    public List<Category> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Category", Category.class).list();
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(category);
        return category;
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(category);
        return category;
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Category categoryToDelete = session.get(Category.class, id);
        session.delete(categoryToDelete);
    }

    @Override
    @Transactional
    public Optional<Category> getCategoryById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Category.class, id));
    }
}
