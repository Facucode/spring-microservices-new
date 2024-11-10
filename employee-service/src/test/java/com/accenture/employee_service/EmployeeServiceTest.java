package com.accenture.employee_service;

import com.accenture.employee_service.model.Employee;
import com.accenture.employee_service.repository.EmployeeRepository;
import com.accenture.employee_service.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetEmployeeById_Found() {

        Employee employee = new Employee(1L, 1L,"Carlos",28,"Leader");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertEquals("Carlos", result.getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(1L));
    }

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee(1L, 1L,"Carlos",28,"Leader");
        when(employeeRepository.add(employee)).thenReturn(new Employee(1L, 1L,"Carlos",28,"Leader"));

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee.getId());
        assertEquals("Jane", savedEmployee.getName());
        verify(employeeRepository, times(1)).add(employee);
    }
}
