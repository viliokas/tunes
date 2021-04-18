package com.example.tunes.service;

import com.example.tunes.exception.UserNotFoundException;
import com.example.tunes.model.User;
import com.example.tunes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) throw new UserNotFoundException(userId);
        return user.get();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
