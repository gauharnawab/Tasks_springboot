package com.email.Controller;


import com.email.Entity.User;
import com.email.Repository.UserRepository;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // Create a new user
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Validated @RequestBody User user, BindingResult bindingResult) {
        // Validate the user input
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            // Save the user to the database
            User savedUser = userRepository.save(user);

            // Send a welcome email to the user
            String subject = "Welcome to Our App";
            String body = "Dear " + user.getName() + ", welcome to our app!";
            emailService.sendEmail(user.getEmail(), subject, body);

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (MessagingException e) {
            // Handle email sending failure
            return new ResponseEntity<>("Failed to send email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Retrieve all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // Update a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Validated @RequestBody User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            // Update the existing user with the new data
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            // Add other fields to update as needed

            User updated = userRepository.save(existingUser);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>("User with ID " + id + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // Add other CRUD operations as needed
}
