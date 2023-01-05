package com.bee.beeWatching.Service;

import com.bee.beeWatching.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    public User getRandomUser();
    public List<User> getAll();

    public Optional<User> getUserById(int id);

    public User save(User newUser);
    public void delete(User user);
    public List<User> getUsersByName(String name);
}
