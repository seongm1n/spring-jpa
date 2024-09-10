package com.example.spring_jpa.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String studentId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Integer grade;

    @Column(length = 20)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();

    public static Student createStudent(String studentId, String name, int grade, String phone, Department department) {
        Student student = new Student();
        student.studentId = studentId;
        student.name = name;
        student.grade = grade;
        student.phone = phone;
        student.department = department;
        return student;
    }

    public void updateStudent(String name, Integer grade, String phone) {
        this.name = name;
        this.grade = grade;
        this.phone = phone;
    }

    public void changeDepartment(Department department) {
        this.department = department;
    }
}
