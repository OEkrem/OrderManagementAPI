package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Payment;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void addPayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(payment);
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(payment);
    }

    @Override
    @Transactional
    public void deletePayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
        Payment paymentToDelete = session.get(Payment.class, payment.getId());
        session.delete(paymentToDelete);
    }

    @Override
    @Transactional
    public Payment getPaymentById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Payment.class, id);
    }
}
