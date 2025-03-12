package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import com.oekrem.SpringMVCBackEnd.repository.PaymentRepository;
import com.oekrem.SpringMVCBackEnd.models.Payment;
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
public class HibernatePaymentRepository implements PaymentRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Page<Payment> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        List<Payment> payments = session.createQuery("from Payment", Payment.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();
        Long totalPayments = session.createQuery("select count(p) from Payment p", Long.class)
                .getSingleResult();
        return new PageImpl<>(payments, pageable, totalPayments);
    }

    @Override
    public Page<Payment> findByPaymentStatus(Pageable pageable, PaymentStatus paymentStatus) {
        Session session = entityManager.unwrap(Session.class);
        List<Payment> payments = session.createQuery("from Payment o where o.paymentStatus = :status", Payment.class)
                .setParameter("status", paymentStatus)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();
        Long totalPayments = session.createQuery("select count(p) from Payment p where p.paymentStatus = :paymentStatus", Long.class)
                .setParameter("paymentStatus", paymentStatus)
                .getSingleResult();
        return new PageImpl<>(payments, pageable, totalPayments);
    }

    @Override
    @Transactional
    public Payment addPayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(payment);
        session.flush(); // -----------------------------------------
        return payment;
    }

    @Override
    @Transactional
    public Payment updatePayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
        return session.merge(payment);
    }

    @Override
    @Transactional
    public void deletePayment(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Payment paymentToDelete = session.get(Payment.class, id);
        session.remove(paymentToDelete);
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
        return session.createQuery("from Payment o where o.order.id = :orderId", Payment.class)
                .setParameter("orderId", orderId)
                .list()
                .stream()
                .findFirst();
    }
}
