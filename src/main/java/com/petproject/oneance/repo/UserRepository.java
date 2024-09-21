package com.petproject.oneance.repo;

import com.petproject.oneance.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (created_at, email, password_hash, role, updated_at, username) " +
                   "VALUES (?1, ?2, ?3, CAST(?4 AS user_role), ?5, ?6)", nativeQuery = true)
    void saveWithRoleWithoutId(LocalDateTime createdAt, String email,
                               String passwordHash, String role,
                               LocalDateTime updatedAt, String username);

    // Метод для сохранения объекта User
    @Transactional
    default void saveUserWithRole(User user) {
        saveWithRoleWithoutId(
            user.getCreatedAt(),
            user.getEmail(),
            user.getPasswordHash(),
            user.getRole().getRole().toLowerCase(),
            user.getUpdatedAt(),
            user.getUsername()
        );
    }
}

