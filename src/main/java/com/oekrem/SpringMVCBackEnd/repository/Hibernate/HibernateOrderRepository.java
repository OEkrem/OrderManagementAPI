package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.OrderRepository;
import com.oekrem.SpringMVCBackEnd.models.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateOrderRepository implements OrderRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
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
    public void deleteAllOrders(List<Order> orders) {
        Session session = entityManager.unwrap(Session.class);
        for (Order order : orders) {
            session.remove(order);
        }
        session.flush();
        session.clear();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Order.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByOrdersDateBefore(LocalDate date) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("select o from Order o where o.date < :date", Order.class)
                .setParameter("date", date)
                .list();
    }
}
