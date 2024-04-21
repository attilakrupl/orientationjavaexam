package com.example.orientationexam.services;

import com.example.orientationexam.models.User;
import com.example.orientationexam.models.UserGroup;
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

    public List<User> getAllUsersForGroup(UserGroup g) {
        return userRepository.findByUserGroup(g.getId());
    }

    public User addNewUserByName(String name) {
        User user = new User();
        user.setName(name);
        user.setGroup_id(null);
        userRepository.save(user);
        return user;
    }

    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    public void updateUsers(List<User> users)
    {
        for(var u : users) {
            updateUser(u);
        }
    }

}
