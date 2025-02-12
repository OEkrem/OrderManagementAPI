package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.UserRepository;
import com.oekrem.SpringMVCBackEnd.models.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateUserRepository implements UserRepository {

    private EntityManager entityManager;

    @Autowired
    public HibernateUserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("select distinct u from User u", User.class).list();
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
}
