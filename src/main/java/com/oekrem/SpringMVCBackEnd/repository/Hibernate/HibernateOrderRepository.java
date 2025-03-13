package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.repository.OrderRepository;
import com.oekrem.SpringMVCBackEnd.models.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<Order> findAllByUserId(Pageable pageable, Long userId) {
        Session session = entityManager.unwrap(Session.class);

        List<Order> orders = session.createQuery("FROM Order o WHERE o.user.id = :userId", Order.class)
                .setParameter("userId", userId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();

        Long totalOrders = session.createQuery("SELECT COUNT(o) FROM Order o WHERE o.user.id = :userId", Long.class)
                .setParameter("userId", userId)
                .getSingleResult();

        return new PageImpl<>(orders, pageable, totalOrders);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);

        List<Order> orders = session.createQuery("FROM Order", Order.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();

        Long totalOrders = session.createQuery("SELECT COUNT(o) FROM Order o", Long.class)
                .getSingleResult();

        return new PageImpl<>(orders, pageable, totalOrders);
    }

    @Override
    @Transactional
    public Order addOrder(Order order) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(order);
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
    public Optional<User> getOwnerById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("select u From User u where u.id = " +
                        "(select o.user.id from Order o where o.user.id = :id)", User.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
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
