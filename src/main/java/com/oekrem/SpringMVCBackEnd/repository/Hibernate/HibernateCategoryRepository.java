package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.models.Category;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateCategoryRepository implements CategoryRepository {

    private final EntityManager entityManager;

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
