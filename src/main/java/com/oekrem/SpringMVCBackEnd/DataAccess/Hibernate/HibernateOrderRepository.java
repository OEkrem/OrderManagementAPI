package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.OrderRepository;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateOrderRepository implements OrderRepository {

    private EntityManager entityManager;

    @Autowired
    public HibernateOrderRepository(EntityManager entityManager) {this.entityManager = entityManager;}

    @Override
    @Transactional
    public List<Order> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Order", Order.class).list();
    }

    @Override
    @Transactional
    public Order addOrder(Order order) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(order);
        return order;
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(order);
        return order;
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Order orderToDelete = session.get(Order.class, id);
        session.remove(orderToDelete);
    }

    @Override
    @Transactional
    public Optional<Order> getOrderById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Order.class, id));
    }
}
