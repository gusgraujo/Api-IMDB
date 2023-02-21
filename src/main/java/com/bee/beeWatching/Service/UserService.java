package com.bee.beeWatching.Service;

import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.User;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {

    public User updateUser(int id, User user) throws ResourceNotFoundException;

    User saveUser(User entity);

    User findById(int id) throws ResourceNotFoundException;

    public List<User> findAll();

    void deleteById(int id);

    public User findByUsername(String username);

}