package com.example.departmentservice.controller;

import com.example.departmentservice.dto.APIResponse;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/")
    public List<Department> findAllDepartment(){
        return departmentService.findAllDepartmentList();
    }

    @GetMapping("/sort/{field}")
    public APIResponse<List<Department>> findAllDepartmentWithSorting(@PathVariable("field") String field){
        return departmentService.findDepartmentWithSorting(field);
    }

    @GetMapping("/page/{offset}/{pageSize}")
    public APIResponse<Page<Department>> findAllDepartmentWithPaging(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize){
        return departmentService.findDepartmentWithPagination(offset, pageSize);
    }

    @GetMapping("/page/{offset}/{pageSize}/sort/{field}")
    public APIResponse<Page<Department>> findAllDepartmentWithPagingAndSorting(@PathVariable("offset") int offset,
                                                                               @PathVariable("pageSize") int pageSize,
                                                                               @PathVariable("field") String field){
        return departmentService.findDepartmentWithPaginationWithSorting(offset, pageSize, field);
    }

    @GetMapping("/{id}")
    public Department findByDepartmentId(@PathVariable("id") Long id){
        LOGGER.info("fetching department with id " + id);
        return departmentService.findDepartmentById(id);
    }

    @PatchMapping("/update/{id}/{departmentName}")
    public Department updateDepartmentWithId(@PathVariable("id") Long id, @PathVariable("departmentName") String departmentName){
        return departmentService.updateDepartmentWithId(id, departmentName);
    }

    @PatchMapping("/update/{id}")
    public Department updateDepartmentWithMap(@PathVariable("id") Long id, @RequestBody Map<Object, Object> objectMap){
        return departmentService.upadteDepartmentWithMap(id, objectMap);
    }
}
