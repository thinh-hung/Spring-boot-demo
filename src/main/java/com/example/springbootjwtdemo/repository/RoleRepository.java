package com.example.springbootjwtdemo.repository;

import com.example.springbootjwtdemo.model.ERole;
import com.example.springbootjwtdemo.model.Role;
import com.example.springbootjwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);





}
