package com.kinematech.kinematech_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kinematech.kinematech_backend.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query ("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.emailVerificationToken = :token")
    User findByEmailVerificationToken(String token);
}