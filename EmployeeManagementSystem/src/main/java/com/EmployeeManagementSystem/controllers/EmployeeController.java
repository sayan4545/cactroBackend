package com.EmployeeManagementSystem.controllers;

import com.EmployeeManagementSystem.dtos.EmployeeDto;
import com.EmployeeManagementSystem.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/getAllEmployees")
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
