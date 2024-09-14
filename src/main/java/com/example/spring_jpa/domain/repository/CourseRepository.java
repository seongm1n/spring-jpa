package com.example.spring_jpa.domain.repository;

import com.example.spring_jpa.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);

    @Query("select c from Course c join fetch c.professor where c.professor.department.deptCode = :deptCode")
    List<Course> findByDepartmentCode(@Param("deptCode") String deptCode);
}
