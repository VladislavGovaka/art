package com.example.collectionsApp.repositories.user;

import com.example.collectionsApp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Optional<User> findById(Long id);
    void deleteById(Long id);
}
