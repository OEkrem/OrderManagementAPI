package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.UserRepository;
import com.oekrem.SpringMVCBackEnd.models.User;
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
public class HibernateUserRepository implements UserRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Page<User> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);

        List<User> users = session.createQuery("FROM User", User.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();

        Long totalOrders = session.createQuery("SELECT COUNT(o) FROM User o", Long.class)
                .getSingleResult();

        return new PageImpl<>(users, pageable, totalOrders);
    }

    @Override
    @Transactional
    public User addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Session session = entityManager.unwrap(Session.class);
        User userToDelete = session.get(User.class, id);
        session.remove(userToDelete);
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(User.class, id));
    }

    @Override
    @Transactional
    public Optional<User> getUserByEmail(String email) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email).uniqueResult();
        return Optional.ofNullable(user);
    }
}
