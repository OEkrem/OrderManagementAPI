package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.OrderRepository;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void addOrder(Order order) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(order);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Order orderToDelete = session.get(Order.class, id);
        session.delete(orderToDelete);
    }

    @Override
    @Transactional
    public Order getOrderById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Order.class, id);
    }
}
