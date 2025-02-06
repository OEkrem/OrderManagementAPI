package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    List<User> findAll();
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);

}
