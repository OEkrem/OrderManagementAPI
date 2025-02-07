package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.DataAccess.AddressRepository;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void deleteAddress(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Address addressToDelete = session.get(Address.class, id);
        session.delete(addressToDelete);
    }

    @Override
    @Transactional
    public Address getAddressById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Address.class, id);
    }
}
