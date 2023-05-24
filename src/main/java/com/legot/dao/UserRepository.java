package com.legot.dao;

import com.legot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByEmailAndPassword(String email, String password);
    Optional<Users> findByEmail(String email);
}
