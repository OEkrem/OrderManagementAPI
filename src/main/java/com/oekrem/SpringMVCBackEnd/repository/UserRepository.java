package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    List<User> findAll(/*int pageNumber, int pageSize*/);
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);

    Optional<User> findUserByEmail(String email);

}
