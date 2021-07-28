package com.felixtrihardjo.prismalink;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    /** Create a new User */
    public ResponseEntity<Object> createUser(User model) {
        User user = new User();
        System.out.println(model.getRoles().get(0));
        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User is already present. Fail to create user.");
        } else {
            user.setFull_name(model.getFull_name());
            user.setCreated_date(model.getCreated_date());
            user.setEmail(model.getEmail());
            user.setRoles(model.getRoles());

            User savedUser = userRepository.save(user);
            if (userRepository.findById(savedUser.getId()).isPresent())
                return ResponseEntity.ok("User Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
        }
    }

    /** Update an Existing User */
    @Transactional
    public ResponseEntity<Object> updateUser(User user, Integer id) {
        if(userRepository.findById(id).isPresent()) {
            User newUser = userRepository.findById(id).get();
            newUser.setFull_name(user.getFull_name());
            newUser.setCreated_date(user.getCreated_date());
            newUser.setEmail(user.getEmail());
            newUser.setRoles(user.getRoles());
            User savedUser = userRepository.save(newUser);
            if(userRepository.findById(savedUser.getId()).isPresent())
                return  ResponseEntity.accepted().body("User updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
    }
    /** Delete an User*/
    public ResponseEntity<Object> deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            if (userRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
            else return ResponseEntity.ok().body("Successfully deleted the specified user");
        } else return ResponseEntity.badRequest().body("Cannot find the user specified");
    }
}

