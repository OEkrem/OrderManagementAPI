package com.oekrem.SpringMVCBackEnd.Services;


import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUserById(int id);
}
