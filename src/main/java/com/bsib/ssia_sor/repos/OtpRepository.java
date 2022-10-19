package com.bsib.ssia_sor.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsib.ssia_sor.entities.Otp;

public interface OtpRepository extends JpaRepository<Otp, String> {
  Optional<Otp> findOtpByUsername(String username);
}
