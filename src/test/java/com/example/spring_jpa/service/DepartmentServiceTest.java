package com.example.spring_jpa.service;

import com.example.spring_jpa.domain.dto.DepartmentDto;
import com.example.spring_jpa.domain.entity.Department;
import com.example.spring_jpa.domain.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    public DepartmentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDepartment_Success() {
        // Given
        DepartmentDto.Request.Create request = new DepartmentDto.Request.Create();
        request.setDeptCode("CS001");
        request.setDeptName("컴퓨터공학과");
        request.setLocation("공학관 401호");

        Department department = Department.createDepartment("CS001", "컴퓨터공학과", "공학관 401호");

        when(departmentRepository.existsByDeptCode("CS001")).thenReturn(false);
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        // When
        Long departmentId = departmentService.createDepartment(request);

        // Then
        assertNotNull(departmentId);
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void createDepartment_AlreadyExists() {
        // Given
        DepartmentDto.Request.Create request = new DepartmentDto.Request.Create();
        request.setDeptCode("CS001");

        when(departmentRepository.existsByDeptCode("CS001")).thenReturn(true);

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            departmentService.createDepartment(request);
        });

        assertEquals("이미 존재하는 학과 코드입니다.", exception.getMessage());
    }
}