package com.accenture.department_service.controller;

import com.accenture.department_service.client.EmployeeClient;
import com.accenture.department_service.model.Department;
import com.accenture.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public class DepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    DepartmentRepository repository;

    EmployeeClient employeeClient;

    public DepartmentController(DepartmentRepository repository, EmployeeClient employeeClient) {
        this.repository = repository;
        this.employeeClient = employeeClient;
    }

    @PostMapping("/")
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.add(department);
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/")
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/organization/{organizationId}")
    public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Departments find with employees: organizationId={}", organizationId);
        List<Department> departments = repository.findByOrganization(organizationId);
        departments.forEach(department -> department.setEmployees(employeeClient.findByDepartment(department.getId())));
        return departments;
    }
}
