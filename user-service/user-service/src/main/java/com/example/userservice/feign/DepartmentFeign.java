package com.example.userservice.feign;

import com.example.userservice.dto.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentFeign {
    @GetMapping("/department/{id}")
    public Department findByDepartmentId(@PathVariable("id") Long id);
}
