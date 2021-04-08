package com.example.quintessenceFirstPartBook.repository;

import com.example.quintessenceFirstPartBook.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findOtpByUsername(String username);
}
