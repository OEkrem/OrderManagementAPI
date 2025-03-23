package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.models.Category;
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
public class HibernateCategoryRepository implements CategoryRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Page<Category> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
         List<Category> categories = session.createQuery("from Category", Category.class)
                 .setFirstResult((int) pageable.getOffset())
                 .setMaxResults(pageable.getPageSize())
                 .list();
         Long totalCategories = session.createQuery("select count(c) from Category c", Long.class)
                 .getSingleResult();

         return new PageImpl<>(categories, pageable, totalCategories);
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
        session.remove(categoryToDelete);
    }

    @Override
    @Transactional
    public Optional<Category> getCategoryById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Category.class, id));
    }
}
