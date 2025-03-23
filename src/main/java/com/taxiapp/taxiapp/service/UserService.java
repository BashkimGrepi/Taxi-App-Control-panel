package com.taxiapp.taxiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxiapp.taxiapp.domain.User;
import com.taxiapp.taxiapp.repository.UserRepository;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {

        return (List<User>) userRepository.findAll();
    }
    
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);

    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
