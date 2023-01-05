package com.bee.beeWatching.Service.Impl;

import com.bee.beeWatching.Model.User;
import com.bee.beeWatching.Repository.UserRepository;
import com.bee.beeWatching.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getRandomUser() {
        int rand = (int)(Math.random() * userRepository.findAll().size()) + 1;
        return userRepository.findById(rand).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findUserByName(name);
    }
}
