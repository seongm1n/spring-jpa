package com.example.spring_jpa.domain.repository;

import com.example.spring_jpa.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByProfessorId(String ProfessorId);
    List<Professor> findByDepartmentDeptCode(String deptCode);
}
