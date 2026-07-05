package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.dto.LoginRequest;
import com.airclaimbd.airclaimbackend.dto.LoginResponse;
import com.airclaimbd.airclaimbackend.entity.User;
import com.airclaimbd.airclaimbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ==========================
    // User Signup
    // ==========================
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userRepository.save(user);
    }

    // ==========================
    // User Login
    // ==========================
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmailAndPassword(
                request.getEmail(),
                request.getPassword()
        );

        if (user != null) {
            return new LoginResponse(
                    "Login Successful",
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            );
        }

        return new LoginResponse(
                "Invalid Email or Password",
                null,
                null,
                null
        );
    }
}