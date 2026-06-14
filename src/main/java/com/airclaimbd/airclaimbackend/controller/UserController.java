package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.dto.LoginRequest;
import com.airclaimbd.airclaimbackend.entity.User;
import com.airclaimbd.airclaimbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmailAndPassword(
                request.getEmail(),
                request.getPassword()
        );

        if (user != null) {
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }
}