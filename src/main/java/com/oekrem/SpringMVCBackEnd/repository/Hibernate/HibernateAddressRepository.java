package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.repository.AddressRepository;
import com.oekrem.SpringMVCBackEnd.models.Address;
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
public class HibernateAddressRepository implements AddressRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Page<Address> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        List<Address> addresses = session.createQuery("from Address", Address.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();

        Long totalAddresses = session.createQuery("Select count(a) from Address a", Long.class)
                .getSingleResult();

        return new PageImpl<>(addresses, pageable, totalAddresses);
    }

    @Override
    @Transactional
    public Page<Address> getAddressesByUserId(Pageable pageable, Long userId) {
        Session session = entityManager.unwrap(Session.class);
        List<Address> addresses = session.createQuery("select u from Address u where u.user.id = :userId", Address.class)
                .setParameter("userId", userId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();
        Long totalAddresses = session.createQuery("Select count(a) from Address a", Long.class)
                .getSingleResult();
        return new PageImpl<>(addresses, pageable, totalAddresses);
    }

    @Override
    @Transactional
    public Address addAddress(Address address) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(address);
        return address;
    }

    @Override
    @Transactional
    public Address updateAddress(Address address) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(address);
        return address;
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Address addressToDelete = session.get(Address.class, id);
        session.remove(addressToDelete);
    }

    @Override
    @Transactional
    public Optional<Address> getAddressById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Address.class, id));
    }

    @Override
    public Optional<User> getOwnerById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("select u From User u where u.id = " +
                                                "(Select a.user.id from Address a where a.id = :id)", User.class)
                .setParameter("id", id)
                .getResultStream().findFirst();
    }

}
