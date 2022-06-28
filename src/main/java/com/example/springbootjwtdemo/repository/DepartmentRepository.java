package com.example.springbootjwtdemo.repository;



import com.example.springbootjwtdemo.model.Department;
import com.example.springbootjwtdemo.model.EDevelopment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(EDevelopment name);
}