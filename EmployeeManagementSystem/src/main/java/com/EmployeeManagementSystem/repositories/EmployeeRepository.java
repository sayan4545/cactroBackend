package com.EmployeeManagementSystem.repositories;

import com.EmployeeManagementSystem.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
