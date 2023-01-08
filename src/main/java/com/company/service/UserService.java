package com.company.service;

import com.company.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getById(long id);
    Optional<User> getUserByMail(String mail);
    void addUser(User newUser);
    void deleteUser(Long id);
    void updateUser(long id, User userForUpdate);
    List<User> getUsersList();
}
