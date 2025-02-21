package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.UserRepository;
import com.oekrem.SpringMVCBackEnd.models.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateUserRepository implements UserRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public List<User> findAll(/*int pageNumber, int pageSize*/) {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery("select distinct u from User u", User.class);
        //query.setFirstResult((pageNumber - 1) * pageSize); // Hangi kayıttan başlayacağını belirler
        //query.setMaxResults(pageSize);
        return query.list(); // getResultList hibernate 5 ile daha uyumlu imiş, 6 ise list
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
