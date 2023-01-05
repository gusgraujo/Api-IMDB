package com.bee.beeWatching.Controller;

import org.springframework.core.io.Resource;
import com.bee.beeWatching.Exception.FileStorageException;
import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Avatar;
import com.bee.beeWatching.Model.User;
import com.bee.beeWatching.Service.FileStorageService;
import com.bee.beeWatching.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FileStorageService fileStorageService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserWithID(
            @PathVariable int id) throws Exception {
        User user = userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this ID:"+ id) );
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/getName/{name}")
    public List<User> getUsersByName(@PathVariable String name){
            return userService.getUsersByName(name);
    }
    @PostMapping("/add")
    public User createUser(
            @RequestBody User user)
    {
        return userService.save(user);
    }
    @GetMapping("/randomUser")
    public User getRandomUser(){
       return userService.getRandomUser();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable int id,
            @Validated @RequestBody User user) throws Exception {
        Optional<User> newUser = userService.getUserById(id);
        if (newUser.isPresent()){
            newUser.get().setName(user.getName());
            newUser.get().setDiscordName(user.getDiscordName());
        }else {
            throw new ResourceNotFoundException("User not found : " + id);
        }
        final User updatedUser = userService.save(newUser.get());
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/getAllSorted")
    public List<User> getAllUsersSorted(){
        return userService.getAll().stream().sorted((u1,u2) -> u1.getName().compareTo(u2.getName())).collect(Collectors.toList());
    }

    //Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(
            @PathVariable int id) throws Exception
    {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()){
            userService.delete(user.get());
        }else {
            throw new ResourceNotFoundException("User not found : " + id);
        }
        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/setAvatar/{id}")
    public User setAvatar(@PathVariable int id,@RequestParam("file") MultipartFile file) throws ResourceNotFoundException {
        Optional<User> user = userService.getUserById(id);
        String regex = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$";
        Pattern p = Pattern.compile(regex);
        if (user.isPresent()){
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            Avatar newAvatar = new Avatar(fileName, fileDownloadUri, file.getContentType(), file.getSize());
            user.get().setAvatarFile(newAvatar);
            Matcher m = p.matcher(fileName);
            if (m.matches())
            {
                return userService.save(user.get());
            }else {
                throw new FileStorageException("File pattern invalid");
            }
        }else{
            throw new ResourceNotFoundException("User not found : " + id);
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
