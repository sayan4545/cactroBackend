package com.EmployeeManagementSystem.services;

import com.EmployeeManagementSystem.entities.EmployeeEntity;
import com.EmployeeManagementSystem.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeCacheService {

    private static final int MAX_SIZE = 10;
    private final EmployeeRepository employeeRepository;
    private final Map<Long, EmployeeEntity> cache = new LinkedHashMap<Long, EmployeeEntity>(MAX_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, EmployeeEntity> eldest) {
            return size() > MAX_SIZE;
        }
    };

    public EmployeeCacheService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String storeEmployee(EmployeeEntity employee) {
        synchronized (cache) {
            if (cache.size() >= MAX_SIZE && !cache.containsKey(employee.getEmpId())) {
                return "Cache fullfilled";
            }
            //saving to the db
            cache.put(employee.getEmpId(), employee);
            employeeRepository.save(employee);
        }
        return "Employee saved";
    }

    public EmployeeEntity retrieveEmployee(Long empId) {
        synchronized (cache) {
            if (cache.containsKey(empId)) {
                return cache.get(empId);
            }
        }

        // Fetch data  from database if not in present in the catch
        Optional<EmployeeEntity> employee = employeeRepository.findById(empId);
        if (employee.isPresent()) {
            synchronized (cache) {
                cache.put(empId, employee.get());
            }
            return employee.get();
        }
        return null;
    }

    public String removeEmployee(Long empId) {
        synchronized (cache) {
            cache.remove(empId);
        }
        employeeRepository.deleteById(empId);
        return "Employee removed successfully";
    }
}

