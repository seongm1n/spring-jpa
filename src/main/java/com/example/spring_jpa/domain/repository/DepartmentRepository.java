package com.example.spring_jpa.domain.repository;

import com.example.spring_jpa.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByDeptCode(String deptCode);
    boolean existsByDeptCode(String deptCode);
}
