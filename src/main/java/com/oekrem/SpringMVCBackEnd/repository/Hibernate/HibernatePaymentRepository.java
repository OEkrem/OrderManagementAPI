package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.PaymentRepository;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernatePaymentRepository implements PaymentRepository {

    private EntityManager entityManager;

    @Autowired
    public HibernatePaymentRepository(EntityManager entityManager) {this.entityManager = entityManager;}

    @Override
    @Transactional
    public List<Payment> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Payment", Payment.class).list();
    }

    @Override
    @Transactional
    public Payment addPayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(payment);
        return payment;
    }

    @Override
    @Transactional
    public Payment updatePayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(payment);
        return payment;
    }

    @Override
    @Transactional
    public void deletePayment(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Payment paymentToDelete = session.get(Payment.class, id);
        session.delete(paymentToDelete);
    }

    @Override
    @Transactional
    public Optional<Payment> getPaymentById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Payment.class, id));
    }

    @Override
    @Transactional
    public Optional<Payment> getPaymentByOrderId(Long orderId){
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Payment where orderId = :orderId").setParameter("orderId", orderId).list().stream().findFirst();
    }
}
