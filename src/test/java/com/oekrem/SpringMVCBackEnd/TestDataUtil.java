package com.oekrem.SpringMVCBackEnd;

import com.oekrem.SpringMVCBackEnd.models.Address;
import com.oekrem.SpringMVCBackEnd.models.User;

public class TestDataUtil {

    public static User createTestUserA(){
        User user = new User();
        user.setId(1L);
        user.setUsername("TestUser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setPhone("123456789");
        user.setAddresses(null);
        return user;
    }
    public static User createTestUserB(){
        User user = new User();
        user.setId(2L);
        user.setUsername("TestUser2");
        user.setFirstName("Test2");
        user.setLastName("User2");
        user.setEmail("test2@test.com");
        user.setPassword("password2");
        user.setPhone("123456789");
        user.setAddresses(null);
        return user;
    }

    public static Address createTestAddressA(){
        Address address = new Address();
        address.setName("TestAddress");
        address.setDoorNumber(1);
        address.setFloor(1);
        address.setBuildingNumber("1/1");
        address.setStreet("Test");
        address.setCity("Test");
        address.setCountry("Test");
        return address;
    }

    public static Address createTestAddressB(){
        Address address = new Address();
        address.setName("TestAddress2");
        address.setDoorNumber(2);
        address.setFloor(2);
        address.setBuildingNumber("2/2");
        address.setStreet("Test2");
        address.setCity("Test2");
        address.setCountry("Test2");
        return address;
    }

}
