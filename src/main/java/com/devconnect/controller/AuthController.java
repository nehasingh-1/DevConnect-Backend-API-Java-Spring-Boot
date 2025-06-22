package com.devconnect.controller;

import com.devconnect.dto.AuthRequest;
import com.devconnect.dto.AuthResponse;
import com.devconnect.model.User;
import com.devconnect.repository.UserRepository;
import com.devconnect.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private JwtService jwtService;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add("USER");
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).build();
    }
}
