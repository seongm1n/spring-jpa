package com.example.spring_jpa.domain.repository;

import com.example.spring_jpa.domain.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentStudentIdAndSemester(String studentId, String semester);

    @Query("select e from Enrollment e join fetch e.student join fetch e.course " +
            "where e.semester = :semester and e.course.courseCode = :courseCode")
    List<Enrollment> findBySemesterAndCourseCode(
            @Param("semester") String semester,
            @Param("courseCode") String courseCode
    );
}
