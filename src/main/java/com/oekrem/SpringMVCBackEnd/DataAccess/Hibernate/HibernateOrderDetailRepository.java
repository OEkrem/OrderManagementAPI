package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.OrderDetailRepository;
import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateOrderDetailRepository implements OrderDetailRepository {

    private EntityManager entityManager;

    @Autowired
    public HibernateOrderDetailRepository(EntityManager entityManager) {this.entityManager = entityManager;}

    @Override
    @Transactional
    public List<OrderDetail> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from OrderDetail", OrderDetail.class).list();
    }

    @Override
    @Transactional
    public void addOrderDetail(OrderDetail orderDetail) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(orderDetail);
    }

    @Override
    @Transactional
    public void updateOrderDetail(OrderDetail orderDetail) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(orderDetail);
    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long id) {
        Session session = entityManager.unwrap(Session.class);
        OrderDetail orderDetailToDelete = session.get(OrderDetail.class, id);
        session.delete(orderDetailToDelete);
    }

    @Override
    @Transactional
    public OrderDetail getOrderDetailById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(OrderDetail.class, id);
    }
}
