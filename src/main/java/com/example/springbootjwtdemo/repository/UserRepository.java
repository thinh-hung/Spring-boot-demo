package com.example.springbootjwtdemo.repository;


import com.example.springbootjwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    List<User> findByRoles(Role role);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
