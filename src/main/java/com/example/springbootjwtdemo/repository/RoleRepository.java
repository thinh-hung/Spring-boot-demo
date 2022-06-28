package com.example.springbootjwt5.repository;
import com.example.springbootjwt5.model.ERole;
import com.example.springbootjwt5.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
