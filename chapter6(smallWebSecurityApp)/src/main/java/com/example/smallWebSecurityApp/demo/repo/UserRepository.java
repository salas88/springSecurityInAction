package com.example.smallWebSecurityApp.demo.repo;

import com.example.smallWebSecurityApp.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
