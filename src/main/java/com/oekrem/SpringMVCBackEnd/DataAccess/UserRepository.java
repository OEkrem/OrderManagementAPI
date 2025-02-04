package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;


public interface UserRepository {

    List<User> findAll();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUserById(int id);
}
