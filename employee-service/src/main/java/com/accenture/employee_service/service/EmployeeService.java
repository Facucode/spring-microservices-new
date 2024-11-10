package com.accenture.employee_service.service;

import com.accenture.employee_service.model.Employee;
import com.accenture.employee_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.add(employee);
    }
}
