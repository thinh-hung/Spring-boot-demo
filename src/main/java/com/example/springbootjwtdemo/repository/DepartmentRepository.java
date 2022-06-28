package com.example.springbootjwt5.repository;


import com.example.springbootjwt5.model.Department;
import com.example.springbootjwt5.model.EDevelopment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(EDevelopment name);
}