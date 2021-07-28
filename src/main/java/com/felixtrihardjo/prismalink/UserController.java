package com.felixtrihardjo.prismalink;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @GetMapping("/user/details/{id}")
    public User getUser(@PathVariable Integer id) {
        if(userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else return  null;
    }
    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @PutMapping("/user/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(user, id);
    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
    @GetMapping("/user/date/{created_date}")
    public List<User> getUserByDate(@PathVariable(name = "created_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date created_date) {
    	if(userRepository.findByDate(created_date).isPresent())
    		return userRepository.findByDate(created_date).get();
    	return null;
    }
}



