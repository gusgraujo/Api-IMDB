package com.bee.beeWatching.Controller;

import com.bee.beeWatching.Exception.FileStorageException;
import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.ChangePasswordRequest;
import com.bee.beeWatching.Model.User;
import com.bee.beeWatching.Model.LoginRequest;
import com.bee.beeWatching.Service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        try{
            List<User> users = userService.findAll();
            if (users != null){
                logger.info(String.format("Users: %s", users));
                return new ResponseEntity<>(users, HttpStatus.OK);
            }else {
                logger.info("Users not found");
                return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            logger.error(String.format("Error: %s",e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable int id) throws ResourceNotFoundException {
        User user = userService.findById(id);
        if (user != null) {
            logger.info(String.format("User %d founded",id));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            logger.error(String.format("Error: %d NOT FOUND",id));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Validated User newUser) {
        try {
            if (userService.findByUsername(newUser.getUsername()) == null){
                User savedUser = userService.saveUser(newUser);
                logger.info("New user with name " + savedUser.getUsername() + " is registered.");
                return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
            }else {
                logger.info("Username already exists!");
                return new ResponseEntity<>(newUser, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            logger.error("Error occured while registering user", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @Validated @RequestBody User user) throws ResourceNotFoundException {
        try {
            User updatedUser = userService.updateUser(id, user);
            if (updatedUser != null) {
                logger.info("User with id {} updated successfully", id);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                logger.error("User with id {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while updating user with id {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            userService.deleteById(id);
            logger.info("User with id {} was successfully deleted.", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while deleting user with id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginRequest logicRequest){
        String username = logicRequest.getUsername();
        String password = logicRequest.getPassword();
        logger.info("Received login request for user: {}", username);

        User user = userService.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword()))
        {
            logger.info("Login successful for user: {}", username);
            return new ResponseEntity<>("Login successful!", HttpStatus.OK);
        }else {
            logger.info("Username or Password incorrect for user: {}", username);
            return new ResponseEntity<>("Username or Password incorrect!", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws ResourceNotFoundException {
        logger.info("Received request to change password for user: {}", changePasswordRequest.getUsername());
        User user = userService.findByUsername(changePasswordRequest.getUsername());
        if (user == null) {
            logger.warn("User not found: {}", changePasswordRequest.getUsername());
            return new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);
        }
        if (!BCrypt.checkpw(changePasswordRequest.getPassword(), user.getPassword())) {
            logger.warn("Incorrect current password for user: {}", changePasswordRequest.getUsername());
            return new ResponseEntity<>("Incorrect current password!", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(changePasswordRequest.getNewPassword());
        userService.updateUser(user.getId(),user);
        logger.info("Password changed successfully for user: {}", changePasswordRequest.getUsername());
        return new ResponseEntity<>("Password changed successfully!", HttpStatus.OK);
    }

}
