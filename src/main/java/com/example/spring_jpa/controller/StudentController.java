package com.example.spring_jpa.controller;

import com.example.spring_jpa.common.ApiResponse;
import com.example.spring_jpa.domain.dto.StudentDto;
import com.example.spring_jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createStudent(
            @RequestBody StudentDto.Request.Create request) {
        try {
            Long studentId = studentService.createStudent(request);
            return ResponseEntity.ok(ApiResponse.success(studentId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<ApiResponse<Void>> updateStudnet(
            @PathVariable String studentId,
            @RequestBody StudentDto.Request.Update request) {
        try {
            studentService.updateStudent(studentId, request);
            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<StudentDto.Response>> getStudent(
            @PathVariable String studentId) {
        try {
            StudentDto.Response response = studentService.getStudent(studentId);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/department/{deptCode}")
    public ResponseEntity<ApiResponse<List<StudentDto.Response>>> getStudentsByDepartment(
            @PathVariable String deptCode) {
        List<StudentDto.Response> students = studentService.getStudentsByDepartment(deptCode);
        return ResponseEntity.ok(ApiResponse.success(students));
    }
}
