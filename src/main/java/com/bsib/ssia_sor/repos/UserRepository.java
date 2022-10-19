package com.bsib.ssia_sor.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsib.ssia_sor.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findUserByUsername(String username);
}
