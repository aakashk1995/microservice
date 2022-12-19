package com.example.departmentservice.feign;

import com.example.departmentservice.entity.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "department-service")
public interface DepartmentMockFeign {
    @GetMapping("/")
    public List<Department> findAllDepartmentMock();
}
