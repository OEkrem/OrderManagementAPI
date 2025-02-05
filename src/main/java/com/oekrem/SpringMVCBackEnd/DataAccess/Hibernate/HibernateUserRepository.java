package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.UserRepository;
import com.oekrem.SpringMVCBackEnd.Models.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        List<User> users = session.createQuery("select distinct u from User u", User.class).list();
        return users;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);

    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Session session = entityManager.unwrap(Session.class);
        User user2 = session.get(User.class, id);
        session.delete(user2);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id.intValue());
    }
}
