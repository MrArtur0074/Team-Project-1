package com.example.demo.repository;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE (:role IS NULL OR u.role = :role) " +
            "AND (:name IS NULL OR u.name LIKE %:name%) " +
            "AND (:email IS NULL OR u.email = :email)")
    List<User> findByFilters(Role role, String name, String email);

    List<User> findByRoleAndIsPending(Role role, Boolean isPending);

    Optional<User> findByEmail(String email);
}