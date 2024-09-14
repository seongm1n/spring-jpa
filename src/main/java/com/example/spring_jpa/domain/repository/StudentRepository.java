package com.example.spring_jpa.domain.repository;

import com.example.spring_jpa.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId(Long studentId);
    List<Student> findByDepartmentDeptCode(String deptCode);

    @Query("select s from Student s join fetch s.department where s.grade = :grade")
    List<Student> findByGradeWithDepartment(@Param("grade") Integer grade);
}
