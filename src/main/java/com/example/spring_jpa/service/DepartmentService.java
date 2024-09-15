package com.example.spring_jpa.service;

import com.example.spring_jpa.domain.dto.DepartmentDto;
import com.example.spring_jpa.domain.entity.Department;
import com.example.spring_jpa.domain.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public Long createDepartment(DepartmentDto.Request.Create request) {
        if (departmentRepository.existsByDeptCode(request.getDeptCode())) {
            throw new IllegalArgumentException("이미 존재하는 학과 코드입니다.");
        }

        Department department = Department.createDepartment(
                request.getDeptCode(),
                request.getDeptName(),
                request.getLocation()
        );

        departmentRepository.save(department);
        return department.getId();
    }

    @Transactional
    public void updateDepartment(String deptCode, DepartmentDto.Request.Update request) {
        Department department = departmentRepository.findByDeptCode(deptCode)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학과입니다."));

        department.updateDepartment(request.getDeptName(), request.getLocation());
    }

    public DepartmentDto.Response getDepartment(String deptCode) {
        Department department = departmentRepository.findByDeptCode(deptCode)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학과입니다."));

        return convertToDto(department);
    }

    private DepartmentDto.Response convertToDto(Department department) {
        DepartmentDto.Response dto = new DepartmentDto.Response();
        dto.setId(department.getId());
        dto.setDeptCode(department.getDeptCode());
        dto.setDeptName(department.getDeptName());
        dto.setLocation(department.getLocation());
        return dto;
    }
}
