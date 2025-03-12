package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.OrderDetailRepository;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
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
public class HibernateOrderDetailRepository implements OrderDetailRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Page<OrderDetail> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        List<OrderDetail> orderDetails = session.createQuery("FROM OrderDetail", OrderDetail.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();

        Long totalOrders = session.createQuery("SELECT COUNT(o) FROM OrderDetail o", Long.class)
                .getSingleResult();

        return new PageImpl<>(orderDetails, pageable, totalOrders);
    }

    @Override
    @Transactional
    public Page<OrderDetail> findByOrderId(Pageable pageable, Long orderId) {
        Session session = entityManager.unwrap(Session.class);
        List<OrderDetail> orderDetails = session.createQuery("FROM OrderDetail o WHERE o.order.id = :orderId", OrderDetail.class)
                .setParameter("orderId", orderId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();

        Long totalOrders = session.createQuery("SELECT COUNT(o) FROM OrderDetail o WHERE o.order.id = :orderId", Long.class)
                .setParameter("orderId", orderId)
                .getSingleResult();

        return new PageImpl<>(orderDetails, pageable, totalOrders);
    }

    @Override
    @Transactional
    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(orderDetail);
        session.flush(); // ---------------------------------------------------------
        return orderDetail;
    }

    @Override
    @Transactional
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        Session session = entityManager.unwrap(Session.class);
        return session.merge(orderDetail);
    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long id) {
        Session session = entityManager.unwrap(Session.class);
        OrderDetail orderDetailToDelete = session.get(OrderDetail.class, id);
        session.remove(orderDetailToDelete);
    }

    @Override
    @Transactional
    public Optional<OrderDetail> getOrderDetailById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(OrderDetail.class, id));
    }

}
