package com.oekrem.SpringMVCBackEnd.DataAccess.Hibernate;

import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Models.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional // test sonunda yapılan değişiklikler geri alınır
public class HibernateUserRepositoryIntegrationTest {

    @Autowired
    private HibernateUserRepository userRepository;

    private User testUser;
    private Address testUserAddress;

    @BeforeEach //
    public void setUp(){
        testUserAddress = new Address(null, "Home", 4, 2, "4/6", "St.Esenler", "İstanbul", "Türkiye", null);
        testUser = new User(null, "ekrem_37", "1234", "Ekrem", "Yıldırım", "ekrem@hotmail.com", "5452330184", null);
        testUserAddress.setUser(testUser);
        testUser.getAddresses().add(testUserAddress);
    }

    @Test
    public void testCreateUser() {

        userRepository.addUser(testUser);

    }
}
