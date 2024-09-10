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
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String deptCode;

    @Column(nullable = false, length = 10)
    private String deptName;

    @Column(length = 100)
    private String location;

    @OneToMany(mappedBy = "department")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Professor> professorList = new ArrayList<>();

    public static Department createDepartment(String deptCode, String deptName, String location) {
        Department department = new Department();
        department.deptCode = deptCode;
        department.deptName = deptName;
        department.location = location;
        return department;
    }

    public void updateDepartment(String deptName, String location) {
        this.deptName = deptName;
        this.location = location;
    }
}
