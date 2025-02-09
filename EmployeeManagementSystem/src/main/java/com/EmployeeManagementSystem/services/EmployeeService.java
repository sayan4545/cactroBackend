package com.EmployeeManagementSystem.services;

import com.EmployeeManagementSystem.dtos.EmployeeDto;
import com.EmployeeManagementSystem.entities.EmployeeEntity;
import com.EmployeeManagementSystem.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeEntity> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = employeeList.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
        return employeeDtoList;


    }

}
