package com.example.spring_jpa.controller;

import com.example.spring_jpa.common.ApiResponse;
import com.example.spring_jpa.domain.dto.DepartmentDto;
import com.example.spring_jpa.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createDepartment(
            @RequestBody DepartmentDto.Request.Create request) {
        try {
            Long departmentId = departmentService.createDepartment(request);
            return ResponseEntity.ok(ApiResponse.success(departmentId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{deptCode}")
    public ResponseEntity<ApiResponse<Void>> updateDepartment(
            @PathVariable String deptCode,
            @RequestBody DepartmentDto.Request.Update request) {
        try {
            departmentService.updateDepartment(deptCode, request);
            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{deptCode}")
    public ResponseEntity<ApiResponse<DepartmentDto.Response>> getDepartment(
            @PathVariable String deptCode) {
        try {
            DepartmentDto.Response response = departmentService.getDepartment(deptCode);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
