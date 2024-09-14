package com.example.spring_jpa.service;

import com.example.spring_jpa.domain.dto.StudentDto;
import com.example.spring_jpa.domain.entity.Department;
import com.example.spring_jpa.domain.entity.Student;
import com.example.spring_jpa.domain.repository.DepartmentRepository;
import com.example.spring_jpa.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public Long CreateStudent(StudentDto.Request.Create request) {
        if (studentRepository.findByStudentId(request.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }

        Department department = departmentRepository.findByDeptCode(request.getDeptCode())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학과입니다."));

        Student student = Student.createStudent(
                request.getStudentId(),
                request.getName(),
                request.getGrade(),
                request.getPhone(),
                department
        );

        studentRepository.save(student);
        return student.getId();
    }

    @Transactional
    public void updateStudent(String studentId, StudentDto.Request.Update request) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생입니다."));

        student.updateStudent(request.getName(), request.getGrade(), request.getPhone());
    }

    public StudentDto.Response getStudent(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생입니다."));

        return convertToDto(student);
    }

    public List<StudentDto.Response> getStudentsByDepartment(String deptCode) {
        return studentRepository.findByDepartmentDeptCode(deptCode).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private StudentDto.Response convertToDto(Student student) {
        StudentDto.Response dto = new StudentDto.Response();
        dto.setId(student.getId());
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setGrade(student.getGrade());
        dto.setPhone(student.getPhone());
        return dto;
    }
}
