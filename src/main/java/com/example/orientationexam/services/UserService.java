package com.example.orientationexam.services;

import com.example.orientationexam.models.User;
import com.example.orientationexam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public long countUsers() { return userRepository.count();}

    public User saveUser(String name) {
        User user = new User();
        user.setName(name);
        user.setForeignGroup(null);
        userRepository.save(user);
        return user;
    }

    public User saveExistingUser(User user) {
        userRepository.save(user);
        return user;
    }
}
