package com.masterclass.impl;

import com.masterclass.entity.Airline;
import com.masterclass.entity.User;
import com.masterclass.exceptionHandler.ApplicationException;
import com.masterclass.repository.UserRepo;
import com.masterclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void addUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepo.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {
//        return new ArrayList<>(Arrays.asList(new User(1,"nitish","kumar"), new User(2,"test","user")));
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Integer userId) {
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new ApplicationException(510,"user not found exception");
    }
}
