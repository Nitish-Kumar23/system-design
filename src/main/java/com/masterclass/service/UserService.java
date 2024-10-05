package com.masterclass.service;

import com.masterclass.entity.User;

import java.util.List;

public interface UserService {

    void addUser(String firstName, String lastName);

    List<User> getAllUsers();

    User getUserById(Integer userId);
}
