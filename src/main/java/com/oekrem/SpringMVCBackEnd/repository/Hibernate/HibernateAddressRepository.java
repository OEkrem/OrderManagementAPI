package com.oekrem.SpringMVCBackEnd.repository.Hibernate;

import com.oekrem.SpringMVCBackEnd.repository.AddressRepository;
import com.oekrem.SpringMVCBackEnd.models.Address;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateAddressRepository implements AddressRepository {

    private EntityManager entityManager;

    @Autowired
    public HibernateAddressRepository(EntityManager entityManager) {this.entityManager = entityManager;}

    @Override
    @Transactional
    public List<Address> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Address", Address.class).list();
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
    @Transactional
    public List<Address> getAddressesByUserId(Long id) {
        Session session = entityManager.unwrap(Session.class);
        List<Address> addresses = session.createQuery("select u from Address u where u.user.id = :id", Address.class)
                .setParameter("id", id).list();
        return addresses;
    }
}
