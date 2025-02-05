package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.Models.Category;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void addCategory(Category category) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(category);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(category);
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
    public Category getCategoryById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Category.class, id);
    }
}
