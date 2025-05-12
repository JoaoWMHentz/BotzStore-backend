package com.kinematech.kinematech_backend.controller;

import com.kinematech.kinematech_backend.dto.LoginRequest;
import com.kinematech.kinematech_backend.dto.RegisterRequest;
import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.service.*;
import com.kinematech.kinematech_backend.utils.AuthUtils;
import com.kinematech.kinematech_backend.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            if (request.getPassword() == null || request.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Password is required");
            }
            if (request.getName() == null || request.getName().isEmpty()) {
                return ResponseEntity.badRequest().body("Username is required");
            }
            if (request.getEmail() == null || request.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (request.getPassword().length() < 6) {
                return ResponseEntity.badRequest().body("Password must be at least 6 characters");
            }
            if (userService.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            String salt = AuthUtils.getSalt();
            String hashedPassword = AuthUtils.hashPassword(request.getPassword(), salt);

            User user = new User();
            user.setUsername(request.getName());
            user.setPassword(hashedPassword);
            user.setSalt(salt);
            user.setEmail(request.getEmail());

            // Gerar token de verificação
            String verificationToken = AuthUtils.generateRandomToken();
            user.setEmailVerificationToken(verificationToken);
            user.setTokenExpiration(System.currentTimeMillis() + 3600000); // 1 hora de validade

            userService.save(user);

            // Enviar e-mail de verificação
            //emailService.sendVerificationEmail(user.getEmail(), verificationToken);

            user.setPassword("");
            user.setSalt("");
            return ResponseEntity.ok("User registered successfully. Please verify your email.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        try {
            User user = userService.findByVerificationToken(token);

            if (user == null) {
                return ResponseEntity.badRequest().body("Invalid token");
            }

            if (user.getTokenExpiration() < System.currentTimeMillis()) {
                return ResponseEntity.badRequest().body("Token expired");
            }

            user.setActive(true);
            user.setEmailVerificationToken(null);
            user.setTokenExpiration(null);
            userService.save(user);

            return ResponseEntity.ok("Email verified successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.findByEmail(request.getEmail());

            if (user == null || !user.isActive()) {
                return ResponseEntity.status(401).body("Invalid credentials or email not verified");
            }

            String hashedPassword = AuthUtils.hashPassword(request.getPassword(), user.getSalt());
            if (!hashedPassword.equals(user.getPassword())) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            String token = JwtUtils.generateToken(user.getEmail(), user.getSalt());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

}