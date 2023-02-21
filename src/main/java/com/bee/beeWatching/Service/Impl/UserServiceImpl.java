package com.bee.beeWatching.Service.Impl;


import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.User;
import com.bee.beeWatching.Repository.UserRepository;
import com.bee.beeWatching.Service.FileStorageService;
import com.bee.beeWatching.Service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Override
    public User updateUser(int id, User user) throws ResourceNotFoundException {
        try{
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

            existingUser.setUsername(user.getUsername());
            existingUser.setUpdatedAt(new Date());
            return saveUser(existingUser);
        }catch (ResourceNotFoundException e){
            logger.error("User not found with id: " + id);
            return null;
        }
    }

    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User newUser) {
        try {
            if (newUser.getGuuid() == null) {
                newUser.setGuuid(UUID.randomUUID());
            }
            if (newUser.getCreatedAt() == null) {
                newUser.setCreatedAt(new Date());
            }
            newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
            newUser.setUpdatedAt(new Date());
            return userRepository.save(newUser);
        } catch (Exception e) {
            logger.error("Error saving user: " + e.getMessage(), e);
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public User findById(int id) throws ResourceNotFoundException {
        Optional<User> optionalEntity = userRepository.findById(id);
        if (optionalEntity.isPresent()) {
            logger.info(String.format("User with id %d found!", id));
            return optionalEntity.get();
        } else {
            logger.error(String.format("User with id %d not found!", id));
            throw new ResourceNotFoundException(String.format("User with id %d not found!", id));
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }


}
